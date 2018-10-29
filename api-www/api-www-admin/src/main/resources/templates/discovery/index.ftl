<!DOCTYPE html>
<html>
	<head>
		<#include "/admin/head.ftl" >
		<title>系统面板</title>
		<#include "/admin/resources.ftl" >
	</head>
	<body>
	<div class="iframe-content">
		<div class="layui-collapse" lay-filter="services">
			<#list services as service>
			<div class="layui-colla-item">
				<h2 class="layui-colla-title">${service.name}（${service.instanceCount()}）</h2>
				<div class="layui-colla-content">
					<#list service.instances as instance>
					<p>${instance.uri}</p>
					</#list>
				</div>
			</div>
			</#list>
		</div>
	</div>
	<script type="text/javascript">
	layui.use(['element', 'layer'], function(){
		var element = layui.element;
		var layer = layui.layer;
		element.on('collapse(services)', function(data) {
		});
	});
	</script>
	</body>
</html>