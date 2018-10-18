<!DOCTYPE HTML>
<html>
	<head>
		<title>交易页面</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width" />
		<meta name="keywords" content="异常页面" />
		<meta name="description" content="异常页面" />
		
		<link rel="shortcut icon" href="/favicon.ico">
		<#include "/include/resources.ftl">
	</head>

	<body>
		<div class="error">
			<p>系统异常</p>
			<p>异常代码：${code}</p>
			<p>异常描述：${message}</p>
			<p>系统时间：${date?string("yyyy-MM-dd HH:mm:ss")}</p>
		</div>
	</body>
</html>