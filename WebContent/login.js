//$(document).ready(function(){
class LogInDetails{
		  constructor(username, password){
			this.username = username; 
			this.password = password; 
		  }
		  username(){ return this.username;} username(username){ this.username = username;}
		  password(){ return this.password;} password(password){ this.password = password;}
}  	


$("#submitId").click(function(event){
	event.preventDefault();	
	
	var logInDetails = new LogInDetails( 
			$("#usernameId").val(), 
			$("#passwordId").val()
			);
	var savedLogins = [];
	savedLogins.push( logInDetails );
	localStorage.setItem( "SAVED_LOGINS", JSON.stringify( savedLogins ) ); 
	
	$("html").parent().find("#maincontainer").load("admin.html");		
});
	

$(document).ready(function(){
	
	$("#showPasswordID").click(function(){
		if($("#passwordId").attr("type") === "password"){
			$("#passwordId").attr("type", "text");			
		}else{
			$("#passwordId").attr("type", "password");
		}
	});
	
});
//});
