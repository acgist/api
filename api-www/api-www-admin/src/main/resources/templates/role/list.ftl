<!DOCTYPE html>
<html>
	<head>
		<#include "/admin/head.ftl" >
		<title>管理员列表</title>
		<#include "/admin/resources.ftl" >
	</head>
	<body>
		<div class="iframe-content">
			<table class="layui-table" lay-data="{url:'/admin/list',method:'POST',page:true}" lay-filter="data-table">
				<thead>
					<tr>
						<th lay-data="{type:'checkbox',fixed:'left'}"></th>
						<th lay-data="{field:'name',width:200,sort:true}">账号</th>
						<th lay-data="{field:'memo',width:200}">描述</th>
						<th lay-data="{toolbar:'#handle'}">操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<script type="text/html" id="handle">
			<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</a>
		</script>
		<script type="text/javascript">
		layui.use('table', function() {
			var table = layui.table;
			table.on('tool(data-table)', function(obj) {
				var data = obj.data;
				if(obj.event == 'delete') {
					layer.confirm('确定删除管理员（' + data.name + '）吗？', {icon : 3, title : '提示'}, function(index) {
						layer.closeAll();
						layer.open({type : 3});
					});
				}
			});
		});
		</script>
	</body>
</html>