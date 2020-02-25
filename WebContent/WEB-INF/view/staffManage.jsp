<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="navbar navbar-expand-sm bg-white">
	<div class="navbar-text navbar-title"
		style="color: rgba(70, 90, 110, 0.85); font-size: 1.3rem;">
		<b> <s:message code="home.staff.title" />
		</b>
	</div>
</div>

<div class="m-4 bg-white">
	<div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="text-center">
					<th><s:message code="home.staff.column1" /></th>
					<th><s:message code="home.staff.column2" /></th>
					<th><s:message code="home.staff.column3" /></th>
					<th><s:message code="home.staff.column4" /></th>
					<th><s:message code="home.staff.column5" /></th>
					<th><s:message code="home.staff.column6" /></th>
					<th><s:message code="home.staff.column7" /></th>
					<th><s:message code="home.staff.column8" /></th>
					<th><s:message code="home.staff.column9" /></th>
					<th colspan="2"><s:message code="home.staff.column10" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ListStaff}">
					<c:forEach var="staff" items="${ListStaff}">
						<tr>
							<td>${staff.staffId}</td>
							<td>${staff.name}</td>
							<td><c:if test="${staff.gender}">
									<s:message code="home.staff.male" />
								</c:if> <c:if test="${!staff.gender}">
									<s:message code="home.staff.female" />
								</c:if></td>
							<td>${staff.birthday}</td>
							<td>${staff.email}</td>
							<td>${staff.photo}</td>
							<td>${staff.phone}</td>
							<td><fmt:formatNumber value="${staff.salary}" /></td>
							<td>${staff.notes}</td>
							<td class="text-center">
								<button type="button" class="btn btn-primary"
									onclick="crunchifyAjax('${staff.staffId}')" data-toggle="modal"
									data-target="#insertModal">
									<img src='<c:url value="resources/icon/edit.png"/>'>
								</button>
							</td>
							<td class="text-center"><button type="button"
									class="btn btn-danger" data-toggle="modal"
									data-target="#deleteModal"
									onclick="getUserToDelete('${staff.staffId}')">
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
	<s:message code="home.staff.insert" />
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
					<form action="delete.htm" method="post">
						<input id="userToDeleteInput" type="hidden" value=""
							name="staffId" />
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
				<form:form modelAttribute="Staff" action="${action}"
					class="was-validated" method="post" id="staffForm" name="staffForm">
					<div class="form-group">
						<label for="staffId">StaffId</label>
						<form:input path="staffId" id="staffId" class="form-control"
							placeholder="Enter StaffId" required="required" />
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
					<div class="form-group">
						<label for="gender">Gender:</label>
						<form:radiobutton id="male" path="gender" value="True" />
						Male
						<form:radiobutton id="female" path="gender" value="False" />
						Female
					</div>

					<div class="form-group">
						<label for="birthday">Birthday ( MM/dd/yyyy ):</label>
						<form:input id="birthday" type="date" path="birthday"
							class="form-control" required="required" />
					</div>

					<div class="form-group">
						<label for="email">Email:</label>
						<form:input path="email" id="email" class="form-control"
							placeholder="Enter Email" required="required" />
					</div>

					<div class="form-group">
						<label for="phone">Phone:</label>
						<form:input path="phone" id="phone" class="form-control"
							placeholder="Enter Phone" required="required" />
					</div>
					<div class="form-group">
						<label for="salary">Salary:</label>
						<form:input path="salary" id="salary" class="form-control"
							placeholder="Enter Salary" />
					</div>

					<div class="form-group">
						<label for="notes">Notes:</label>
						<form:input path="notes" id="notes" class="form-control"
							placeholder="Enter Notes" />
					</div>

					<div class="form-group">
						<label for="photo">Photo:</label>
						<form:input path="photo" id="photo" class="form-control"
							placeholder="Enter Photo" value="unknow.png" />
					</div>
					<form:button class="btn btn-primary">Save</form:button>
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
		staffForm.reset()
		$('#staffForm').attr('action', 'insert.htm');
		$('#staffId').prop('readonly', false);
	}

	//get info staff to display update form
	function crunchifyAjax(staffId) {
		$('#staffForm').attr('action', 'update.htm');
		$('#staffId').prop('readonly', true);
		$.ajax({
			type : "GET",
			contentType : "application/json;",
			url : 'testAJAX.htm',
			data : {
				name : staffId,
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				$('#staffId').val(data.staffId);
				$('#name').val(data.name);
				if (data.gender) {
					$('#male').prop("checked", true);
				} else {
					$('#female').prop("checked", true);
				}
				$('#birthday').val(data.birthday);
				$('#email').val(data.email);
				$('#phone').val(data.phone);
				$('#salary').val(data.salary);
				$('#notes').val(data.notes);
			},
			error : function(data) {
				console.log(data);
			}
		});
	};
</script>
