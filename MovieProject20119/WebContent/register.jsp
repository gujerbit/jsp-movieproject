<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%
	String msg = null;

	if(request.getAttribute("register") != null) {
		msg = (String)request.getAttribute("register");
%>
		<script>
			alert('<%= msg %>');
			location.href='login.jsp';
		</script>
<%
	} else if(request.getAttribute("error") != null) {
		msg = (String)request.getAttribute("error");
%>	
		<script>
			alert('<%= msg %>');
			location.href='#';
		</script>
<%
	}
%>
<div class="register">
	<form action="Register.do" method="post">
		<p>
			REGISTER
		</p>
		<hr>
		<p>
			<span>아이디</span> <span>|</span> <span><input type="text" name="id" required></span>
		</p>
		<p>
			<span>비밀번호</span> <span>|</span> <span><input type="password" name="pw" required></span>
		</p>
		<p>
			<span>이메일</span> <span>|</span> <span><input type="email" name="email" placeholder="ex) yy_1910218@y-y.hs.kr" required></span>
		</p>
		<p>
			<span>전화번호</span> <span>|</span> <span><input type="tel" name="tel" placeholder="ex) 01012345678" required></span>
		</p>
		<p>
			<span>생년월일</span> <span>|</span> 
			<span>
				<select name="year" required>
<%
	for(int i = 2020; i >= 1900; i--) {
%>
					<option value="<%= i %>"><%= i %></option>
<%
	}
%>
				</select>
				<select name="month" id="month" required>
<%
	for(int i = 1; i <= 12; i++) {
%>
					<option value="<%= i %>"><%= i %></option>
<%
	}
%>
				</select>
				<select name="day" id="day" required>
<%
	for(int i = 1; i <= 31; i++) {
%>
					<option value="<%= i %>"><%= i %></option>
<%
	}
%>
				</select>
			</span>
		</p>
		<hr>
		<div id="register-form">
			<input type="submit" value="회원가입"> <input type="reset" value="다시 입력">
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"/>