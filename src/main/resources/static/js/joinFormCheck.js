/**
 *  Join Form에서 검증
 */

 $("#userName").blur(idDuplicateCheck);
 
 function idDuplicateCheck(){
	 
	 $.ajax({
		 type:"GET",
		 url:"./idDuplicateCheck",
		 data:{
			 userName:$('#userName').val()
		 },
		 success:function(result){
			 console.log(result)
			 if(result){
				 console.log('사용 가능한 ID')
			 }else {
				 console.log('중복 ID')
			 }
		 },
		 error:function(){
			 console.log('error')
		 }
	 })
	 
 }