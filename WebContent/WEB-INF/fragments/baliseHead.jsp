<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css">

<title>${param.titre}</title>

</head>

<body>
	<div id="bandeau">
		<img alt="ENI-Enchères"
			src="<%=request.getContextPath()%>/images/enchere.png"><br><br><br>
		<h1 class="titre">&nbsp;&nbsp;ENI-Enchères</h1>
	</div>