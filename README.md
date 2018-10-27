# API-ACGIST

#### 项目介绍
一个基于Spring Cloud的HTTP接口DEMO项目。  

#### 服务安全
* 集群内服务器均公开nginx端口（80），其余所有端口均只允许内部集群网络访问。
* 客户端通过nginx访问接口网关，通过网关访问内部服务或站点，网关负责鉴权和屏蔽端点。
* 内网含有通知的情况需要验证通知地址是否是外网地址（防止攻击集群内部端点）。

#### SESSION共享
Spring Session Redis

#### Spring Cloud组件
Zuul：网关  
Eureka：注册中心  
Config：配置中心  
Stream：消息队列  
Bus：消息总线（stream）  
Feign/Ribbon：服务调用  
Sleuth/Zipkin：链路跟踪（stream）  
Hystrix：熔断器（stream）  
Turbine/Dashboard：熔断器仪表盘聚合（stream）  

#### 软件版本
* JAVA：JAVA 10（不支持JAVA 11）
* Redis：3.X.X
* MySQL：5.X.X
* RabbitMQ：3.X.X
* Spring Boot：2.0.5.RELEASE
* Spring Cloud：Finchley.SR1

#### 服务端口
固定前缀+服务类型+服务序列号（10~99）  
* 系统服务：2+XX+XX  
* 业务服务：3+XX+XX  

** 系统服务类型：** 
* 10：注册中心
* 20：配置中心
* 30：服务网关
* 80：网站网关（前端和静态资源：10-80、后台：80-99）
* 90：管理平台（Spring Boot Admin）

** 业务服务类型：** 
* 10：用户服务
* 20：订单服务
* 30：网关信息服务
* 80：网站网关（10-50：前端、50-80：后台、80-99：静态资源）

#### 项目结构
|目录|描述|
|:-|:-|
|api-common|通用模块|
|api-config|配置中心|
|api-registry|注册中心|
|api-www|网站模块|
|api-admin|Spring Boot Admin管理|
|api-gateway|网关模块|
|api-service|服务模块|

#### 管理系统菜单
系统管理  
	系统用户  
	角色管理  
服务管理  
	服务监控：查看、关闭、仪表盘、端点操作、启动通知、实例数量、bus刷新  
	服务链路  
用户管理  
	用户管理  
订单管理  
	订单管理  

#### 待办功能
后台  
各个端点处理，网关端点权限，页面优化    
消息中间件  
日志统一处理  
element-ui  

#### 接口映射
|地址|说明|
|:-|:-|
|/gateway/api/**|网关接口|
/service/**|服务|

#### 服务包结构
|包路径|作用|
|:-|:-|
|com.api.main|main方法|
|com.api.core.服务模块.*|服务模块|
|com.api.data.服务模块.*|数据库模块|
|com.api.feign.服务模块.*|feign模块|

#### 常见忽略错误
@EnableHystrix：用于开启/actuator/hystrix.stream端点  
@EnableHystrixDashboard：开启hystrix仪表盘  
@EnableFeignClients：需要指定包路径  
feign模块以及包含hystrix熔断功能，但是没有端点监控功能（@EnableHystrix）  