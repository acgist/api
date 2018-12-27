# 服务模块
maven添加：
```
<!-- 服务调用 -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
	<optional>true</optional>
</dependency>
<!-- hystrix监控 -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
	<optional>true</optional>
</dependency>
```
添加扫描：
```
// 添加监控
@EnableHystrix
// 添加服务
@ComponentScan({"com.api.feign"})
@EnableFeignClients("com.api.feign")
```