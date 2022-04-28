$(function() {
	$("#btnLogin").click(function() {
	
		var vsWriter = $("#vsWriter").val();
		var vsWriterScore = $("#vsWriterScore").val();
		var vsOpponentScore = $("#vsOpponentScore").val();
		var vsOpponent = $("#vsOpponent").val();
		var vsMatch = $("#vsMatch").val();
		if (vsWriter == "null") {
			alert("플레이어1을 선택하세요.");
			$("#vsWriter").focus(); //입력포커스 이동

			return false; //함수 종료
		}
		if (vsWriterScore == "") {
			alert("플레이어1의 점수를 입력하세요.");
			$("#vsWriterScore").focus();
			return false;
		}
		if (vsOpponentScore == "") {
			alert("플레이어2의 점수를 입력하세요.");
			$("#vsOpponentScore").focus();
			return false;
		}
		if (vsOpponent == "null") {
			alert("플레이어2를 선택하세요.");
			$("#vsOpponent").focus();
			return false;
		}
		if (vsMatch == "null") {
			alert("대결종류를 선택하세요.");
			$("#vsMatch").focus();
			return false;
		}
		if (vsWriter == vsOpponent) {
			alert("서로 다른 플레이어를 선택하시오.");
			return false;
		}
		//폼 내부의 데이터를 전송할 주소
		document.form1.action = "/main/mainWrite";
		document.form1.submit(); //제출
	});
	
	$("#btnLogin2").click(function() {
		var vsWriter = $("#vsWriter").val();
		var vsWriterScore = $("#vsWriterScore").val();
		var vsOpponentScore = $("#vsOpponentScore").val();
		var vsOpponent = $("#vsOpponent").val();
		var vsMatch = $("#vsMatch").val();
		if (vsWriter == "null") {
			alert("플레이어1을 선택하세요.");
			$("#vsWriter").focus(); //입력포커스 이동

			return false; //함수 종료
		}
		if (vsWriterScore == "") {
			alert("플레이어1의 점수를 입력하세요.");
			$("#vsWriterScore").focus();
			return false;
		}
		if (vsOpponentScore == "") {
			alert("플레이어2의 점수를 입력하세요.");
			$("#vsOpponentScore").focus();
			return false;
		}
		if (vsOpponent == "null") {
			alert("플레이어2를 선택하세요.");
			$("#vsOpponent").focus();
			return false;
		}
		if (vsMatch == "null") {
			alert("대결종류를 선택하세요.");
			$("#vsMatch").focus();
			return false;
		}
		if (vsWriter == vsOpponent) {
			alert("서로 다른 플레이어를 선택하시오.");
			return false;
		}
		//폼 내부의 데이터를 전송할 주소
		document.form2.action = "/main/mainUpdate";
		document.form2.submit(); //제출
	});
});