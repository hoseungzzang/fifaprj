<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, 그리고 Bootstrap 기여자들">
<meta name="generator" content="Hugo 0.88.1">
<title>회원가입</title>

<link rel="canonical"
	href="https://getbootstrap.kr/docs/5.1/examples/sign-in/">



<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<!-- Favicons -->



<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link href="/css/login/signin.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
		<form:form action="signok" method="post" modelAttribute="signUpBean">
			<img src="../../images/emplogo.png" alt="" width="200" height="100">
			
			<div class="form-floating">
			<div class="fifaColor item">이름</div>
				<form:input path="userName" name="userName" class="form-control" id="floatingInputName" placeholder="홍길동"/>
				<form:errors path="userName" class="fifaErrorColor"/>
			</div>
			
			<div class="form-floating">
				<div class="fifaColor item">ID</div>
				<form:input path="userId" name="userId" type="ID" class="form-control" id="floatingInput" placeholder="wnghtmd99 or wnghtmd99@naver.com"/>
				<form:errors path="userId" class="fifaErrorColor"/>
				<div class="fifaErrorColor">${errorCode}</div>
			</div>
			
			<div class="form-floating">
				<div class="fifaColor item">Password</div>
				<form:input path="userPassword" name="userPassword" type="password" class="form-control" id="floatingPassword"
					placeholder="Password"/>
				<form:errors path="userPassword" class="fifaErrorColor"/>	
			</div>
			
			<div class="form-floating">
				<div class="fifaColor item">플레이어명</div>
				<form:select path="userPindex" name="userPindex" class="fifaSelectBox" style="font-size:14px;">
				<option selected value="999">플레이어명</option>
				<c:forEach items = "${getPlayerList}" var="player">
				<option value="${player.pindex}"><c:out value="${player.name}"></c:out></option>
				</c:forEach>
				</form:select>
				<form:errors path="userPindex" class="fifaErrorColor"/>	
			</div>
			
			<button class="fifaButton" type="submit">가입하기</button>
			<br>
			<button class="fifaButton"
				onclick="location.href='/index'; return false;">취소</button>

		</form:form>
	</main>
	


</body>
</html>
