$(function(){
$("#btnLogin").click(function(){
 userId=$("#floatingInput").val();
 var userPassword=$("#floatingPassword").val(); if(userId == ""){
  alert("아이디를 입력하세요");
  $("#floatingInput").focus(); //입력포커스 이동

  return; //함수 종료
}
if(userPassword==""){
 alert("비밀번호를 입력하세요"); 
 $("#floatingPassword").focus();
  return;
}
//폼 내부의 데이터를 전송할 주소
 document.form1.action= "/login";
 document.form1.submit(); //제출
 });
});