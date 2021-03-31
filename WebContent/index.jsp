<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 다른 페이지의 파일을 포함 -->
<!-- include: 별도의 서블릿이 아니고 본 JSP 내에 포함되어 컴파일 -->
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
	
	<h3>MVC model 2 미니 프로젝트</h3>
	<p>
		<a href="phonebook">폰북리스트</a>
	</p>

	
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>