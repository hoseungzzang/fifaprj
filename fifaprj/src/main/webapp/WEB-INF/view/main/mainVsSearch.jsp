<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전적모아보기</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<link href="/css/5.1/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<link href="/css/main/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="/js/main/vsSearchCheck.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header" />

	<main class="" style="background-color: #f5f5f5; height: 1270px;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">전적조회</h1>
		
		</div>



		<div class="table-responsive">
			<table class="table table-striped table-sm" style="text-align:center;">
				<thead>
					<tr>
						<th scope="col">플레이어1</th>
						<th scope="col">플레이어2</th>
						<th scope="col">승패</th>
						<th scope="col">승률</th>
						<th scope="col">종류</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${getVsList}" var="vsHistory">
						<tr>
							<td>${vsHistory.get(0)}</td>
							<td>${vsHistory.get(1)}</td>
							<td>${vsHistory.get(2)}승 ${vsHistory.get(4)}무 ${vsHistory.get(3)}패</td>
							<td>${vsHistory.get(5)}%</td>
							<td>${vsHistory.get(6)}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	<form name="form1" method="POST">
	<div class="btn-toolbar mb-2 mb-md-0 center" >
				<div >
				<select id="userName1" name="userName1" class="fifaSelectBox" style="font-size:14px;">
				<option selected value="null">플레이어명</option>
				<c:forEach items = "${getPlayerList}" var="player">
				<option value="${player.name}"><c:out value="${player.name}"></c:out></option>
				</c:forEach>
				</select>
				</div>
			</div>
			
	
			<div class="btn-toolbar mb-2 mb-md-0 center" >
			
			<div class="btn-group me-2">
			<label class="" style="margin-left: 15px;"><input type="radio" id="date" name="date" value="7" checked="checked"> 7일</label>
			<label class="" style="margin-left: 25px;"><input type="radio" id="date" name="date" value="14"> 14일</label>
			<label class="" style="margin-left: 25px;"><input type="radio" id="date" name="date" value="all"> 전체</label>
			</div>
			
			</div>
			<div class="btn-group me-2 center">
					<button id="btnLogin" class="btn btn-sm fontBlack" style="margin-bottom: 4px;" type="submit">플레이어 전적검색</button>
				</div>
	</form>
			
	</main>
</body>
</html>