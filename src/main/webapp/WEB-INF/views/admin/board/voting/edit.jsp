<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<h2>수정 페이지</h2>
	<form action="../edit" method="post">
		<fieldset>
			<legend>voting 수정정보 필드</legend>
			<table border="1">
				<tbody>
					<tr>
						<td>제목</td>
						<td colspan="3"><input type="text" name="title" value="${v.title}" /></td>
					</tr>
					<tr>
						<td>작성일</td>
						<td colspan="3">${v.date}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${v.writerId}</td>
						<td>조회수</td>
						<td>${v.hit}</td>
						<td>글번호</td>
						<td>${v.id}</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td colspan="3">
						<c:forEach var="f" items="${files}">
							<span>${f.src}</span>
							<a href="voting-file-del?code=${f.code}&ncode=${v.code}">[X]</a>
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td colspan="4"><textarea name="content" rows="20" cols="60">${v.content}</textarea>

						</td>
					</tr>
				</tbody>
			</table>
			<div>				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" name="code" value="${v.code}" />
				<input type="submit" value="저장" />
				<a href="voting-detail?code=${v.code}">취소</a>				
			</div>
		</fieldset>
	</form>





