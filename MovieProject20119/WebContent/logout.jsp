<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberVO vo = null;
	String[] arr = {"admin", "member"};
	int logout = 0;
	
	if(request.getParameter("logout") != null) {
		logout = Integer.parseInt(request.getParameter("logout"));
	}
	
	for(int i = 0; i < 2; i++) {
		if(session.getAttribute(arr[i]) != null) {
			vo = (MemberVO)session.getAttribute(arr[i]);
		}
	}
	
	if(vo != null && logout == 0) {
%>
		<script>
			var logout = confirm('<%= vo.getId() %>님 정말 로그아웃 하시겠습니까?');
			
			if(logout) {
				location.href='logout.jsp?logout=1';
			} else {
				location.href='index.jsp';
			}
		</script>
<%
	} else if(logout == 1) {
		session.invalidate();
%>
		<script>
			alert('로그아웃 성공!');
			location.href='login.jsp';
		</script>
<%
	}
%>
</body>
</html>