<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Dat script dung thu tu bootstrap.min.css -> jquery.min.js -> popper.min.js -> boostrap.min.js -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap-validate.js"></script>
<style>
header {
	padding: 1.75em;
}

article {
	background-color: #F3F6F9;
	font-size: 14px;
}

.bg-white {
	background-color: #FFFFFF;
}
</style>
</head>

<body class="container-flush">
	<header></header>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
		<div>
			<a class="navbar-brand" href="#">xPeter BearLoli</a>
		</div>
		<div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link text-light"
					data-lang="en" href="#">English</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					data-lang="vi" href="#">Vi</a></li>
			</ul>
		</div>
	</nav>
	<div class="row mb-2">
		<aside class="col-xl-2 pl-0 pr-0 bg-dark text-center">
			<div class="list-group list-group-flush  mb-2">
				<a href="dashboard.htm"
					class="list-group-item bg-success text-light font-weight-bold">Dashboard</a>
				<a href="staffManage.htm"
					class="list-group-item list-group-item-action bg-dark text-light">
					<s:message code="global.aside.staff" />
				</a> <a href="departManage.htm"
					class="list-group-item list-group-item-action bg-dark text-light"><s:message
						code="global.aside.depart" /> </a> 
						<a href="userManage.htm"
					class="list-group-item list-group-item-action bg-dark text-light">
					<s:message code="global.aside.user" />
				</a> <a href="#"
					class="list-group-item list-group-item-action bg-dark text-light dropdown-toggle collapsed"
					data-toggle="collapse" data-target="#collapse"><s:message
						code="global.aside.record" /> </a>
				<div class="collapse" id="collapse" style="font-size: 14px">
					<a
						class="list-group-item list-group-item-action bg-dark text-light"
						href="recordManage.htm?type=true"><s:message
							code="global.aside.record.achievement" /></a> <a
						class="list-group-item list-group-item-action bg-dark text-light"
						href="recordManage.htm?type=false"><s:message
							code="global.aside.record.discipline" /></a>
				</div>
				<a href="#"
					class="list-group-item list-group-item-action bg-dark text-light dropdown-toggle collapsed"
					data-toggle="collapse" data-target="#collapseStatistic"><s:message
						code="global.aside.statistic" /> </a>
				<div class="collapse" id="collapseStatistic" style="font-size: 14px">
					<a
						class="list-group-item list-group-item-action bg-dark text-light"
						href="statisticStaff.htm"><s:message
							code="global.aside.statistic.staff" /> </a> <a
						class="list-group-item list-group-item-action bg-dark text-light"
						href="statisticDepart.htm"><s:message
							code="global.aside.statistic.depart" /></a>
				</div>
			</div>
		</aside>
		<article class="col-xl-10 p-0">
			<jsp:include page="${param.view}"></jsp:include>
		</article>
	</div>

	<footer
		class="page-footer font-small special-color-dark pt-4 text-center"
		style="background-color: antiquewhite;">

		<h5>PS09070 - Vương Thế Minh Thăng</h5>

		<div class="container pt-2">
			<a class="fb-ic" href="https://www.facebook.com/peter.vuog"> <i
				class="fa fa-facebook-f fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
			</a> <a class="tw-ic"> <i
				class="fa fa-twitter fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
			</a> <a class="ins-ic"> <i
				class="fa fa-instagram fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
			</a> <a class="pin-ic"> <i
				class="fa fa-pinterest fa-lg white-text fa-2x"> </i>
			</a>
		</div>

		<div class="footer-copyright py-3">
			© 2020 Copyright: <b>xPeter</b>
		</div>

	</footer>
</body>
<script type="text/javascript">
	//function chuyen doi da ngon ngu
	$(function() {
		$("a[data-lang]").click(function() {
			var lang = $(this).attr("data-lang");
			$.get("dashboard.htm?language=" + lang, function() {
				location.reload();
			});
			return false;
		});
	});
</script>
</html>