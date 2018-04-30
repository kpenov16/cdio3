
$(document).ready(function(){
		
	function LogInViewModel(username, password, users, idUserToDelete){
			this.username = username 
			this.password = password
			this.users = users
			this.idUserToDelete = idUserToDelete;
	}
	
	function UserResponced(firstName,  lastName){
			this.firstName = firstName
			this.lastName = lastName
	}
	
	function LogInResponceViewModel(success, roles, users){
		this.success = success
		this.roles = roles
		this.users = users
	}
	/////////////////	
	class LogInDetails{
		  constructor(username, password){
			this.username = username; 
			this.password = password; 
		  }
		  username(){ return this.username;} username(username){ this.username = username;}
		  password(){ return this.password;} password(password){ this.password = password;}
	}  	
	class User{
		  constructor(id, cpr, firstName, lastName, initials, password, role, active){
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
	
	 var i = 0;
     let users = localStorage.getItem('users') ? JSON.parse( localStorage.getItem('users') ) : [];
     let savedLogins = localStorage.getItem("SAVED_LOGINS") ? JSON.parse( localStorage.getItem("SAVED_LOGINS") ) : [];
 	
     //load from server 
     loadUsers();
     
     
	$("#updateID").click(function(event){
		event.preventDefault();	
		updateUsers();	
	 });
	 
	 $("#createUserID").click(function(event){
		 event.preventDefault();		
 		 $("html").parent().find("#maincontainer").load("createUser.html");
	 });
	 
	 $("#deleteId").click(function(event){
		 event.preventDefault();
		 deleteRows();		
	 }); 
	 
	 function deleteRows(){		 
		 try {
				var rowCount = $("#userstable > tbody").children().length;
				//alert($("#userstable > tbody").children().eq(0).prop('nodeName'));//.prop('nodeName'));
				for(var i = 1; i < rowCount; i++) {					
					var $row = $("#userstable > tbody").children().eq(i);
					var $chkbox = $row.find(".delChk");					
					
					if(null != $chkbox && true == $chkbox.is(":checked")) {						
						
						deleteUserById( Number( $row.find('#userId').text().replace(/\s/g,'') ) ) 
						//alert("User with id: " + $row.find('#userId').text() + " was not deleted!");
												
						$("#userstable > tbody").children().eq(i).remove();
						rowCount--;
						i--;
					}
				}
		}catch(e) {
			alert(e);
		}
	 }
	 
	 function deleteUserById( userId ){
		 
		 let savedLogins = localStorage.getItem("SAVED_LOGINS") ? JSON.parse( localStorage.getItem("SAVED_LOGINS") ) : [];
		 var updateViewModel = new LogInViewModel(savedLogins[0].username, savedLogins[0].password);
		 updateViewModel.idUserToDelete = userId;
		 
		 var vm = JSON.stringify( updateViewModel );
		 
		 //var deleted = false;
		 
		 $.ajax({
				type : "DELETE", //"DELETE"
				url : "http://localhost:8080/DynamicLoading/rest2/users/Id", //"http://localhost:8080/DynamicLoading/rest/login",
				contentType : "application/json; charSet=UTF-8",
				data : vm,
				dataType : "json"
			})			
			.done(function(data){  //JSON, status, jqXHR){
				alert(".done");
				//deleted = true;
			})			
			.fail(function(data){
				alert(".fail");
			});
		 
		 //return deleted;
	 }
	 
	 function updateUsers(){
		 let savedLogins = localStorage.getItem("SAVED_LOGINS") ? JSON.parse( localStorage.getItem("SAVED_LOGINS") ) : [];
			
			alert( "username: " + savedLogins[0].username + " password" + savedLogins[0].password );
	        
			var updateViewModel = new LogInViewModel(savedLogins[0].username, savedLogins[0].password)
			
			updateViewModel.users = extractUsers();			
			
			var vm = JSON.stringify( updateViewModel );
			
	        $.ajax({
				type : "PUT", //"PUT"
				url : "http://localhost:8080/DynamicLoading/rest2/users/all", //"http://localhost:8080/DynamicLoading/rest/login",
				contentType : "application/json; charSet=UTF-8",
				data : vm,
				dataType : "json"
			})			
			.done(function(data){  //JSON, status, jqXHR){
				alert(".done");
				//populateTable(data);
				//$(".Editable").makeEditable();
			})			
			.fail(function(data){
				alert(".fail");
			});
	     
	 }
	
	function extractUsers(){
		var users = [];
		$("#userstable > tbody").find('tr').each(function(){ //children().each(function(){
			//$(this).find('td').
			//$(this) is the current row 
			//alert( "nodeName: " + $(this).prop('nodeName') +" "+$(this).text() + " " + $(this).find('#userId').text() + " => " + $(this).find('#userFirstName').text() );
			
			var user = new User();
			user.id = $(this).find('#userId').text();
			user.active = $(this).find('#activeChk').is(':checked');
			user.cpr = $(this).find('#userCpr').text();
			user.firstName = $(this).find('#userFirstName').text();
			user.lastName = $(this).find('#userLastName').text();
			user.initials = $(this).find('#userInitials').text();
			user.role = new UserRole( $(this).find('#adminChk').is(':checked'),
									  $(this).find('#leaderChk').is(':checked'),
									  $(this).find('#pharmacistChk').is(':checked'),
									  $(this).find('#technicianChk').is(':checked') );  
			user.password = $(this).find('#userPassword').text();
			users.push(user);
		});
		users.splice(0,1); //get rid the first as it is from the header, this is a work around because I didn't design the table properly
		/*
		users.forEach(function(user,index){
			alert("index: "+index+" "+user.id+" "+user.active+" "+user.cpr+" "+user.firstName+" "+user.lastName+" "+user.initials+" "+
					user.role.admin+" "+user.role.leader+" "+user.role.pharmacist+" "+user.role.technician+" "+
					user.password);			
		});*/		
		return users;
	}
	 
	 function loadUsers(){		 
		let savedLogins = localStorage.getItem("SAVED_LOGINS") ? JSON.parse( localStorage.getItem("SAVED_LOGINS") ) : [];
		
		alert( savedLogins[0].username + " " + savedLogins[0].password );
        
		var vm = JSON.stringify( new LogInViewModel(savedLogins[0].username, savedLogins[0].password) );
		
        $.ajax({
			type : "POST",
			url : "http://localhost:8080/DynamicLoading/rest2/users/all", //"http://localhost:8080/DynamicLoading/rest/login",
			contentType : "application/json; charSet=UTF-8",
			data : vm,
			dataType : "json"
		})			
		.done(function(data){  //JSON, status, jqXHR){
			alert(".done");
			populateTable(data);
			$(".Editable").makeEditable();
		})			
		.fail(function(data){
			alert(".fail");
		});
     }
	 
	 function populateTable(data){		    
		    data.forEach(function(user, index){ //data.users.forEach(function(user, index){
		    	alert(user.role.admin + " " + user.role.technician);
		    	var $tr = $("<tr/>");
		    	$("#userstable > tbody:last").append($tr);
		    	$('<td />', {class: 'Editable', id: 'userId' }).text( user.id ).appendTo($tr);
		    	$('<td />', {id: 'isUserActive'}).append( $('<input />', {type: 'checkbox', value: 'Active', id: 'activeChk'}).prop('checked', user.active) ).appendTo( $tr );
		    	$('<td />', {class: 'Editable', id: 'userCpr' }).text( user.cpr ).appendTo($tr);
		    	$('<td />', {class: 'Editable', id: 'userFirstName' }).text( user.firstName ).appendTo($tr);
		    	$('<td />', {class: 'Editable', id: 'userLastName' }).text( user.lastName ).appendTo($tr);
		    	$('<td />', {class: 'Editable', id: 'userInitials' }).text( user.initials ).appendTo($tr);
		    	$('<td />', {id: 'userRoles'}).append(  
		    			$('<div />', {class: 'mutliSelect'}).append(
		    					$('<input />', {type: 'checkbox', value: 'Admin', id: 'adminChk'}).prop('checked', user.role.admin), $('<label />').html('Admin |') ,
		    					$('<input />', {type: 'checkbox', value: 'Leader', id: 'leaderChk'}).prop('checked', user.role.leader), $('<label />').html('Leader |<br>') ,
		    					$('<input />', {type: 'checkbox', value: 'Pharmacist', id: 'pharmacistChk'}).prop('checked', user.role.pharmacist ), $('<label />').html('Pharmacist |') ,
		    					$('<input />', {type: 'checkbox', value: 'Technician', id: 'technicianChk'}).prop('checked', user.role.technician ), $('<label />').html('Technician |')
		    				) 
		    			).appendTo( $tr );
		    	$('<td />', {class: 'Editable', id: 'userPassword' }).text( user.password ).appendTo($tr);
		    	$('<td />').append( $('<input />', {class: 'delChk', name: 'chk', type: 'checkbox'}) ).appendTo( $tr );		    	
		    			    	
		    });
		 
	 }	 
	 
	 $.fn.makeEditable = function() {
		  $(this).on('dblclick',function(){
		    if($(this).find('input').is(':focus')) return this;
		    var cell = $(this);
		    var content = $(this).html();
		    $(this).html('<input type="text" value="' + $(this).html() + '" />')
		      .find('input')
		      .trigger('focus')
		      .on({
		        'blur': function(){
		          $(this).trigger('closeEditable');
		        },
		        'keyup':function(e){
		          if(e.which == '13'){ // enter
		            $(this).trigger('saveEditable');
		          } else if(e.which == '27'){ // escape
		            $(this).trigger('closeEditable');
		          }
		        },
		        'closeEditable':function(){
		          cell.html(content);
		        },
		        'saveEditable':function(){
		          content = $(this).val();
		          $(this).trigger('closeEditable');
		        }
		    });
		  });
		return this;
	}
	
});

//"<td><dd><div class='mutliSelect'><ul><li><input type='checkbox' value='Admin' />Admin</li><li><input type='checkbox' value='Leader' />Leader</li></ul></div></dd></td>" +

//var r=$("#userstable").length; 
//$("#userstable").eq(r-1).after('<tr> <td> ...column 1 value column 2 value....');


/*
var table = document.getElementById("userstable");
var row = table.insertRow(-1); //(0)    	
var cell01 = row.insertCell(0);
var cell02 = row.insertCell(1);
var cell03 = row.insertCell(2);
var cell04 = row.insertCell(3);
var cell05 = row.insertCell(4);
var cell06 = row.insertCell(5);
var cell07 = row.insertCell(6);

cell01.innerHTML = users[0].id;//"id user" + i++;	
cell02.innerHTML = users[0].cpr;	
cell03.innerHTML = users[0].firstName;	    	
cell04.innerHTML = users[0].lastName;	
cell05.innerHTML = users[0].ini;	
cell06.innerHTML = users[0].roles;	    	
cell07.innerHTML = users[0].password;
*/

//var table = document.getElementById("userstable");
/*var row = table.insertRow(-1);     	
var cell01 = row.insertCell(0);
var cell02 = row.insertCell(1);
var cell03 = row.insertCell(2);
var cell04 = row.insertCell(3);
var cell05 = row.insertCell(4);
var cell06 = row.insertCell(5);
var cell07 = row.insertCell(6);

cell01.innerHTML = "";//users[0].id;	
cell02.innerHTML = "";//users[0].cpr;	
cell03.innerHTML = "<input type='text' value=" + user.firstName + ">";	    	
cell04.innerHTML = user.lastName;	
cell05.innerHTML = "";//users[0].ini;	
cell06.innerHTML = "<dd><div class='mutliSelect'><ul><li><input type='checkbox' value='Apple' />Apple</li><li><input type='checkbox' value='Blackberry' />Blackberry</li></ul></div></dd>";//users[0].roles;	    	
cell07.innerHTML = "";//users[0].password;*/	


/*
s = "";
data.users.forEach(function(user, index){
	s += "number: " + index +" " + user.firstName + " " + user.lastName;
});

alert(typeof data + " " + data.roles + s);*/

/*
"<td class='PlainText'>" + "<input type='text' value='" + "none" + "' />" + "</td>" + 
"<td class='PlainText'>" + "<input type='text' value='" + "none" + "' />" + "</td>" +
"<td class='PlainText'>" + "<input type='text' value='" + user.firstName + "' />" + "</td>" + 
"<td class='PlainText'>" + "<input type='text' value='" + user.lastName + "' />" + "</td>" + 
"<td class='PlainText'>" + "<input type='text' value='" + "none" + "' />" + "</td>" + 
"<td><dd><div class='mutliSelect'><ul><li><input type='checkbox' value='Admin' />Admin</li><li><input type='checkbox' value='Leader' />Leader</li></ul></div></dd></td>" +
"<td class='PlainText'>" + "<input type='text' value='" + "none" + "' />" + "</td>" + 
"<td class='PlainText'>" + "<input type='text' value='" + "none" + "' />" + "</td>" + 
"<td>" + "<input class='delChk' type='checkbox' name='chk'/>" + "</td>" +
*/


//$tr.append(
//"<td class='Editable' id='userId'>" + user.id + "</td>" + 
//"<td id='isUserActive'><input type='checkbox' value='Active'/></td>" + 
//"<td class='Editable' id='userCpr'>" + user.cpr + "</td>" +
//"<td class='Editable' id='userFirstName'>" + user.firstName + "</td>" + 
//"<td class='Editable' id='userLastName'>" + user.lastName + "</td>" + 
//"<td class='Editable' id='userInitials'>" + user.initials + "</td>" + 
//"<td id='userRoles'><div class='mutliSelect'><input type='checkbox' value='Admin' id='adminChk'/> Admin | <input type='checkbox' value='Leader' id='leaderChk'/>Leader |<br><input type='checkbox' value='Pharmacist' id='pharmacistChk'/>Pharmacist | <input type='checkbox' value='Technician' id='technicianChk'/>Technician | </div></td>" +
//"<td class='Editable'>" + user.password + "</td>" + 
//"<td>" + "<input class='delChk' type='checkbox' name='chk'/>" + "</td>"		
//);
/*
$("#userstable > tbody:last").append(
"<tr>"+
	"<td class='Editable' id='userId'>" + user.id + "</td>" + 
	"<td id='isUserActive'><input type='checkbox' value='Active'/></td>" + 
	"<td class='Editable' id='userCpr'>" + user.cpr + "</td>" +
	"<td class='Editable' id='userFirstName'>" + user.firstName + "</td>" + 
	"<td class='Editable' id='userLastName'>" + user.lastName + "</td>" + 
	"<td class='Editable' id='userInitials'>" + user.initials + "</td>" + 
	"<td id='userRoles'><div class='mutliSelect'><input type='checkbox' value='Admin' id='adminChk'/> Admin | <input type='checkbox' value='Leader' id='leaderChk'/>Leader |<br><input type='checkbox' value='Pharmacist' id='pharmacistChk'/>Pharmacist | <input type='checkbox' value='Technician' id='technicianChk'/>Technician | </div></td>" +
	"<td class='Editable'>" + user.password + "</td>" + 
	"<td>" + "<input class='delChk' type='checkbox' name='chk'/>" + "</td>" +
"</tr>"
);*/	
//$("#userstable > tbody:last").find("#adminChk").prop('checked', user.role.admin);
//$("#userstable > tbody:last").find("#leaderChk").prop('checked', user.role.leader);
//$("#userstable > tbody:last").find("#pharmacistChk").prop('checked', user.role.pharmacist);
//$("#userstable > tbody:last").find("#technicianChk").prop('checked', user.role.technician);

/*
data.users.forEach(function(user, index){
	$("#userstable > tbody").children().find("#adminChk").prop("checked", user.role.admin);
});/*
/*
$("td.PlainText").dblclick(function () {
    var OriginalContent = $(this).text();

    $(this).addClass("cellEditing");
    $(this).html("");
    $(this).children().first().focus();

    $(this).children().first().keypress(function (e) {
        if (e.which == 13) {
            var newContent = $(this).val();
            $(this).parent().text(newContent);
            $(this).parent().removeClass("cellEditing");
        }
    });

    $(this).children().first().blur(function(){
        $(this).parent().text(OriginalContent);
        $(this).parent().removeClass("cellEditing");
    });
});	    
*/