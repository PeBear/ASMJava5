<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="navbar navbar-expand-sm bg-white">
	<div class="navbar-text navbar-title"
		style="color: rgba(70, 90, 110, 0.85); font-size: 1.3rem;">
		<b> <s:message code="statistic.depart.title" />
		</b>
	</div>
</div>

<div class="m-4 bg-white">
	<div>
		<table class="table table-bordered table-hover">
			<thead>
				<tr class="text-center">
					<th><s:message code="statistic.depart.column1" /></th>
					<th><s:message code="statistic.depart.column2" /></th>
					<th><s:message code="statistic.depart.column3" /></th>
					<th><s:message code="statistic.depart.column4" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty ListStatistic}">
					<c:forEach var="statistic" items="${ListStatistic}">
						<tr>
							<td>${statistic.name}</td>
							<td>${statistic.achievement}</td>
							<td>${statistic.discipline}</td>
							<td>${statistic.result}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>