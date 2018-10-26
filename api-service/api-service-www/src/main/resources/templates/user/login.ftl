<!DOCTYPE HTML>
<html>
	<head>
		<title>用户登录</title>
		<#include "/include/head.ftl">
		<meta name="keywords" content="用户登录" />
		<meta name="description" content="用户登录" />
		
		<link rel="shortcut icon" href="/favicon.ico" />
		<#include "/include/resources.ftl">
	</head>

	<body>
	<div class="container">
		<form class="form-horizontal" method="post" action="/user/login">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">账号：</label>
				<div class="col-sm-10">
					<input required="required" name="username" type="text" class="form-control" id="username" placeholder="账号" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-10">
					<input required="required" name="password" type="password" class="form-control" id="password" placeholder="密码" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">登陆</button>
				</div>
			</div>
		</form>
	</div>
	</body>
</html>