# api

#### 项目介绍
一个基于Spring Cloud的HTTP接口DEMO项目。  
内网访问均不需要权限，外网全部需要经过网关进行访问，服务不直接对外提供服务，网关负责鉴权、数据校验等操作。

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
* 32：仪表盘
* 33：链路监控
* 40：服务网关
* 41：系统网关  
** 业务服务类型：** 
* 10：用户业务
* 20：订单业务

#### 项目结构
├─api-common：通用模块  
├─api-config：配置中心  
├─api-registry：注册中心  
├─api-gateway-admin：系统网关，管理平台网关，session共享  
├─api-admin：管理平台，模仿Spring Boot Admin项目，常用端点、仪表盘、链路监控整合  
├─api-admin-boot：管理平台，Spring Boot Admin  
├─api-admin-sleuth：链路调用监控系统  
├─api-admin-dashboard：仪表盘：dashboard、turbine  
├─api-gateway-service：服务网关，系统服务网关，签名验证数据集校验  
├─api-service-www：服务-网站  
├─api-service-user：服务-用户  
└─api-service-order：服务-订单    

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

	sign();
	String message = ValidatorUtils.verify(this); // 数据校验
	if(message != null) {
		throw new ErrorCodeException(APICode.CODE_3000, message);
	}

各个端点处理，网关端点权限  
JPA，消息中间件，异步信息处理，接口API页面  
日志统一处理  
https://gitee.com/minull/ace-security  
https://preview.pro.ant.design  
项目本地缓存数据，bus刷新  

#### 数据库表
|表名|作用|
|:-|:-|
|tb_user|用户|
|tb_order|订单|
|tb_admin|管理员|
|tb_menu|菜单栏|

#### 映射关系
|映射|类型|
|:-|:-|
|/admin/**|后台服务|
|/gateway/api/**|接口服务|
|/**|接口服务|
** 接口映射 **
|网关|控制器|服务|
|:-|:-|:-|
|/gateway/api/pay|/pay|/service/pay|
|/gateway/api/pay/drawback|/pay/drawback|/service/pay/drawback|
|/gateway/api/pay/query|/pay/query|/service/pay/query|

#### 包结构
|包路径|作用|
|:-|:-|
|com.acgist.api|接口|
|com.acgist.service|服务|
|com.acgist.modules|组件|
|com.acgist.controller|网页|
|com.acgist.service.impl|服务实现|
