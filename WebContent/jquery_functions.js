//"use strict"

function LogInViewModel(username, password){
		this.username = username 
		this.password = password
}

function User(firstName,  lastName){
		this.firstName = firstName
		this.lastName = lastName
}

function LogInResponceViewModel(success, roles, users){
	this.success = success
	this.roles = roles
	this.users = users
}

$(document).ready(function(){
	  
	    
	    
	
		$("#sendID").click(function(event){
			event.preventDefault();
			
			var id = $("#idID").val();
			var userName = $("#userNameID").val();
			var password = $("#passwordID").val();
			
			var vm = JSON.stringify( new LogInViewModel(userName, password) );
			
			$.ajax({
				type : "POST",
				url : "http://localhost:8080/HelloWorldService/rest/login",
				contentType : "application/json; charSet=UTF-8",
				data : vm,
				dataType : "json"
			})
			
			.done(function(data){  //JSON, status, jqXHR){
				//alert(dataJSON + " success");
				//var response = JSON.parse(jqXHR.responseJSON);
				//var jq = JSON.parse(dataJSON);
				//$("#responceID").text("success: " + vm.success); //vm.success);
				//alert(jqXHR.responseJSON + " " + status);
				s = "";
				data.users.forEach(function(user, ind){
					s += "number: " + ind +" " + user.firstName + " " + user.lastName;
				});
				
				alert(typeof data + " " + data.roles + s);
			})
			
			.fail(function(dataJSON){
				alert(dataJSON + " error");
				//var vm = JSON.parse(dataJSON);
				//$("#responceID").text("success: " + vm.success )
			});
			
		});
});


/*

$("title").after("<link id='loginCss' rel='stylesheet' type='text/css' href='styles/main.css' />")

//load(function(){
//	  $("<link/>", {
//		   rel: "stylesheet",
//		   type: "text/css",
//		   href: "/styles/main.css"
//		}).appendTo("title");
//});


function setupAdminContainerCss(){
	$("table, td").css({
		"border": "1px solid black",
		"border-collapse": "collapse",
		"padding": "5px"
	    });				
		$("th").css({
			  "border": "1px solid black",
		      "border-collapse": "collapse",
			  "padding": "5px",
		      "text-align": "left" 
		  });
		$("caption").css({
		   "text-align": "left",
		   "font-size": "18px"
		});	
}

function setupCreateUserContainerCss(){
	$("div.userdetails").css({
	    "display": "grid",
	    "grid-template-columns": "max-content max-content",
	    "grid-gap": "5px"
	  });
	
	$("div.userdetails label.detailslabel").css({
	    "text-align": "right"
	  });
	$("div.userdetails label.detailslabel:after").css({
	    "content": "': '"
	  });
}

$(document).ready(function(){
	  
		$("#submitId").click(function(event){
			event.preventDefault();
			
			var name = $("#usernameId").val();
			var password = $("#passwordId").val();
			
			//I need to check here with the server if the username and password are matching
			if(name === "laster" && password === "effect")
			{
				$("#logincontainer").hide();
				//alert("Hello " + name);
				$("#adminContainer").show();
				
				//css
				setupAdminContainerCss();
				
			}else{
				alert("Wrong user name or password.");
				$("#submitId").css( "background-color", "red");
			}
				
		});
		
		$("a.LogOutLink").click(function(event){
			event.preventDefault();
			$("#usernameId").val("");
			$("#passwordId").val("");
			
			$("#adminContainer").hide();
			$("#createUserContainer").hide();			
			$("#logincontainer").show();
		});
		
		$("a.CreateUserLink").click(function(event){
			event.preventDefault();
			
			
			$("#logincontainer").hide();
			$("#adminContainer").hide();
			$("#createUserContainer").show();
			setupCreateUserContainerCss();
		});
		

  });
  */
