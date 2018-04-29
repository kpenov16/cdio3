/**
 * Predefines requests with JSON stringification.
 */

var Agent = {}
Agent.get = function(url, success, error){
	$.ajax({
		url:url,
		method: 'GET',
		success: success,
		error: error
	})
}
Agent.postJSON = function(url, data, success, error){
	$.ajax({
		url:url,
		method: 'POST',
		data: JSON.stringify(data),
		success: success,
		contentType: 'application/json',
		error: error
	})
}