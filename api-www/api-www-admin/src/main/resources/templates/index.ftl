<!DOCTYPE html>
<html>
	<head>
		<#include "/admin/head.ftl" >
		<title>API-ADMIN</title>
		<#include "/admin/resources.ftl" >
	</head>
	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<div class="layui-logo">API-ADMIN</div>
				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item">
						<a href="javascript:;"><img src="${staticBase}/resources/images/logo.png" class="layui-nav-img">贤心</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="">基本资料</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="">退出</a></li>
				</ul>
			</div>
			<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="" href="javascript:;">系统管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;">系统用户</a>
								</dd>
								<dd>
									<a href="javascript:;">系统角色</a>
								</dd>
								<dd>
									<a href="javascript:;">系统权限</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">服务管理</a>
							<!--
							查看、关闭、仪表盘、端点操作、启动通知、实例数量、bus刷新
							-->
							<dl class="layui-nav-child">
								<dd>
									<a href="/discovery" target="api-content">服务列表</a>
								</dd>
							</dl>
							<dl class="layui-nav-child">
								<dd>
									<a href="/hystrix/" target="api-content">服务监控</a>
								</dd>
							</dl>
							<dl class="layui-nav-child">
								<dd>
									<a href="/zipkin/" target="api-content">链路监控</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">用户管理</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">订单管理</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="layui-body">
				<iframe name="api-content" width="100%" height="100%" src="/system/index"></iframe>
			</div>
			<div class="layui-footer">
				<p>Copyright <span>&copy;</span> 2013-${.now?string("yyyy")} ACGIST.COM. All Rights Reserved.</p>
			</div>
		</div>
		<script>
			layui.use('element', function() {
				var element = layui.element;
			});
		</script>
	</body>
</html>