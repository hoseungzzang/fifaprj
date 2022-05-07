<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, 그리고 Bootstrap 기여자들">
    <meta name="generator" content="Hugo 0.88.1">
    <title>메인페이지</title>

    <link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/jumbotron/">
    <link href="/css/5.1/bootstrap.min.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
</head>

<body>
	<tiles:insertAttribute name="header" />
	<main style="background-color:#f5f5f5; height:1270px;">
  <div class="container py-4">
    <header class="pb-3 mb-4 border-bottom">
      
        <span class="fs-4">${userName}님 안녕하세요!</span>
      
    </header>

    <div class="p-5 mb-4 bg-white rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">전체전적을 확인해보세요!</h1>
        <p class="col-md-8 fs-4">현재 플레이어는 총 ${getPlayerList} 명 입니다.</p>
        <button class="btn custom btn-lg" type="button" onclick="location.href='/main/mainAllSearch'; return false;">전체전적 보러가기</button>
        <button class="btn custom btn-lg" type="button" onclick="location.href='/main/mainLeagueSearch'; return false;">리그 표 보러가기</button>
      </div>
    </div>

    <div class="row align-items-md-stretch">
      <div class="col-md-6">
        <div class="h-100 p-5 text-black bg-white rounded-3">
          <h2 class="display-5 fw-bold">전적을 등록하세요!</h2>
          <p class="col-md-8 fs-4">현재 친선전만 진행하니,<br>친선전으로 등록 부탁 드립니다.</p>
          <button class="btn custom btn-lg" type="button" onclick="location.href='/main/mainWrite'; return false;">전적등록 하러가기</button>
        </div>
      </div>
      <div class="col-md-6">
        <div class="h-100 p-5 bg-white border rounded-3">
          <p style="font-size:37px"class="display-5 fw-bold">다른 플레이어들과의 <br>전적을 확인하세요!</p>
       	  <p class="col-md-8 fs-4">한눈에 전적을 몰아볼 수 <br>있습니다.<br></p>
         <button class="btn custom btn-lg" type="button" onclick="location.href='/main/mainVsSearch'; return false;">전적 확인하기</button>
        </div>
      </div>
    </div>


  </div>
</main>

</body>
</html>