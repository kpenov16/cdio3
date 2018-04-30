$(document).ready(function(){
	class User{
		  constructor(id, cpr, firstName, lastName, initials, password, active, role){
			this.id = id; this.cpr = cpr; 
			this.firstName = firstName; this.lastName = lastName;
			this.initials = initials; this.password = password;
			this.role = role; this.active = active; 
		  }
		  id(){ return this.id;} id(id){ this.id = id;}
		  cpr(){ return this.cpr;} cpr(cpr){ this.cpr = cpr;}
		  firstName(){ return this.firstName;} firstName(firstName){ this.firstName = firstName;}
		  lastName(){ return this.lastName;} lastName(lastName){ this.lastName = lastName;}
		  initials(){ return this.initials;} initials(initials){ this.initials = initials;}		  
		  password(){ return this.password;} password(password){ this.password = password;}
		  role(){ return this.role;} role(role){ this.role = role;}
		  active(){ return this.active;} active(active){ this.active = active;}
	 }
	 class UserRole{
		  constructor(admin, leader, pharmacist, technician){
			this.admin = admin;	this.leader = leader; 
			this.pharmacist = pharmacist; this.technician = technician; 
		  }
		  admin(){ return this.admin;} admin(admin){ this.admin = admin;}
		  leader(){ return this.leader;} leader(leader){ this.leader = leader;}
		  pharmacist(){ return this.pharmacist;} pharmacist(pharmacist){ this.pharmacist = pharmacist;}
		  technician(){ return this.technician;} technician(technician){ this.technician = technician;}
	 }
	 function LogInViewModel(username, password, users, idUserToDelete){
			this.username = username 
			this.password = password
			this.users = users
			this.idUserToDelete = idUserToDelete;
	 }

	//let users = localStorage.getItem('users') ? JSON.parse(localStorage.getItem('users')) : [];
	
	
	$("#saveID").click(function(event){		    
		event.preventDefault();		
		
		var userName = $('.userdetails').find('#usernameid').text(); //possibly unique 
		var password = $('.userdetails').find('#passwordid').text();
		/*
		var role = new UserRole( $('.mutliSelect').find('#adminChk').is(':checked'),
								 $('.mutliSelect').find('#leaderChk').is(':checked'),
								 $('.mutliSelect').find('#pharmacistChk').is(':checked'),
								 $('.mutliSelect').find('#technicianChk').is(':checked') );
		*/
		
    	let savedLogins = localStorage.getItem("SAVED_LOGINS") ? JSON.parse( localStorage.getItem("SAVED_LOGINS") ) : [];
		
		alert( "username: " + savedLogins[0].username + " password" + savedLogins[0].password );
        
		var updateViewModel = new LogInViewModel(savedLogins[0].username, savedLogins[0].password)

    	var user = extractUser();		
		updateViewModel.users = [user];
		
		var vm = JSON.stringify( updateViewModel );
		
        $.ajax({
			type : "PUT", //"PUT" 
			url : "http://localhost:8080/DynamicLoading/rest2/users/newUsers", //"http://localhost:8080/DynamicLoading/rest/login",
			contentType : "application/json; charSet=UTF-8",
			data : vm,
			dataType : "json"
		})			
		.done(function(data){  //JSON, status, jqXHR){
			alert(".done");
		})			
		.fail(function(data){
			alert(".fail");
		}); 
    	
    	$("html").parent().find("#maincontainer").load("admin.html");
	});
	
	function extractUser(){
		return new User( $('.userdetails').find('#useridid').val(),
						 $('.userdetails').find('#cprid').val(),
						 $('.userdetails').find('#firstnameid').val(),
						 $('.userdetails').find('#lastnameid').val(),
						 $('.userdetails').find('#initialsid').val(),
						 $('.userdetails').find('#passwordid').val(),
						 $('.userdetails').find('#activeChk').is(':checked'),
						 new UserRole( $('.mutliSelect').find('#adminChk').is(':checked'),
								       $('.mutliSelect').find('#leaderChk').is(':checked'),
								       $('.mutliSelect').find('#pharmacistChk').is(':checked'),
								       $('.mutliSelect').find('#technicianChk').is(':checked') )
						);
	}
	
	
});
