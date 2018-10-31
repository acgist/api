<!DOCTYPE html>
<html>
	<head>
		<#include "/admin/head.ftl" >
		<title>系统角色</title>
		<#include "/admin/resources.ftl" >
	</head>
	<body>
		<div class="iframe-content">
			<div class="layui-btn-group">
				<button class="layui-btn" id="create">新建</button>
			</div>
			<table class="layui-table" id="data-table" lay-filter="data-table"></table>
		</div>
		<script type="text/html" id="handle">
			<a class="layui-btn layui-btn-sm" lay-event="update">修改</a>
			<a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="permission">权限</a>
			<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</a>
		</script>
		<script type="text/javascript">
		layui.use(['layer', 'table'], function() {
			var $ = layui.$;
			var table = layui.table;
			table.render({
				method : 'POST',
				elem : '#data-table',
				url : '/role/list',
				cols : [[
					{checkbox : true, fixed : true},
					{field : 'name', width : 200, sort : true},
					{field : 'memo'},
					{toolbar : '#handle', width : 400}
				]],
				id : 'data-table-model'
			});
			$("#create").on('click', function() {
				layer.open({
					type : 2,
					title : '添加角色',
					area : ['800px', '400px'],
					content : '/role/submit'
				});
			});
			table.on('tool(data-table)', function(obj) {
				var data = obj.data;
				if(obj.event == 'delete') {
					layer.confirm('确定删除角色（' + data.name + '）吗？', {icon : 3, title : '提示'}, function(index) {
						layer.closeAll();
						layer.open({type : 3});
						layui.jquery.post("/role/delete", {
							id : data.id
						}, function(message) {
							layer.closeAll();
							table.reload('data-table-model');
							layer.alert('系统用户删除成功', {icon : 1});
						});
					});
				} else if(obj.event == 'update') {
					layer.open({
						type : 2,
						title : '修改角色',
						area : ['800px', '400px'],
						content : '/role/update?id=' + data.id
					});
				} else if(obj.event == 'permission') {
					layer.open({
						type : 2,
						title : '角色权限',
						area : ['800px', '600px'],
						content : '/role/permission?id=' + data.id
					});
				}
			});
		});
		</script>
	</body>
</html>