<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="navbar navbar-expand-sm bg-white">
	<div class="navbar-text navbar-title"
		style="color: rgba(70, 90, 110, 0.85); font-size: 1.3rem;">
		<b> <s:message code="home.record.title" /> <c:if
				test="${RecordType}">
				<s:message code="home.record.achievement" />
			</c:if> <c:if test="${RecordType == false}">
				<s:message code="home.record.discipline" />
			</c:if>
		</b>
	</div>
</div>

<div class="m-4 bg-white">
	<div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="text-center">
					<th><s:message code="home.record.column1" /></th>
					<th><s:message code="home.record.column2" /></th>
					<th><s:message code="home.record.column3" /></th>
					<th><s:message code="home.record.column4" /></th>
					<th><s:message code="home.record.column5" /></th>
					<th colspan="2"><s:message code="home.record.column6" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ListRecord}">
					<c:forEach var="record" items="${ListRecord}">
						<tr>
							<td>${record.recordId}</td>
							<td><c:if test="${record.type}">
									<s:message code="home.record.achievement" />
								</c:if> <c:if test="${record.type == false}">
									<s:message code="home.record.discipline" />
								</c:if></td>
							<td>${record.reason}</td>
							<td>${record.date}</td>
							<td>${record.staff.name}</td>
							<td class="text-center">
								<button type="button" class="btn btn-primary"
									onclick="crunchifyAjax('${record.recordId}')"
									data-toggle="modal" data-target="#insertModal">
									<img src='<c:url value="resources/icon/edit.png"/>'>
								</button>
							</td>
							<td class="text-center"><button type="button"
									class="btn btn-danger" data-toggle="modal"
									data-target="#deleteModal"
									onclick="getUserToDelete('${record.recordId}')">
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
	<c:if test="${RecordType}">
		<s:message code="home.record.insertAchievement" />
	</c:if>
	<c:if test="${RecordType == false}">
		<s:message code="home.record.insertDiscipline" />
	</c:if>
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
				<s:message code="home.record.delete" />
				<b><span id="userDelete"></span></b>?
				<div class="text-right">
					<form action="recordManage.htm" method="post">
						<input id="userToDeleteInput" type="hidden" name="recordId" />
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
				<h5 class="modal-title">Insert Record Form</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<form action="recordManage.htm" class="was-validated" method="post"
					id="recordForm" name="recordForm">
					
					<div class="form-group">
						<label for="recordId">RecordId:</label> <input id="recordId"
							name="recordId" class="form-control" required="required" readonly/>
					</div>
					
					<div class="form-group">
						<label for="staffId">StaffId:</label> <input id="staffId"
							name="staffId" class="form-control" required="required" />
					</div>
					<div class="form-group">
						<label for="date">Date ( MM/dd/yyyy ):</label> <input id="date"
							type="date" name="date" class="form-control" required="required" />
					</div>

					<div class="form-group">
						<label for="reason">Reason:</label> <input name="reason"
							id="reason" class="form-control" placeholder="Enter Reason"
							required="required" />
						<div class="valid-feedback">Valid.</div>
						<div class="invalid-feedback">Please fill out this field.</div>
					</div>
					<button class="btn btn-primary" id="btnSave" type="submit"
						name="insert">Save</button>
				</form>
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
		recordForm.reset()
		$('#btnSave').attr('name', 'insert');
		$('#recordId').val("Auto identity");
		$('#staffId').prop("readonly", false);
	}

	//get info staff to display update form
	function crunchifyAjax(recordId) {
		$('#btnSave').attr('name', 'update');
		$('#staffId').prop("readonly", true);
		$.ajax({
			type : "GET",
			contentType : "application/json;",
			url : 'recordManage/ajaxRecord.htm',
			data : {
				recordId : recordId,
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				$('#recordId').val(data.recordId);
				$('#staffId').val(data.staff.staffId);
				$('#date').val(data.date);
				$('#reason').val(data.reason);
			},
			error : function(data) {
				console.log(data);
			}
		});
	};

	
</script>