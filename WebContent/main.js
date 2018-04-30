/*
 * //on document ready
$(function(){
	$("#submit").click(function(event){
		event.preventDefault();
		$("#maincontainer").load("admin.html");

	});	
})
*/

$("title").after("<link id='loginCss' rel='stylesheet' type='text/css' href='styles/main.css' />")
/*
load(function(){
	  $("<link/>", {
		   rel: "stylesheet",
		   type: "text/css",
		   href: "/styles/main.css"
		}).appendTo("title");
});
*/

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

