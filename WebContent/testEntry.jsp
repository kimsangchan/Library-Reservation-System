<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<link href="css/loadingBar.css" rel="stylesheet" />
		<link href="css/MainPageStyle.css" rel="stylesheet" />
		<link href="css/AccountPageStyle.css" rel="stylesheet" />

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="js/pace.js"> </script>
		
		<script>
			$(document).ready(function(){
				Pace.track(function(){
					$.ajax({
						url: "http://localhost:8080/RoomReservation/HtmlParser",
						success: function(result){
							$("#roomDisplay").html(result);
						}
					});
				});
			});
			
			function notifyUser(roomName)
			{
				var xhttp = new XMLHttpRequest();
				xhttp.open("GET","http://localhost:8080/RoomReservation/EmailSenderProcessor?EmailType=notification&capacity=1&recipient0=<%=request.getSession().getAttribute("username")%>&roomName="+roomName);
				console.log("http://localhost:8080/RoomReservation/EmailSenderProcessor?EmailType=notification&capacity=1&recipient0=<%=request.getSession().getAttribute("username")%>&roomName="+roomName);
				xhttp.send();
			}
		</script>
		
	</head>
	
	<body>
		<div id="topOfPage"> 
			<a href="http://www.usc.edu/">
				<img class="right" src="http://parents.usc.edu/wp-content/themes/usc-communications-parents/images/usc_logo.svg">
			</a>
			<button id ="logout" value = "Logout" style="float:right" onclick="window.location='MainPage.jsp'">Logout</button>
			
		</div>
		<div id="roomDisplay"> </div>
		<!-- div id="bottomOfPage"> </div -->
	</body>

	
</html>