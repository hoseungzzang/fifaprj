<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="ko">
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, 그리고 Bootstrap 기여자들">
<meta name="generator" content="Hugo 0.88.1">
<title>로그인</title>

<link rel="canonical"
	href="https://getbootstrap.kr/docs/5.1/examples/sign-in/">



<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<script type="text/javascript" src="/js/login/signCheck.js"></script>
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
<script>

</script>

<!-- Custom styles for this template -->
<link href="/css/login/signin.css" rel="stylesheet">
</head>
<body class="text-center">

	<main class="form-signin">
		<form name="form1" method="post">
			<img class="mb-4" src="../../images/emplogo.png" alt="" width="200"
				height="100">

			<div class="form-floating">
				<div class="fifaColor item">ID</div>
				<input name="userId" type="ID" class="form-control"
					id="floatingInput" placeholder="wnghtmd99 or wnghtmd99@naver.com">

			</div>
			<div class="form-floating">
				<div class="fifaColor item">Password</div>
				<input name="userPassword" type="password" class="form-control"
					id="floatingPassword" placeholder="Password">
			</div>
			<div class="fifaErrorColor">${errorCode}</div>
			<button class="fifaButton" id="btnLogin">로그인</button>

			<button class="fifaButton"
				onclick="location.href='/login/signup'; return false;">회원가입</button>

		</form>
	</main>



</body>
</html>
