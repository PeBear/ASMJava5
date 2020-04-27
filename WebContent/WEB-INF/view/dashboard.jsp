<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="navbar navbar-expand-sm bg-white">
	<div class="navbar-text navbar-title"
		style="color: rgba(70, 90, 110, 0.85); font-size: 1.3rem;">
		<b> <s:message code="home.dashboard.title" />
		</b>
	</div>
</div>

<div class="m-4 bg-white row">
	<c:if test="${not empty ListStaff}">
		<c:forEach var="staff" items="${ListStaff}">
			<div class="card m-3" style="width: 250px">
				<img class="card-img-top" src="resources/images/staff/${staff.photo}"
					alt="Card image" style="width: 100%">
				<div class="card-body">
					<h4 class="card-title">(${staff.staffId}) ${staff.name}</h4>
					<p class="card-text">${staff.departId.name}</p>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>