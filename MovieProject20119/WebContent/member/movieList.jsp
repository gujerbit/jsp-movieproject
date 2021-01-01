<%@page import="vo.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<%
	if(request.getAttribute("error") != null) {
		String msg = (String)request.getAttribute("error");
%>
		<script>
			alert('<%= msg %>');
			location.href='index.jsp';
		</script>
<%
	} else if(request.getAttribute("list") != null) {
		ArrayList<MovieVO> list = (ArrayList<MovieVO>)request.getAttribute("list");
		int idx = 0;
%>
		<div class="movie-box">
<%
		for(MovieVO vo : list) {
			idx++;
%>
			<div id="movies">
				<div id="rank">
					NO.<%= idx %>
				</div>
				<img src="images/<%= vo.getImg() %>">
				<div id="movie-info">
					<p><%= vo.getMovieName() %></p>
					<p><%= vo.getCategory() %> | <%= vo.getRuntime() %>분</p>
				</div>
				<a href="TimeSelect.do?movieName=<%= vo.getMovieName() %>">예매</a>
			</div>
<%
		}
	}
%>
		</div>
<jsp:include page="/footer.jsp"/>