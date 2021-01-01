<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<%
	String msg = null;
	if(request.getAttribute("success") != null) {
		msg = (String)request.getAttribute("success");
%>
		<script>
			alert('<%= msg %>');
			location.href='index.jsp';
		</script>
<%
	} else if(request.getAttribute("error") != null) {
		msg = (String)request.getAttribute("error");
%>
		<script>
			alert('<%= msg %>');
			location.href='member/movieList.jsp';
		</script>
<%
}

	ArrayList<Integer> list = new ArrayList<Integer>();
	int schNo = 0;

	if(request.getAttribute("list") != null) {
		list = (ArrayList<Integer>)request.getAttribute("list");
		schNo = (int)request.getAttribute("schNo");
	}
%>
<div class="seat-list">
	<form action="Reservation.do" method="post" id="seat-form">
<%
	if(list.size() != 0) {
		for(int i = 0; i < list.size(); i++) {
			for(int j = 1; j <= 20; j++) {
				if(list.get(i) == j) {
%>
					<div id="seat-item" class="disable">
						<%= j %> <input type="radio" name="seatNo" value="<%= j %>" disabled>
					</div>
<%
				} else {
%>
					<div id="seat-item" class="enable">
						<%= j %> <input type="radio" name="seatNo" value="<%= j %>">
					</div>
<%
				}
			}
		}
	} else {
		for(int i = 0; i < 20; i++) {
%>
			<div id="seat-item" class="enable">
				<%= i+1 %> <input type="radio" name="seatNo" value="<%= i+1 %>">
			</div>
<%
		}
	}
	MemberVO vo = (MemberVO)session.getAttribute("member");
%>
		<input type="hidden" name="id" value="<%= vo.getId() %>">
		<input type="hidden" name="schNo" value="<%= schNo %>">
		<div id="seat-select-form">
			<div id="seat-box">
				<input type="submit" value="예매">
			</div>
		</div>
	</form>
</div>
<jsp:include page="/footer.jsp"/>