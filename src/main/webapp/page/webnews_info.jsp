<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=2,user-scalable=yes" />
		<title>公共详情页</title>
	</head>
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
		}
		
		html,
		body,
		.main {
			height: 100%;
			background: #FFFFFF;
		}
		
		img {
			height: auto;
			margin: 10px 0;
		}
		
		.title {
			font-family: PingFangSC-Regular;
			font-size: 18px;
			color: #333333;
			padding: 10px 15px;
		}
		
		.time {
			font-family: PingFangSC-Regular;
			font-size: 14px;
			color: #666666;
			padding: 0 15px 10px;
		}
		
		.content {
			padding-bottom: 10px;
		}
		
		.content p {
			font-family: PingFangSC-Regular;
			font-size: 14px;
			color: #333333;
			padding: 0px 15px 0px 15px;
		}
	</style>

	<body>
		<div class="main">
			<div class="title">
				<%=request.getAttribute("title") %>
			</div>
			<div class="time">
				<%=request.getAttribute("time") %>
			</div>
			<div class="content">
				<%=request.getAttribute("content") %>
			</div>
		</div>
	</body>

</html>