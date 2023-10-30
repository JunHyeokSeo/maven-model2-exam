<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
	<title>MVC 게시판</title>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	
	<script src="<%=request.getContextPath() %>/board/script.js"></script>
</head>
<body>

<form action="<%=request.getContextPath() %>/BoardAddAction.do" method="post" 
	  enctype="multipart/form-data">
<table cellpadding="0" cellspacing="0" align=center border=1>
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">작성자</div>
		</td>
		<td>
			<input type="text" name="board_name" id="board_name" size="10" maxlength="10">
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input type="password" name="board_pass" id="board_pass" size="10" maxlength="10"> 
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목</div>
		</td>
		<td>
			<input type="text" name="board_subject" id="board_subject" size="50" maxlength="100"> 
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="board_content" id="board_content" cols="67" rows="15"></textarea>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			<input type="file" name="board_file">
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">			
			
			<input type=submit value="등록">
			
			<input type=reset value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>