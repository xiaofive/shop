Redis-Cluster服务客户端，对其他模块提供通用Redis服务，开发中根据自己的需要调用即可。

几种客户端对比 

都是在Java中对Redis操作的封装。

1.Jedis
Jedis是Redis的Java实现的客户端，其API提供了比较全面的Redis命令的支持。支持基本的数据类型如：String、Hash、List、Set、Sorted Set。

#优点：比较全面的提供了Redis的操作特性，相比于其他Redis 封装框架更加原生。
编程模型： 使用阻塞的I/O，方法调用同步，程序流需要等到socket处理完I/O才能执行，不支持异步操作。Jedis客户端实例不是线程安全的，所以需要通过连接池来使用Jedis。

2.Lettuce
高级Redis客户端，用于线程安全同步，异步和响应使用，支持集群，Sentinel，管道和编码器。

优点：适合分布式缓存框架。
编程模型：基于Netty框架的事件驱动的通信层，其方法调用是异步的。Lettuce的API是线程安全的，所以可以操作单个Lettuce连接来完成各种操作。

3.Redisson

Redisson实现了分布式和可扩展的Java数据结构。Redisson不仅提供了一系列的分布式Java常用对象，基本可以与Java的基本数据结构通用，还提供了许多分布式服务。

优点： 促使使用者对Redis的关注分离，让使用者能够将精力更集中地放在处理业务逻辑上，提供很多分布式相关操作服务，例如，分布式锁，分布式集合，可通过Redis支持延迟队列。
