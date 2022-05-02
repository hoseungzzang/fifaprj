$(function() {
	$("#btnLogin").click(function() {
		var grpl = $("input[name=vsWriterScore]").length;

		//배열 생성
		var grparr = new Array(grpl);

		//배열에 값 주입
		for (var i = 0; i < grpl; i++) {
			grparr[i] = new Array(5);
			grparr[i][0] = $("select[name=vsWriter]").eq(i).val();
			grparr[i][1] = $("input[name=vsWriterScore]").eq(i).val();
			grparr[i][2] = $("input[name=vsOpponentScore]").eq(i).val();
			grparr[i][3] = $("select[name=vsOpponent]").eq(i).val();
			grparr[i][4] = $("select[name=vsMatch]").eq(i).val();
			for (var j = 0; j < 5; j++) {
				if (grparr[i][j] == "") {
					alert(grparr[i][j] + (i + 1) + "열의" + (j + 1) + "번째 칸을 입력하세요.");
					return false;
				}
			}

		}
		$.ajax({
			url: '/main/mainWrite',
			type: 'post',
			dataType: 'json',
			traditional: true,
			data: { grparr: grparr },
			success: function(data) { console.log("성공"); }
			, error: function(e) { console.log(e); }

		});
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