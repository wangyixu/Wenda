# Wenda

A web site like zhihu.com

- 1.开发工具Git，Intelli J。Java语言脱颖而出说研究过JVM，垃圾回收算法、参数配置与后台运行的关系。

- 2.Spring Boot, Velocity: Spring控制反转、依赖注入，这样解决了一些数据初始化的问题，具体实现，为什么分为controller、service等四层，找到spring框架的一两个点深入研究下。Velocity和其他语言Java、c++一样是相通的，include把公用的东西提取出来，为了使前后端分离，解耦，数据怎么传递、怎么解析的。

- 3.MyBatis: 怎么把数据库的前后的读取、XML解析，怎么跟系统结合起来的。

- 4.登录注册：密码加salt，关于网络安全；拦截器实现，拦截器的思想；token实现登录，登录的时候下发token，跟用户相关联起来，开始放在数据库里，后来优化了放在Redis里；分布式统一登录系统，登录后token有效，所有相关平台都登录了，如百度、百度音乐、百度文库等；登录注册的验证，邮件激活。

- 5.前缀树：敏感词过滤，先构造一个前缀树，再通过有限状态机实现检测文本是否包含并替换掉敏感词，优点：有扩展性，方便添加过滤词；复杂度小，性能的提高。

- 6.Redis：底层：有哪些数据结构；应用：用在异步框架里、排行榜里。

- 7.异步框架：负载多，为了能把结果更快反馈给用户，自己写的，底层数据结构用的Redis的队列，因为Redis能保证性能同步，还想过用优先队列可以把紧急任务优先处理掉，消息的发射、处理、事件的模型定义、具体handler，定义了一些公共接口。

- 8.邮件：SSL连接，SSL是什么？邮箱开启账号设置授权才可以发送。排序算法：把好的问题挑选出来。

- 9.推拉timeline：推的好处：实时性快，让好友快速得到反馈；拉：节省僵尸号、不活跃用户的空间。推拉结合，timeline里每个新鲜事长相不一样，和velocity相结合，后台数据一样，结合模板快速渲染时间轴。

- 10.爬虫：怎么提高效率？多线程；怕的数据怎么解析，什么方法把好的数据解析出来；效率是多少，一天爬多少数据，怎么防止禁掉，换IP或是网上找了很多开放的代理服务器，切换服务器；最后使用的pyspider框架，使用的功能都是最基础的，性能的优化最重要。

- 11：solr搜索：分词算法怎么实现（太深了），框架搭建起来，里面怎么实现的：document、field、搜索的dataimport，它有个很好的框架能把数据很快地导进来；倒排？；去重，对比相似度，敏感哈希把相似度求出来，还有余弦定理等。

- 12.单元测试，部署：搭建服务器，对Linux有多熟，懂哪些东西；Nginx反向代理是什么，正反向代理区别，负载均衡，纽带（流量？）切换是什么。
