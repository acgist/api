<!DOCTYPE HTML>
<html>
	<head>
		<title>系统错误</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width" />
		<meta name="keywords" content="系统错误" />
		<meta name="description" content="系统错误" />
		
		<link rel="shortcut icon" href="/favicon.ico">
		<#include "/include/resources.ftl">
	</head>

	<body>
		<div class="error">
			<p>系统错误</p>
			<p>错误代码：${code}</p>
			<p>错误描述：${message}</p>
			<p>系统时间：${.now?string("yyyy-MM-dd HH:mm:ss")}</p>
		</div>
	</body>
</html>