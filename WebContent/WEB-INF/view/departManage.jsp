<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="navbar navbar-expand-sm bg-white">
	<div class="navbar-text navbar-title"
		style="color: rgba(70, 90, 110, 0.85); font-size: 1.3rem;">
		<b> <s:message code="home.depart.title" />
		</b>
	</div>
</div>

<div class="m-4 bg-white">
	<div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="text-center">
					<th><s:message code="home.depart.column1" /></th>
					<th><s:message code="home.depart.column2" /></th>
					<th colspan="2"><s:message code="home.depart.column3" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ListDepart}">
					<c:forEach var="depart" items="${ListDepart}">
						<tr>
							<td>${depart.departId}</td>
							<td>${depart.name}</td>
							<td class="text-center">
								<button type="button" class="btn btn-primary"
									onclick="crunchifyAjax('${depart.departId}')"
									data-toggle="modal" data-target="#insertModal">
									<img src='<c:url value="resources/icon/edit.png"/>'>
								</button>
							</td>
							<td class="text-center"><button type="button"
									class="btn btn-danger" data-toggle="modal"
									data-target="#deleteModal"
									onclick="getUserToDelete('${depart.departId}')">
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
	<s:message code="home.depart.insert" />
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
				<s:message code="home.staff.delete" />
				<b><span id="userDelete"></span></b>?
				<div class="text-right">
					<form action="departManage.htm" method="post">
						<input id="userToDeleteInput" type="hidden" name="departId" />
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
				<form:form modelAttribute="Depart" action="departManage.htm"
					class="was-validated" method="post" id="departForm"
					name="departForm">
					<div class="form-group">
						<label for="departId">DepartId</label>
						<form:input path="departId" id="departId" class="form-control"
							placeholder="Enter DepartId" required="required" />
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Please fill out this field.</div>
					</div>
					<div class="form-group">
						<label for="name">Name:</label>
						<form:input path="name" id="name" class="form-control"
							placeholder="Enter Name" required="required" />
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
		departForm.reset()
		//$('#departForm').attr('action', 'insert.htm');
		$('#btnSave').attr('name', 'insert');
		$('#departId').prop('readonly', false);
	}

	//get info staff to display update form
	function crunchifyAjax(departId) {
		var name = departId;
		$('#btnSave').attr('name', 'update');
		$('#departId').prop('readonly', true);
		$.ajax({
			type : "GET",
			contentType : "application/json;",
			url : 'departManage/ajaxDepart.htm',
			data : {
				departId : departId,
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				$('#departId').val(data.departId);
				$('#name').val(data.name);
			},
			error : function(data) {
				console.log(data);
			}
		});
	};
</script>