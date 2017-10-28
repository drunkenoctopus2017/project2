/**
 * app.js
 */
window.onload = function(){
	loadLogin();
}

function loadLogin(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
		}
	}
	xhr.open('GET', 'login', true);
	xhr.send();
}