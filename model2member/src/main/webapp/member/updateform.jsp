<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 수정</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>

    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
        function openDaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                    document.getElementById('post').value = data.zonecode;
                    document.getElementById('address').value = data.address;
                }
            }).open();
        }
    </script>


    <!-- 외부 자바스크립트 파일 불러오기 -->
    <script src="<%=request.getContextPath() %>/member/member.js"></script>
    <script>
        $(function () {
            //핸드폰 앞자리 선택
            $("#phone1 option[value='${member.phone1}']").attr("selected", true)

            //이메일 드롭박스 선택
            $("#email option[value='${member.domain}']").attr("selected", true)

            //성별 선택
            $("input:radio[name='gender'][value='${member.gender}']").attr("checked", true);

            //취미선택
            <c:forEach var="hobby" items="${h}">
            $("input:checkbox[name='hobby'][value='${hobby}']").attr("checked", true);
            </c:forEach>
        });
    </script>

</head>
<body>

<form method="post" action="<%=request.getContextPath() %>/Update.do">
    <input type="hidden" name="id" value="${member.id}">
    <table border=1 width=500 align=center>
        <caption>회원 수정</caption>
        <tr>
            <td>ID</td>
            <td>
                ${member.id }
            </td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type=password id="passwd" name="passwd"></td>
        </tr>
        <tr>
            <td>성명</td>
            <td>
                <input type=text id="name" name="name" value="${member.name}">
            </td>
        </tr>
        <tr>
            <td>주민번호</td>
            <td><input type=text size=6 maxlength=6 id="jumin1" name="jumin1" value="${member.jumin1}">-
                <input type=text size=7 maxlength=7 id="jumin2" name="jumin2" value="${member.jumin2}">
            </td>
        </tr>
        <tr>
            <td>E-Mail</td>
            <td><input type=text size=10 id="mailid" name="mailid" value="${member.mailid}">@
                <input type=text size=10 id="domain" name="domain" value="${member.domain}">
                <select id="email" name="email">
                    <option value="">직접입력</option>
                    <option value="naver.com">네이버</option>
                    <option value="daum.net">다음</option>
                    <option value="nate.com">네이트</option>
                    <option value="gmail.com">gmail</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>전화번호</td>
            <td><input type=text size=4 id="tel1" name="tel1" maxlength=4 value="${member.tel1}">-
                <input type=text size=4 id="tel2" name="tel2" maxlength=4 value="${member.tel2}">-
                <input type=text size=4 id="tel3" name="tel3" maxlength=4 value="${member.tel3}">
            </td>
        </tr>
        <tr>
            <td>핸드폰</td>
            <td><select id="phone1" name="phone1">
                <option value="">번호선택</option>
                <option value="010">010</option>
                <option value="011">011</option>
                <option value="016">016</option>
                <option value="018">018</option>
                <option value="019">019</option>
            </select>-
                <input type=text size=4 id="phone2" name="phone2" maxlength=4 value="${member.phone2}">-
                <input type=text size=4 id="phone3" name="phone3" maxlength=4 value="${member.phone3}">
            </td>
        </tr>
        <tr>
            <td>우편번호</td>
            <td><input type=text size=5 id="post" name="post" value="${member.post}">
                <input type=button value="우편번호검색"
                       onClick="openDaumPostcode()">
            </td>
        </tr>
        <tr>
            <td>주소</td>
            <td><input type=text size=45 id="address" name="address" value="${member.address}"></td>
        </tr>
        <tr>
            <td>성별</td>
            <td>
                <input type=radio id="male" name="gender" value="남자">남자
                <input type=radio id="female" name="gender" value="여자">여자
            </td>
        </tr>
        <tr>
            <td>취미</td>
            <td>
                <input type="checkbox" id="h1" name="hobby" value="공부">공부
                <input type="checkbox" id="h2" name="hobby" value="게임">게임
                <input type="checkbox" id="h3" name="hobby" value="등산">등산
                <input type="checkbox" id="h4" name="hobby" value="낚시">낚시
                <input type="checkbox" id="h5" name="hobby" value="쇼핑">쇼핑
            </td>
        </tr>
        <tr>
            <td>자기소개</td>
            <td>
                <textarea id="intro" name="intro" rows="5" cols="50"
                          placeholder="자기소개를 100자 이내로 입력하세요">${member.intro}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan=2 align=center>
                <input type=submit value="회원수정">
                <input type=reset value="취소" onclick="history.go(-1)">
            </td>
        </tr>
    </table>
</form>


</body>
</html>