# seata demo #
## 环境准备 ##
本测试用例使用seata
已经配置好seata的运行环境，使用nacos实现seata的注册和配置中心。
### seata服务端配置 ###
1. 首先下载好seata，然后修改registry.conf文件，修改配置中心和注册中心为nacos。（其他的比如file模式和本地注册中心也就是file模式这里就不细说了，实际应用应该不会那样玩）
2. 使用seata官网提供的脚本，将seata配置文件导入到nacos。脚本相关的在seata/script文件中（注意！注意！注意！下载的seata，在seata目录下的bin、conf这些目录同级新建一个目录名为：script，详见2-文档中的script文件。位置放的不对，运行脚本可能会提醒找不到config.txt等错误），按照自己的需求config.txt中主要修改项为：
   1. store.mode=db
   2. 数据库的连接，修改为自己的账号密码
   `` store.db.dbType=mysql
      store.db.driverClassName=com.mysql.jdbc.Driver
      store.db.url=jdbc:mysql://127.0.0.1:3306/zsk_seata_db?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
      store.db.user=xxxx
      store.db.password=xxxxxxx``
   3. 使用官方的运行脚本：`nacos-config.sh`运行命令：`sh nacos-config.sh -h localhost -p 8848 -g SEATA_GROUP -t d36a1115-c73a-4bfe-9d41-f11e11104d93 -u nacos -w nacos`
   4. 运行命令中要注意将nacos的参数修改为自己环境的实际参数，主要有：
      1. -h:nacos的地址
      2. -p:nacos的端口
      3. -g:nacos的分组名
      4. -t:nacos的命名空间id
      5. -u:nacos账户名称
      6. -w:nacos账户密码
   5. 导入后查看下nacos是否有导入的配置信息
   6. 将seata服务端的表导入数据库，本例中叫：zsk_seata_db
   7. 启动seata。在seata目录下的bin中有启动脚本。启动后没有报错，并且可以看到启动后的端口号，就是成功了。
3. 将相应的数据表导入数据库
   1. 注意，数据库信息分为两种。一种是seata的服务端，本例中数据库名为：zsk_seata_db
   2. 每个业务模块表中，比如order_service和storage_service中都有一个叫：undo_log的表
   3. 别忘了顺便把本例中需要的表也导进去
4. 本例中，为seata server端创建了一个nacos命名空间d36a1115-c73a-4bfe-9d41-f11e11104d93，然后三个业务模块单独一个命名空间06636a8a-5857-416a-bb82-15de3cce540c。业务模块中nacos不需要什么配置文件了，新建个命名空间就行了
## AT模式 ##
对应模块：atDemo
### 模块介绍 ###
- business-service: 模拟业务模块。通过创建订单，向order-service和storage-service发送请求，模拟事务
- order-service: 订单模块
- storage-service：库存模块
### 实现方式 ###
1. 三个模块中的bootstrap文件中有介绍
2. 在business-service中创建订单方法上有个：`@GlobalTransactional`注解，这个注解就是开启了seata事务
3. 其他的就是微服务的知识了，比如nacos配置和注册中心的配置，这里就不细说了。
### 测试流程 ###
1. 首先启动三个模块
2. 本例中，是通过postman发送请求测试的，访问链接：`localhost:6760/business/buy`,post方式，传入的参数为：
`{
   "productId": "1",
   "userId": "1"
   }`
3. 查看数据库中可以发现，数据库：seata_demo_order，中表：order_info新增一个订单信息。数据库：seata_demo_storage,中表：storage_info，对应商品id会减少库存。这是一个成功的示例。
4. 如果修改库存模块，增加：`int a = 100/0;`，会发现order_info表和storage_info表都没有修改信息，即事务全部回滚了。
5. 通过断点可以知道，事务运行一半时，对于zsk_seata_db和两个业务库中的某个业务库（具体看断点打到哪，比如订单增加运行结束，库存还没修改，则看订单模块对应的数据库表中的undo_log表）中会有信息，随着事务的结束，会删除相应的信息。
这个牵涉到seata的运行原理和流程，这里就不细说了。
### 需要学习的知识点 ###
1. 微服务的搭建肯定要知道，不然nacos何去何从
2. 分布式事务的基础知识；协议2pc，3pc；解决方案。
3. seata的运行配置，运行原理
