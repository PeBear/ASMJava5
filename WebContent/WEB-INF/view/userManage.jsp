<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="navbar navbar-expand-sm bg-white">
	<div class="navbar-text navbar-title"
		style="color: rgba(70, 90, 110, 0.85); font-size: 1.3rem;">
		<b> <s:message code="home.user.title" />
		</b>
	</div>
</div>

<div class="m-4 bg-white">
	<div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="text-center">
					<th><s:message code="home.user.column1" /></th>
					<th><s:message code="home.user.column2" /></th>
					<th><s:message code="home.user.column3" /></th>
					<th colspan="2"><s:message code="home.user.column4" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ListUser}">
					<c:forEach var="user" items="${ListUser}">
						<tr>
							<td>${user.username}</td>
							<td>${user.password}</td>
							<td>${user.fullname}</td>
							<td class="text-center">
								<button type="button" class="btn btn-primary"
									onclick="crunchifyAjax('${user.username}')" data-toggle="modal"
									data-target="#insertModal">
									<img src='<c:url value="resources/icon/edit.png"/>'>
								</button>
							</td>
							<td class="text-center"><button type="button"
									class="btn btn-danger" data-toggle="modal"
									data-target="#deleteModal"
									onclick="getUserToDelete('${user.username}')">
									<img src='<c:url value="resources/icon/trashbin.png"/>'>
								</button></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>

<button type="button" class="btn btn-success" onclick="setInsertForm();"
	data-toggle="modal" data-target="#insertModal">
	<s:message code="home.user.insert" />
</button>

<div id="deleteModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title text-danger font-weight-bold">Delete
					Student</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<s:message code="home.user.delete" />
				<b><span id="userDelete"></span></b>?
				<div class="text-right">
					<form action="userManage.htm" method="post">
						<input id="userToDeleteInput" type="hidden" name="username" />
						<button type="submit" class="btn btn-danger" name="delete">Delete</button>
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
				<form:form modelAttribute="User" action="userManage.htm"
					class="was-validated" method="post" id="userForm" name="userForm">
					<div class="form-group">
						<label for="username">Username:</label>
						<form:input path="username" id="username" class="form-control"
							placeholder="Enter Username" required="required" />
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Please fill out this field.</div>
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<form:input path="password" id="password" class="form-control"
							placeholder="Enter Password" required="required" />
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Please fill out this field.</div>
					</div>
					<div class="form-group">
						<label for="fullname">Fullname:</label>
						<form:input path="fullname" id="fullname" class="form-control"
							placeholder="Enter Fullname" required="required" />
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Please fill out this field.</div>
					</div>
					<form:button class="btn btn-primary" id="btnSave">Save</form:button>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script>
	var user;
	//get staffId to delete
	function getUserToDelete(username) {
		user = username;
		console.log(user);
		document.getElementById("userDelete").innerHTML = username;
		document.getElementById("userToDeleteInput")
				.setAttribute("value", user);
	}

	//reset insertForm and $action = insert.htm
	function setInsertForm() {
		userForm.reset();
		//$('#departForm').attr('action', 'insert.htm');
		$('#btnSave').attr('name', 'insert');
		$('#username').prop('readonly', false);
	}

	//get info staff to display update form
	function crunchifyAjax(username) {
		$('#btnSave').attr('name', 'update');
		$('#username').prop('readonly', true);
		$.ajax({
			type : "GET",
			contentType : "application/json;",
			url : 'userManage/ajaxUser.htm',
			data : {
				username : username,
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				$('#username').val(data.username);
				$('#password').val(data.password);
				$('#fullname').val(data.fullname);
			},
			error : function(data) {
				console.log(data);
			}
		});
	};

	
</script>