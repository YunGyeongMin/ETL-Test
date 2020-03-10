/**
 * 
 */
$(document).ready(function(){
	//ODS
	$("#ODS_insert").click(function(){
		$("#ODS_insert").prop("disabled", true);
		$("#ODS_result").show();
		var page = setInterval(function(){ 
			$.ajax({
				type:"POST",
				url:"/odsCnt"	
			}).done(function(d){
				$("#ODS_result").text("insert 실시간 현황 : "+ d + "건");
			})
			}, 1000);
		$.ajax({
			type : "POST",
			url : "/ods"
		}).done(function(d){
			$.ajax({
				type:"POST",
				url:"/odsCnt"	
			}).done(function(d){
				$("#ODS_result").text("insert 실시간 현황 : "+ d + "건");
				alert("insert 실시간 현황 : "+ d + "건");
			})
			clearInterval(page);
			$("#ODS_result").hide();
			$("#ODS_insert").prop("disabled", false);
			if(d == 1) alert("ods insert 성공!");
			else alert("ods insert 실패!");
		})
	})
	
	//DW
	$("#DW_insert").click(function(){
		$("#DW_insert").prop("disabled", true);
		$("#ODS_result").show();
		var page = setInterval(function(){ 
			$.ajax({
				type:"POST",
				url:"/dwCnt"	
			}).done(function(d){
				$("#ODS_result").text("insert 실시간 현황 : "+ d + "건");
			})
			}, 1000);
		$.ajax({
			type : "POST",
			url : "/dw"
		}).done(function(d){
			clearInterval(page);
			$.ajax({
				type:"POST",
				url:"/dwCnt"	
			}).done(function(d){
				$("#ODS_result").text("insert 실시간 현황 : "+ d + "건");
				alert("insert 실시간 현황 : "+ d + "건");
			})
			$("#ODS_result").hide();
			$("#DW_insert").prop("disabled", false);
			if(d == 1) alert("dw insert 성공!");
			else alert("dw insert 실패!");
		})
	})
	
	//DM
	$("#DM_insert").click(function(){
		$("#DM_insert").prop("disabled", true);
		$("#ODS_result").show();
		var page = setInterval(function(){ 
			$.ajax({
				type:"POST",
				url:"/dmCnt"	
			}).done(function(d){
				$("#ODS_result").text("insert 실시간 현황 : "+ d + "건");
			})
			}, 1000);
		$.ajax({
			type : "POST",
			url : "/dm"
		}).done(function(d){
			clearInterval(page);
			$.ajax({
				type:"POST",
				url:"/dmCnt"	
			}).done(function(d){
				$("#ODS_result").text("insert 실시간 현황 : "+ d + "건");
				alert("insert 실시간 현황 : "+ d + "건");
			})
			$("#ODS_result").hide();
			$("#DM_insert").prop("disabled", false);
			if(d == 1) alert("dm insert 성공!");
			else alert("dm insert 실패!");
		})
	})
	
	//수집결과
	$("#DM_result").click(function(){
		
	})
	
})
	
