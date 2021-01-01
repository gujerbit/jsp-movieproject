<%@page import="vo.ScheduleVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<%
	ArrayList<ScheduleVO> list = null;
	String msg = null;
	
	if(request.getAttribute("list") != null) {
		list = (ArrayList<ScheduleVO>)request.getAttribute("list");
	} else if(request.getAttribute("error") != null) {
		msg = (String)request.getAttribute("error");
	}
%>
<div class="time-list">
	<div id="time-title">
		<%= list.get(0).getMovieName() %>
	</div>
	<table border="1">
		<tr>
			<th>제목</th> <th>상영 일자</th> <th>상영관</th> <th>잔여 좌석</th>
		</tr>
<%
	for(ScheduleVO vo : list) {
%>
		<tr>
			<td><a href="SeatSelect.do?schNo=<%= vo.getSchNo() %>"><%= vo.getMovieName() %></a></td> <td><%= vo.getRunDay() %></td> <td><%= vo.getRoomNo() %></td> <td><%= 20 - vo.getSeatCnt() %>/20</td>
		</tr>
<%
	}
%>
	</table>
</div>
<jsp:include page="/footer.jsp"/>