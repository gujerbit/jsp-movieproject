<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%
	if(request.getAttribute("login") != null) {
		MemberVO vo = (MemberVO)request.getAttribute("login");
		
		if(vo.getId().equals("admin")) {
			session.setAttribute("admin", vo);
		} else {
			session.setAttribute("member", vo);
		}
%>
		<script>
			alert('<%= vo.getId() %>님 로그인 성공!');
			location.href='index.jsp';
		</script>
<%
	} else if(request.getAttribute("error") != null) {
		String msg = (String)request.getAttribute("error");
%>
		<script>
			alert('<%= msg %>');
			location.href='#';
		</script>
<%
	}
%>
<div class="login">
	<form action="Login.do" method="post">
		<p>
			LOGIN
		</p>
		<hr>
		<p>
			<span>아이디</span> <span>|</span> <input type="text" name="id" required>
		</p>
		<p>
			<span>비밀번호</span> <span>|</span> <input type="password" name="pw" required>
		</p>
		<hr>
		<div id="login-form">
			<input type="submit" value="로그인"> <input type="reset" value="다시 입력">
		</div>
	</form>
	<div id="login-to-register">
		<a href="register.jsp">혹시 YGV 계정이 없으신가요?</a><br>
		<a href="#">아이디를 잊으셨나요?</a><br>
		<a href="#">비밀번호를 잊으셨나요?</a>
	</div>
</div>
<jsp:include page="footer.jsp"/>