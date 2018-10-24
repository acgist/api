# api

#### 项目介绍
一个基于Spring Cloud的HTTP接口DEMO项目。  
内网访问均不需要权限，外网访问项目为接口服务和网站服务，均通过网关服务对外提供同时进行负载均衡和鉴权。  

#### SESSION共享
Spring Session Redis

#### Spring Cloud组件
Zuul：网关  
Eureka：注册中心  
Config：配置中心  
Stream：消息队列  
Bus：消息总线（stream）  
Sleuth：链路跟踪（stream）  
Feign/Ribbon：服务调用  
Hystrix：熔断器（stream）  
Turbine：熔断器仪表盘聚合（stream）  
Dashboard：仪表盘  

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
* 30：系统网关  
* 40：服务网关
* 50：网站网关
** 业务服务类型：** 
* 10：管理平台（自己开发）
* 12：管理平台（Spring Boot Admin）
* 20：网站服务
* 30：静态资源
* 40：用户服务
* 50：订单服务
* 60：网关记录服务

#### 项目结构
|目录|描述|
|:-|:-|
|api-boot-admin|Spring Boot Admin管理|
|api-common|通用模块|
|api-config|配置中心|
|api-registry|注册中心|
|api-gateway|网关模块|
|api-service|服务模块|

#### 管理系统菜单
系统管理  
	系统用户  
	角色管理  
服务管理  
	服务监控：查看、关闭、仪表盘、端点操作  
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
https://preview.pro.ant.design  
https://gitee.com/minull/ace-security  

#### 接口映射
|地址|说明|
|:-|:-|
|/gateway/api/**|网关接口|
/service/**|服务|

#### 包结构
|包路径|作用|
|:-|:-|
|com.api.main|main方法|
|com.api.feign|feign模块|
|com.api.data|数据库模块|
|com.api.core|主要方法，用于初始化时自动加载配置|
|com.api.core.服务模块.*|服务模块|

#### 常见忽略错误
@EnableHystrix：用于开启/actuator/hystrix.stream端点  
@EnableHystrixDashboard：开启hystrix仪表盘  
@EnableFeignClients：需要指定包路径  
feign模块以及包含hystrix熔断功能，但是没有端点监控功能（@EnableHystrix）  