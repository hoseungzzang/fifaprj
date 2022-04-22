<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모든전적보기</title>
<link href="/css/5.1/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<link href="/css/main/dashboard.css" rel="stylesheet">
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
						<th scope="col">작성자</th>
						<th scope="col">스코어</th>
						<th scope="col">상대방</th>
						<th scope="col">대결일자</th>
						<th scope="col">승리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${viewAll}" var="vsHistory">
						<tr>
							<td>${vsHistory.vsWriter}</td>
							<td>${vsHistory.vsWriterScore} vs ${vsHistory.vsOpponentScore}</td>
							<td>${vsHistory.vsOpponent}</td>
							<td>${vsHistory.vsDate}</td>
							<td>${vsHistory.vsWinner}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="/main/mainAllSearch?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}${paging.searchTypeKeyword}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="/main/mainAllSearch?nowPage=${p }&cntPerPage=${paging.cntPerPage}${paging.searchTypeKeyword}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="/main/mainAllSearch?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}${paging.searchTypeKeyword}">&gt;</a>
		</c:if>
	</div>
	<form action="/main/mainAllSearch" method="GET">
	<div class="btn-toolbar mb-2 mb-md-0 center" >
				<div style="margin-left:145px;">
				<select name="userName1" class="fifaSelectBox" style="font-size:14px;">
				<option selected value="none">플레이어명</option>
				<c:forEach items = "${getPlayerList}" var="player">
				<option value="${player.name}"><c:out value="${player.name}"></c:out></option>
				</c:forEach>
				</select>
				<span style="font-size:24px;">vs</span>
				<select name="userName2" class="fifaSelectBox" style="font-size:14px;">
				<option selected value="none">플레이어명</option>
				<c:forEach items = "${getPlayerList}" var="player">
				<option value="${player.name}"><c:out value="${player.name}"></c:out></option>
				</c:forEach>
				</select>
				
				<div class="btn-group me-2">
					<button class="btn btn-sm fontBlack" style="margin-bottom: 4px;" type="submit">플레이어 전적검색</button>
				</div>
				</div>
			</div>
			</form>
			<div class="btn-toolbar mb-2 mb-md-0 center" >
			<form action="/main/mainAllSearch" method="GET">
			<div class="btn-group me-2">
					<button class="btn btn-sm fontBlack" style="margin-bottom: 4px;" type="submit">내 전제 전적조회
					</button>
					<input type="hidden" name="mySearch" value="${getUserName}"></input>
			</div>
			</form>
			
			<div class="btn-group me-2">
					<button class="btn btn-sm fontBlack" style="margin-bottom: 4px;"
					onclick="location.href='mainAllSearch'; return false;">전체 전적검색</button>
			</div>
			</div>
	</main>
</body>
</html>