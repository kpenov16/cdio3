$(document).ready(function(){
	class User{
		  constructor(id, cpr, firstName, lastName, ini, roles, password){
			this.id = id; this.cpr = cpr; 
			this.firstName = firstName; this.lastName = lastName;
			this.ini = ini; this.roles = roles; this.password = password;
		  }
		  id(){ return this.id;} id(id){ this.id = id;}
		  cpr(){ return this.cpr;} cpr(cpr){ this.cpr = cpr;}
		  firstName(){ return this.firstName;} firstName(firstName){ this.firstName = firstName;}
		  lastName(){ return this.lastName;} lastName(lastName){ this.lastName = lastName;}
		  ini(){ return this.ini;} ini(ini){ this.ini = ini;}
		  roles(){ return this.roles;} roles(roles){ this.roles = roles;}
		  password(){ return this.password;} password(password){ this.password = password;}
		}  
		//const username = document.getElementById("usernameid").innerHTML();
		//const firstname = document.getElementById("firstnameid").innerHTML();
		//const lastname = document.getElementById("lastnameid").innerHTML();

		let users = localStorage.getItem('users') ? JSON.parse(localStorage.getItem('users')) : [];
	
	
	$("#saveID").click(function(event){		    
		event.preventDefault();		
		
		//var user = new User(username, firstname, lastname);
    	var user2 = new User("testUserName", "TestFirstName", "TestLastName", "123", "231", "122", "223");
    	//users.push(user);
    	users.push(user2);
    	localStorage.setItem("users", JSON.stringify(users)); 
    	
    	$("html").parent().find("#maincontainer").load("admin.html");
	});
});
