<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 탈퇴폼</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>

	<!-- 외부 자바스크립트 파일 불러오기 -->
	<script src="<%=request.getContextPath() %>/member/member.js"></script>

</head>
<body>

<form method="post" action="<%=request.getContextPath() %>/Delete.do">
	<input type="hidden" name="id" value="${sessionScope.id }">
	<table border=1 width=500 align=center>
		<caption>회원 탈퇴</caption>
		<tr>
			<td>비밀번호</td>
			<td><input type=password id="passwd" name="passwd"></td>
		</tr>
		<tr>
			<td colspan=2 align=center>
				<input type=submit value="회원탈퇴">
				<input type=reset value="취소" onClick="history.go(-1)">
			</td>
		</tr>
	</table>
</form>


</body>
</html>