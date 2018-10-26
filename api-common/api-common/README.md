# 通用模块
#### 添加数据库使用
maven添加：
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
添加扫描：
```
@EntityScan("com.api.data.**.entity")
@ComponentScan({"com.api.data"})
@EnableJpaRepositories(basePackages = "com.api.data.**.repository", repositoryBaseClass = BaseExtendRepositoryImpl.class)
@EnableTransactionManagement
```

#### 添加服务
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