 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="MkWeb" prefix="mkw" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/mystyle.css" />
</head>
<body>
This is page1.jsp

<div>
	<mkw:get obj="list" rst="alpha">
		<h1>${alpha.user_SEQ} 번째 정보 </h1>
			<p> ${alpha.name} </p>
			${alpha.address}
	</mkw:get>
	
	<mkw:get obj="list" rst="beta">
		<h1>${beta.user_SEQ} 번째 정보 </h1>
			<p> ${beta.name} </p>
			${beta.address}
	</mkw:get>
</div>
</body>
</html>