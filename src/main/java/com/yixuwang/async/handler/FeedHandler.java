package com.yixuwang.async.handler;

import com.alibaba.fastjson.JSONObject;
import com.yixuwang.async.EventHandler;
import com.yixuwang.async.EventModel;
import com.yixuwang.async.EventType;
import com.yixuwang.model.EntityType;
import com.yixuwang.model.Feed;
import com.yixuwang.model.Question;
import com.yixuwang.model.User;
import com.yixuwang.service.FeedService;
import com.yixuwang.service.FollowService;
import com.yixuwang.service.QuestionService;
import com.yixuwang.service.UserService;
import com.yixuwang.util.JedisAdapter;
import com.yixuwang.util.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * Created by yixu on 2018/7/3.
 */
@Component
public class FeedHandler implements EventHandler {
    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    @Autowired
    FeedService feedService;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    QuestionService questionService;


    private String buildFeedData(EventModel model) {
        Map<String, String> map = new HashMap<>();
        // 触发用户是通用的
        User actor = userService.getUser(model.getActorId());
        if (actor == null) {
            return null;
        }
        map.put("userId", String.valueOf(actor.getId()));
        map.put("userHead", actor.getHeadUrl());
        map.put("userName", actor.getName());

        if (model.getType() == EventType.COMMENT ||
                (model.getType() == EventType.FOLLOW  && model.getEntityType() == EntityType.ENTITY_QUESTION)) {
            //评论或关注问题时
            Question question = questionService.getById(model.getEntityId());
            if (question == null) {
                return null;
            }
            map.put("questionId", String.valueOf(question.getId()));
            map.put("questionTitle", question.getTitle());
            return JSONObject.toJSONString(map);
        }
        return null;
    }

    @Override
    public void doHandle(EventModel model) {
        /*
        // 为了测试，把model的userId随机一下
        Random r = new Random();
        model.setActorId(1+r.nextInt(10));
        */

        // 构造一个新鲜事
        Feed feed = new Feed();
        feed.setCreatedDate(new Date());
        feed.setType(model.getType().getValue());
        feed.setUserId(model.getActorId());
        feed.setData(buildFeedData(model));
        if (feed.getData() == null) {
            // 不支持的feed
            return;
        }
        feedService.addFeed(feed);


        /* 接下来是实现 推timeline */

        // 获得所有粉丝
        List<Integer> followers = followService.getFollowers(EntityType.ENTITY_USER, model.getActorId(), Integer.MAX_VALUE);
        // 系统队列
        followers.add(0);
        // 给所有粉丝的Redis队列推事件
        for (int follower : followers) {
            String timelineKey = RedisKeyUtils.getTimelineKey(follower);
            jedisAdapter.lpush(timelineKey, String.valueOf(feed.getId()));
            // 限制最长长度，如果timelineKey的长度过大，就删除后面的新鲜事
        }
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(new EventType[]{EventType.COMMENT, EventType.FOLLOW, EventType.LIKE});
    }
}
