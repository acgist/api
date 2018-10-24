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
			<form method="post" action="/order">
				<p>用户名：<input name="orderId" placeholder="用户名" type="text" /></p>
				<p><input type="submit" value="提交" /></p>
			</form>
		</div>
	</body>
</html>