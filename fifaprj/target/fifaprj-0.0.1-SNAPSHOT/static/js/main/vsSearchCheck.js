$(function() {
	$("#btnLogin").click(function() {
		var userName1 = $("#userName1").val();

		if (userName1 == "null") {
			alert("플레이어를 선택하세요.");
			$("#userName1").focus(); //입력포커스 이동

			return false; //함수 종료
		}
		
		//폼 내부의 데이터를 전송할 주소
		document.form1.action = "/main/mainVsSearch";
		document.form1.submit(); //제출
	});
});