# Wenda
-------------

类似知乎的一个问答社区网站，包括用户注册登录管理、邮件激活改密码、问题和回答的发布赞踩以及评论、站内关注和私信等社交功能、敏感词过滤、异步任务设计、timeline的实现等。

#### 示例:  

![image](example.png)

![image](example2.png)

### 设计思路

- **1.开发工具**：Git，Intelli J。涉及技术：Spring Boot，Velocity，MyBatis等。

- **2.登录注册**：使用拦截器实现；为了安全，密码加salt；token实现登录，登录的时候下发token，跟用户相关联起来，开始放在数据库里，后来优化了放在Redis里；还可以使用分布式统一登录系统，登录后token有效，所有相关平台都登录了，如百度、百度音乐、百度文库等；登录注册的验证，邮件激活。

- **3.敏感词过滤**：先构造一个前缀树，再通过有限状态机实现检测文本是否包含并替换掉敏感词，优点：有扩展性，方便添加过滤词；复杂度小，性能的提高。

- **4.Redis**：常用数据结构，String、Hash、List、Set、Zset(Sorted Set)；用在异步框架里实现赞踩、发邮件，以及排行榜里等地方。

- **5.异步框架**：负载多，为了能把结果更快反馈给用户，底层数据结构用Redis的队列写的异步框架，因为Redis能保证性能同步，还想过用优先队列可以把紧急任务优先处理掉，消息的发射、处理、事件的模型定义、具体handler，定义了一些公共接口。

- **6.发送邮件**：SSL连接，邮箱开启账号设置授权才可以发送。

- **7.内容排序算法**：把好的内容挑选出来，可参考 Quora、Reddit、StackOverflow 等网站使用的排序算法。

- **8.推拉timeline**：推的好处：实时性快，让好友快速得到反馈；拉：节省僵尸号、不活跃用户的空间。推拉结合，timeline里每个新鲜事长相不一样，和velocity相结合，后台数据一样，结合模板快速渲染时间轴。

### TODO

- 爬取和导入其他网站问题和回答的数据，solr搜索，打包以及服务器部署等

## License

