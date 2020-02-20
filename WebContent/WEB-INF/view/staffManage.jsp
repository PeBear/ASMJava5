<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<s:message code="global.company" />
<a href="#" data-lang="en">English</a>
<a href="#" data-lang="vi">Vi</a>
<button type="button" onclick="crunchifyAjax()">AJAX</button>
<button type="button" onclick="changeText()">AJAX Text</button>
<div class="container">
	<div>
		<h2>${mess}</h2>
		<h3>
			<s:message code="home.staff.title" />
		</h3>
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
							<td>${staff.salary}</td>
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
	<form action="form.htm" method="post">
		<input type="hidden" value="" name="staffId" />
		<!-- 			<button type="submit" class="btn btn-success">Insert</button> -->
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#insertModal">
			<s:message code="home.staff.insert" />
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
					<s:message code="home.staff.delete" />
					<b><span id="userDelete"></span></b>?
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
					<form:form modelAttribute="Staff" action="${action}" method="post">
						<div class="form-group">
							<label for="staffId">StaffId</label>
							<form:input path="staffId" id="staffId" class="form-control"
								placeholder="Enter StaffId" />
						</div>
						<div class="form-group">
							<label for="name">Name:</label>
							<form:input path="name" id="name" class="form-control"
								placeholder="Enter Name" />
						</div>
						<div class="form-group">
							<label for="gender">Gender:</label>
							<form:radiobutton id="male" path="gender" value="True" />
							Male
							<form:radiobutton id="female" path="gender" value="False" />
							Female
						</div>

						<div class="form-group">
							<label for="birthday">Birthday:</label>
							<form:input id="birthday" path="birthday" class="form-control" />
						</div>
						<div class="form-group">
							<label for="email">Email:</label>
							<form:input path="email" id="email" class="form-control"
								placeholder="Enter Email" />
						</div>
						<div class="form-group">
							<label for="phone">Phone:</label>
							<form:input path="phone" id="phone" class="form-control"
								placeholder="Enter Phone" />
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
						<form:button class="btn btn-primary">Save</form:button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="result"></div>
<script>
	var user;
	$(function() {
		$("#birthday").datepicker({
			dateFormat : 'mm/dd/yy'
		});
	});
	function getUserToDelete(username) {
		user = username;
		console.log(user);
		document.getElementById("userDelete").innerHTML = username;
		document.getElementById("userToDeleteInput")
				.setAttribute("value", user);
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

	function crunchifyAjax(staffId) {
		var name = staffId;
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : 'testAJAX.htm',
			data : {
				name : name,
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

	function changeText() {
		$('#result').html("hello AJAX");
	}
</script>
