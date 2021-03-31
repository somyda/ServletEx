<%@page import="com.bit.servlet.dao.PhoneBook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 확인</title>
</head>
<body>
	<h2>주소록 Servlet</h2>
	<c:forEach items="${ list }" var="vo">

		<!-- 정보 테이블 -->
	<table border=1>
		<tr>
			<th>Id</th>
			<td>${ vo.id }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${ vo.firstName }</td>
		</tr>
		<tr>
			<th>휴대전화</th>
			<td>${ vo.hp }</td>
		</tr>
		<tr>
			<th>집전화</th>
			<td>${ vo.tel }</td>
		</tr>
		<!-- 삭제 버튼 -->
		<tr>
			<td colspan="2">
				<form action="<c:url value="/el"/>">
					<input type="hidden" name="a" value="delete" />
					<!-- 게시물의 no(PK) -->
					<input type="hidden" name="no" value="${ vo.no }"/>
					<!-- 전송 버튼 -->
					<input type="submit" value="삭제" />
				</form>
				<!-- TODO: 수정 기능을 구현해보기 -->
			</td>
		</tr>
	</table>		
	
	</c:forEach>
	
	<p>
		<a href="<c:url value="/el?a=form" />">메일링 리스트 가입(MVC)</a>
	</p>
	
</body>
</html>