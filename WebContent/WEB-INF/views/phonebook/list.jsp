<%@page import="com.bit.servlet.dao.PhoneBook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 확인</title>
</head>
<body>
	<h2>주소록 Servlet</h2>

    <!--  검색 필드 -->
    <form action="<c:url value="/phonebook"/>" method="POST">
		<input type="hidden" name="action" value="search" />
		<label for="keyword">검색</label>
		<input type="text" name="keyword" id="keyword" />
		<input type="submit" value="검색" />
	</form>
    <br/>
    
	<!-- 정보 테이블 -->
	<table border=1>
		<thead>
			<tr>
				<th>이름</th>
				<th>휴대전화</th>
				<th>전화번호</th>
				<th>도구</th>
			</tr>
		</thead>
		
    <c:forEach items="${ list }" var="vo">
		<tr>
			<td>${ vo.name }</td>
			<td>${ vo.hp }</td>
			<td>${ vo.tel }</td>
    		<!-- 삭제 버튼 -->
			<td>
				<form action="<c:url value="/phonebook"/>">
					<input type="hidden" name="a" value="delete" />
					<!-- 게시물의 id(PK) -->
					<input type="hidden" name="id" value="${ vo.id }"/>
					<!-- 전송 버튼 -->
					<input type="submit" value="삭제" />
				</form>
			</td>
		</tr>
	</c:forEach>
		
	</table>		
	
	<p>
		<a href="<c:url value="/phonebook?a=form" />">새주소추가</a>
	</p>
	
</body>
</html>