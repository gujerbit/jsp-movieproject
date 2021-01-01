<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="#">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/app.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="wrap">
	<header>
<%
	int type = 0; //0 -> logout / 1 -> admin / 2 -> member
	MemberVO memberVO = null;
	
	if(session.getAttribute("admin") != null) {
		type = 1;
		memberVO = (MemberVO)session.getAttribute("admin");
	} else if(session.getAttribute("member") != null) {
		type = 2;
		memberVO = (MemberVO)session.getAttribute("member");
	}

	if(type != 0) {
%>
		<div class="member-box">
			<span><%= memberVO.getId() %>님</span> |
			<a href="">내정보</a> |
			<a href="logout.jsp">로그아웃</a>
		</div>
<%
	} else {
%>
		<div class="member-box">
			<a href="login.jsp">로그인</a>|
			<a href="register.jsp">회원가입</a>
		</div>
<%
	}
%>
		<div class="menu-box">
			<div id="menu-logo">
				<a href="index.jsp">YGV</a>
<%
	if(request.getServletPath().contains("movieList.jsp")) {
%>
				<img src="https://img.cgv.co.kr/R2014/images/title/h2_movie.png">
<%
	} else {
%>
				<img src="https://img.cgv.co.kr/R2014/images/title/h2_cultureplex.png">
<%
	}
%>
			</div>
<%
	if(type == 1) {
%>
			<div id="menu">
			</div>
<%
	} else if(type == 2) {
%>
			<div id="menu">
				<a href="MovieList.do">영화</a>
				<a href="RoomCount.do">예매</a>
			</div>
<%
	}
%>
		</div>
		<hr>
	</header>