<!DOCTYPE HTML>
<html>
	<head>
		<title>用户登录</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width" />
		<meta name="keywords" content="用户登录" />
		<meta name="description" content="用户登录" />
		
		<link rel="shortcut icon" href="/favicon.ico">
		<#include "/include/resources.ftl">
	</head>

	<body>
		<div class="order">
			<form method="post" action="/user/login">
				<p>账号：<input name="username" placeholder="账号" type="text" /></p>
				<p>密码：<input name="password" placeholder="密码" type="text" /></p>
				<p><input type="submit" value="提交" /></p>
			</form>
		</div>
	</body>
</html>