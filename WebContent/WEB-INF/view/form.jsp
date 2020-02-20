<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3>Form Student</h3>
		<c:set var="action" value="insert.htm"/>
		<c:if test="${isUpdate == true}"> <c:set var="action" value="update.htm"/></c:if>
		<form:form modelAttribute="Student" action="${action}" method="post">
			<div class="form-group">
				<label for="username">Username:</label>
				<form:input path="username" id="username" class="form-control"  placeholder="Enter username"/>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<form:input path="password" id="password" class="form-control" placeholder="Enter password"/>
			</div>
			<div class="form-group">
				<label for="fullname">Fullname:</label>
				<form:input path="fullname" id="fullname" class="form-control" placeholder="Enter fullname"/>
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<form:input path="email" id="email" class="form-control" placeholder="Enter email"/>
			</div>
			<form:button class="btn btn-primary">Save</form:button>
		</form:form>
	</div>
</body>
</html>