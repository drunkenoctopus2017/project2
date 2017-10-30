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
	}).when("/mainMenu", {
		templateUrl: urlBase + "mainMenuView.html", 
		controller: "mainMenuController"
	});
});

app.controller("GlobalController", function() {
	//this in addition to the "global as GlobalController" syntax will allow us to access
	//variables and references in this code block in other codeblocks (eg test controller, see below)
	//This is where we'll store "global" variables instead of $rootScope.
	//Do not put stuff here lightly.
	
	//Alternatively, it may be possible to use angular.value() to contain variables (it's similar to constant, seen above).
	
	global = this; 
	
	this.scrumUser = {};
});

app.controller("loginController", function($scope, $location, loginUserService) {
	
	$scope.login = function() {
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
				$location.path("/mainMenu");
			}, function (error) {
				alert("There was an error logging in!");
			}
		);
	}
});

app.controller("mainMenuController", function($scope) {
	console.log("mainMenu");
	$scope.firstName = global.scrumUser.firstName;
	$scope.lastName = global.scrumUser.lastName;
});

//Factory, Service, or Provider? Which to use?
app.factory("loginUserService", function($http) {
	return {
		login: function(username, password) {
			return $http.post("authenticateLogin", {username: username, password: password});
		}
	};
});