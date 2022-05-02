<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, 그리고 Bootstrap 기여자들">
<meta name="generator" content="Hugo 0.88.1">
<title>전적등록</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<link rel="canonical"
	href="https://getbootstrap.kr/docs/5.1/examples/sign-in/">



<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<script type="text/javascript" src="/js/main/writeCheck.js"></script>
<script type="text/javascript" src="/js/main/writeSelect.js"></script>
<!-- Favicons -->





<!-- Custom styles for this template -->
<link href="/css/main/mainWrite.css" rel="stylesheet">
</head>
<body class="text-center">



	<main class="form-signin">
		<form name="form1" method="post" >
			<img src="../../images/emplogo.png" alt="" width="200" height="100">
			<div class="form-floating" id="inputForm">

				<select name="vsWriter" id="vsWriter" class="fifaSelectBox"
					style="font-size: 14px;margin-left:100px;">
					<option selected value="null">플레이어1</option>
					<c:forEach items="${getPlayerList}" var="player">
						<option value="${player.name}"><c:out
								value="${player.name}"></c:out></option>
					</c:forEach>
				</select> 
				<input name="vsWriterScore" class="form-control-custom" id="vsWriterScore" placeholder="점수입력" /> 
					<span>VS</span> 
					<input
					name="vsOpponentScore" class="form-control-custom" id="vsOpponentScore"
					placeholder="점수입력" /> 
					<select name="vsOpponent" class="fifaSelectBox" style="font-size: 14px;" id="vsOpponent">
					<option selected value="null">플레이어2</option>
					<c:forEach items="${getPlayerList}" var="player">
						<option value="${player.name}"><c:out
								value="${player.name}"></c:out></option>
					</c:forEach>
				</select>
				<select name="vsMatch" class="fifaSelectBox"
					style="font-size: 14px; width:100px;" id="vsMatch">
				<option selected value="null">대결종류</option>
				<option value="fMatch">친선전</option>
				<option value="lMatch">리그전</option>
				</select>
			</div>
			<div id="field"></div>
			<input type="button" class="fifaButton" onclick="addForm();" value="입력 폼 추가하기">
			<br>
			<input type="button" class="fifaButton" onclick="removeForm();" value="입력 폼 삭제하기">
			<br>
			<button class="fifaButton" id="btnLogin">등록하기</button>
			<br>
			<button class="fifaButton"
				onclick="location.href='mainSearch'; return false;">돌아가기</button>

		</form>
	</main>



</body>
</html>
