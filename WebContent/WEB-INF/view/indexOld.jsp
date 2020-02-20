<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>

<!-- Dat script dung thu tu bootstrap.min.css -> jquery.min.js -> popper.min.js -> boostrap.min.js -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:message code="global.company" />
	<a href="#" data-lang="en">English</a> |
	<a href="#" data-lang="vi">Vi</a>
	<div class="container">
		<div>
			<h2>${mess}</h2>
			<h3>
				<s:message code="home.employee.title" />
			</h3>
			<table class="table table-bordered table-hover">
				<thead>
					<tr class="text-center">
						<th><s:message code="home.employee.column1" /></th>
						<th><s:message code="home.employee.column2" /></th>
						<th><s:message code="home.employee.column3" /></th>
						<th><s:message code="home.employee.column4" /></th>
						<th colspan="2"><s:message code="home.employee.column5" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty ListStudent}">
						<c:forEach var="student" items="${ListStudent}">
							<tr>
								<td>${student.username}</td>
								<td>${student.password}</td>
								<td>${student.fullname}</td>
								<td>${student.email}</td>
								<td class="text-center">
									<form action="form.htm" method="post">
										<input type="hidden" value="${student.username}"
											name="username" />
										<button type="submit" class="btn btn-primary">
											<img src='<c:url value="resources/icon/edit.png"/>'>
										</button>
									</form>
								</td>
								<td class="text-center"><button type="button"
										class="btn btn-danger" data-toggle="modal"
										data-target="#deleteModal"
										onclick="getUserToDelete('${student.username}')">
										<img src='<c:url value="resources/icon/trashbin.png"/>'>
									</button></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<form action="form.htm" method="post">
			<input type="hidden" value="" name="username" />
			<!-- 			<button type="submit" class="btn btn-success">Insert</button> -->
			<button type="button" class="btn btn-success" data-toggle="modal"
				data-target="#insertModal">
				<s:message code="home.employee.insert" />
			</button>
		</form>
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-danger font-weight-bold">Delete
							Student</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						Do you want to delete <b><span id="userDelete"></span></b>
						student?
						<div class="text-right">
							<form action="delete.htm" method="post">
								<input id="userToDeleteInput" type="hidden" value=""
									name="username" />
								<button type="submit" class="btn btn-danger">Delete</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="insertModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Insert Student Form</h5>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<c:set var="action" value="insert.htm" />
						<c:if test="${isUpdate == true}">
							<c:set var="action" value="update.htm" />
						</c:if>
						<form:form modelAttribute="Student" action="${action}"
							method="post">
							<div class="form-group">
								<label for="username">Username:</label>
								<form:input path="username" id="username" class="form-control"
									placeholder="Enter username" />
							</div>
							<div class="form-group">
								<label for="password">Password:</label>
								<form:input path="password" id="password" class="form-control"
									placeholder="Enter password" />
							</div>
							<div class="form-group">
								<label for="fullname">Fullname:</label>
								<form:input path="fullname" id="fullname" class="form-control"
									placeholder="Enter fullname" />
							</div>
							<div class="form-group">
								<label for="email">Email:</label>
								<form:input path="email" id="email" class="form-control"
									placeholder="Enter email" />
							</div>
							<form:button class="btn btn-primary">Save</form:button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var user;
		function getUserToDelete(username) {
			user = username;
			console.log(user);
			document.getElementById("userDelete").innerHTML = username;
			document.getElementById("userToDeleteInput").setAttribute("value",
					user);
		}
		//function chuyen doi da ngon ngu
		$(function() {
			$("a[data-lang]").click(function() {
				var lang = $(this).attr("data-lang");
				$.get("index.htm?language=" + lang, function() {
					location.reload();
				});
				return false;
			});
		});
	</script>
</body>

</html>