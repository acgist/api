<!DOCTYPE html>
<html>
	<head>
		<#include "/admin/head.ftl" >
		<title>系统缓存</title>
		<#include "/admin/resources.ftl" >
	</head>
	<body>
		<div class="iframe-content cache">
			<fieldset class="layui-elem-field">
				<legend>清除缓存</legend>
				<div>
					<button class="layui-btn">测试</button>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>刷新缓存</legend>
				<div>
					<@autho pattern="/cache/permission/roles">
					<button class="layui-btn" id="permissionRoles">权限角色映射</button>
					</@autho>
				</div>
			</fieldset>
		</div>
		<script type="text/javascript">
		layui.use(['form', 'layer', 'table', 'jquery', 'element'], function() {
			var $ = layui.$;
			$('#permissionRoles').on('click', function() {
				layui.jquery.get("/cache/permission/roles", function() {
					layer.alert('缓存已刷新', {icon : 1});
				});
			});
		});
		</script>
	</body>
</html>