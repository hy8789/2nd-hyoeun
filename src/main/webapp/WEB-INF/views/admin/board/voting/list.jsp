<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main>

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${list}">
				<tr>
					<td>${v.id}</td>
					<td><a href="voting/${v.id}">${v.title}</a></td>
					<td>${v.writerId}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${v.date}" />
					</td>
					<td>${v.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이지 넘버 -->
	<ul>
	<c:set var="page" value="${param.p}" /> 
	<c:set var="startNum" value="${page-((page-1)%5)}" /> 
	<c:set var="lastNum" value="${fn:substringBefore((count%10 == 0 ? count/10 : count/10 +1),'.')}" />

	p: ${page}
	s: ${startNum}
	l: ${lastNum}
	<c:if test="${startNum != 1}">
	   <a href="?p=${startNum != 1}">이전</a>
	</c:if>
	      
	      <c:forEach var="i" begin="0" end="4">   <!-- ${lastNum-1} -->
	      
	         <c:set var="strong" value="" />
	         <c:if test="${page == startNum+i }">
	            <c:set var="strong" value="text-strong" />
	         </c:if>
	
	         <c:if test="${startNum+i <= lastNum}">
	            <li><a class="${strong}" href="${ctx}/admin/board/voting/?p=${startNum+i}">${startNum+i}</a></li>
	         </c:if>
	
	         <!--    목록이 더이상 없으면 하이퍼링크 지움 -->
	         <c:if test="${startNum+i > lastNum}">
	            <li>${startNum+i}</li>
	         </c:if>
	         
	      </c:forEach>
	      
	<c:if test="${lastNum >= startNum+5 }">
	   <a href="?p=${startNum+5}">다음</a>
	</c:if>
</ul>
	
	<a href="voting/reg">등록하기</a>
</body>
</main>