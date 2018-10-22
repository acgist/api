# api

#### 项目介绍
一个基于Spring Cloud的HTTP接口DEMO项目。  
内网访问均不需要权限，外网访问项目为接口服务和网站服务，均通过网关服务对外提供同时进行负载均衡。  
网关只负责负载均衡，接口服务和网站服务负责鉴权。

#### 数据处理
系统数据分为JSON和其他数据类型。

#### 软件版本
* JAVA：JAVA 10（不支持JAVA 11）
* Redis：3.X.X
* MySQL：5.X.X
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
|api-common|通用模块，工具、实体、数据库、服务接口|
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
各个端点处理，网关端点权限  
JPA，消息中间件，异步信息处理，接口API页面  
日志统一处理  
https://gitee.com/minull/ace-security  
https://preview.pro.ant.design  
项目本地缓存数据，bus刷新  
网页项目端点屏蔽  

#### 接口映射
|网关|服务|
|:-|:-|
|/gateway/api/**|/service/**|

#### 包结构
|包路径|作用|
|:-|:-|
|com.acgist.main|main方法|
|com.acgist.api|接口|
|com.acgist.pojo|POJO|
|com.acgist.config|配置|
|com.acgist.controller|网页|
|com.acgist.服务模块.*|服务模块|

#### 常见忽略错误
@EnableHystrix：用于开启/actuator/hystrix.stream端点  
@EnableHystrixDashboard：开启hystrix仪表盘  
@EnableFeignClients：需要指定包路径  
feign模块以及包含hystrix熔断功能，但是没有端点监控功能（@EnableHystrix）  