<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String view = request.getParameter("view");
	if (view.startsWith("login")) {
		pageContext.forward("login.jsp");
	} else {
		pageContext.forward("layout.jsp");
	}
%>