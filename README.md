# api

#### 项目介绍
一个基于Spring Cloud的HTTP接口DEMO项目

#### 软件版本
* JAVA：JAVA 10
* Spring Boot：2.0.5.RELEASE
* Spring Cloud：Finchley.SR1

#### 服务端口
固定前缀+服务类型+服务序列号（10~99）  
* 系统服务：2+XX+XX  
* 业务服务：3+XX+XX  
** 系统服务类型：** 
* 10：注册中心
* 20：配置中心
* 30：管理平台（自己开发）
* 31：管理平台（Spring Boot Admin）
* 40：接口网关
* 41：系统网关
* 50：链路监控
* 51：仪表盘（turbine）
* 52：仪表盘（dashboard）
* 60：网站模块  
** 业务服务类型：** 
* 10：交易业务

#### 项目结构
├─api-common：通用模块  
├─api-config：配置中心  
├─api-registry：注册中心  
├─api-gateway：接口网关，系统服务网关  
├─api-gateway-admin：系统网关，管理平台网关  
├─api-admin-boot：管理平台，Spring Boot Admin  
├─api-admin：管理平台，模仿Spring Boot Admin项目，常用端点、仪表盘、链路监控整合
├─api-sleuth：链路调用监控系统  
├─api-turbine：仪表盘  
├─api-dashboard：仪表盘  
├─api-www：网站模块  
└─api-service-pay：服务-交易  

各个端点处理，网关端点权限  
JPA，消息中间件，异步信息处理，接口API页面

#### 映射关系
|网关|控制器|服务|
|:-|:-|:-|
|/gateway/api/pay|/pay|/service/pay|
|/gateway/api/pay/drawback|/pay/drawback|/service/pay/drawback|
|/gateway/api/pay/query|/pay/query|/service/pay/query|