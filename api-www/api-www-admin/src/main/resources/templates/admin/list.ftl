<!DOCTYPE html>
<html>
	<head>
		<#include "/admin/head.ftl" >
		<title>管理员列表</title>
		<#include "/admin/resources.ftl" >
	</head>
	<body>
		<div class="iframe-content">
			<div class="data-filter">
				<div class="layui-inline">
					<input class="layui-input" name="id" id="idInput" placeholder="ID" autocomplete="off" />
				</div>
				<div class="layui-inline">
					<input class="layui-input" name="name" id="nameInput" placeholder="账号" autocomplete="off" />
				</div>
				<button class="layui-btn" id="data-reload">搜索</button>
			</div>
			<table class="layui-table" id="data-table" lay-filter="data-table"></table>
		</div>
		<script type="text/html" id="handle">
			<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</a>
		</script>
		<script type="text/javascript">
		layui.use('table', function() {
			var $ = layui.$;
			var table = layui.table;
			table.render({
				method : 'POST',
				elem : '#data-table',
				url : '/admin/list',
				cols : [[
					{checkbox : true, fixed : true},
					{field : 'name', width : 200, sort : true},
					{field : 'memo', width : 200},
					{toolbar : '#handle'}
				]],
				id : 'data-table-model',
				page : true
			});
			$('#data-reload').on('click', function() {
				var idInput = $('#idInput');
				var nameInput = $('#nameInput');
				table.reload('data-table-model', {
					page : {curr : 1},
					where : {
						id : idInput.val(),
						name : nameInput.val()
					}
				});
			});
			table.on('tool(data-table)', function(obj) {
				var data = obj.data;
				if(obj.event == 'delete') {
					layer.confirm('确定删除管理员（' + data.name + '）吗？', {icon : 3, title : '提示'}, function(index) {
						layer.closeAll();
						layer.open({type : 3});
						layui.jquery.post("/admin/delete", {
							id : data.id
						}, function(message) {
							layer.closeAll();
							layer.alert('管理员已刷新', {icon : 1}, function() {
								table.reload('data-table-model');
								layer.closeAll();
							});
						});
					});
				}
			});
		});
		</script>
	</body>
</html>