$(function(){
	var $user = $("#username"),$pass = $("#password"),$login = $("#logindo"),$remember = $("#remember");
	
	$login.on("click", function() {
		if(!$user.val()) {
			$.alert("请输入用户名！", "warning", function() {
				setTimeout(function(){$user[0].focus()},100);
			});
			return false;
		}
		if(!$pass.val()) {
			$.alert("请输入密码！", "warning", function() {
				setTimeout(function(){$pass[0].focus()},100);
			});
			return false;
		}
		if($user.val() && $pass.val()){
			$pass.blur();
			$.ajax({
				type: "post",
				url: "/app-admin/Login/login.do",
				data: {
					"userName": $user.val(),
					"userPassword": $pass.val()
				},
				suc: function(data) {
					window.location.href = "index.html"
				},
				hasAlert: true,
				onErrConfirm: function() {
					setTimeout(function(){$user[0].focus()},100);
				},
				loadingContainer:$("body")
			});
			return false;
		}
	})
	$user.on("keyup",function(e){
		if(e.which == 13){
			$pass[0].focus();
		}
	});
	$pass.on("keyup",function(e){
		if(e.which == 13){
			$login.trigger("click");
		}
	})
	$(window).load(function(){
		setTimeout(function(){$user[0].focus()},100);
	})
});
