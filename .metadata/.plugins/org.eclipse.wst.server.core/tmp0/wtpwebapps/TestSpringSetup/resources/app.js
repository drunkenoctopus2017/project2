/**
 * Application module and control for app.html.
 * Written verbosely for clarity (hopefully).
 */

/**
 * NOTE: This file currently contains console.logs and dummy data/methods.
 * These should be deleted before being pushed to develop branch.
 * If you use this file to build new features, 
 * delete all console.logs and dummy methods from your source (including the ones already here).
 * And also delete this comment and others.
 * 
 * Only descriptive/explanatory comments should remain.
 */

var app = angular.module("MainModule", ["ngRoute"]);

//This is an application-wide constant. If our url path changes, we can maintain it here.
app.constant("urlBase", "resources/");

app.config(function($routeProvider, urlBase) {
	$routeProvider.when("/", {
		templateUrl: urlBase + "loginView.html", 
		controller: "loginController"
	});
});

app.controller("GlobalController", function() {
	//this in addition to the "global as GlobalController" syntax will allow us to access
	//variables and references in this code block in other codeblocks (eg test controller, see below)
	//This is where we'll store "global" variables instead of $rootScope.
	//Do not put stuff here lightly.
	
	//Alternatively, it may be possible to use angular.value() to contain variables (it's similar to constant, seen above).
	
	global = this; 
	
	this.testVar = "testVar";
	console.log("global init - testVar: " + global.testVar);
	
	this.scrumUser = {};
});
/*
Anything to add to this?
controller("test", function($scope) {}) vs controller("test", ["$scope", function($scope) {}])
Use the Array syntax so you can minify your code without worrying about the minifier renaming function parameters.
*/
app.controller("loginController", function($scope, loginUserService) {
	console.log("profile init");
	console.log("global testvar: " + global.testVar);
	
	$scope.username = "test";
	$scope.password = "test";
	
	$scope.login = function() {
		console.log("huh")
		//note that this anonymous function only has one line.
		loginUserService.login($scope.username, $scope.password).then(
			function (response) {
				//nested anonymous function for success
				/*
				 * The response object has these properties:
				 * data – {string|Object} – The response body transformed with the transform functions.
				 * status – {number} – HTTP status code of the response.
				 * headers – {function([headerName])} – Header getter function.
				 * config – {Object} – The configuration object that was used to generate the request.
				 * statusText – {string} – HTTP status text of the response.
				 * xhrStatus – {string} – Status of the XMLHttpRequest (complete, error, timeout or abort).
				 */
				//Save the data to the global controller if necessary.
				global.scrumUser = response.data;
				console.log("success " + global.scrumUser.username);
			}, function (error) {
				console.log("failure -- add some logic here to send the user back to the logout or a 404");
			}
		);
	}
});

//Factory or Service? Which to use?
app.factory("loginUserService", function($http) {
	
	//This is here for the dummy data test.
	//Delete before pushing to develop.
	var urlBase = "resources/";
	
	return {
		//Since the returned object contains this method and since this method CAN
		//access the above local scope var's this is how we can expose them to the rest of the application.
		//loginUserService will return an object that has a method to return the variables.
		//Note that loginUserService is instantiated as a Singleton (again, defacto).
		getUsername: function() {
			return this.username1;
		}, 
		//Use this technique when trying to get client up and running before connecting to webcontainer.
		loginDummy: function() {
			 return $http.get(urlBase + "DummyUserData.json");
		}, 
		login: function(username, password) {
			 //return $http.get('resources/DummyUserData.json');
			console.log("login?");
			return $http.post("authenticateLogin", {username: username, password: password});
		}
	};
});