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

//This is an application-wide variable.
app.value("loginUser", {
	firstName: "not logged in", 
	lastName: ""
	//Add more fields here only if necessary.
});

app.value("loginUserBoards", []);
//The authorization level of the user.
app.value("loginUserRole", {
	id: 0, 
	roleName: "unauthorized"
});

app.config(function($routeProvider, urlBase) {
	$routeProvider.when("/", {
		templateUrl: urlBase + "loginView.html", 
		controller: "loginController"
	}).when("/mainMenu", {
		templateUrl: urlBase + "mainMenuView.html", 
		controller: "mainMenuController"
	}).when("/createScrumBoard", {
		templateUrl: urlBase + "createScrumBoardView.html", 
		controller: "createScrumBoardController"
	});
});

app.controller("loginController", function($scope, $location, loginUserService, loginUser, loginUserRole, loginUserBoards) {
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
				//Save the data to the loginUser value, declared above.
				//Update if necessary to store more info, but the name is currently all that is necessary.
				//All other data should be stored on the server.
				loginUser.firstName = response.data.firstName;
				loginUser.lastName = response.data.lastName;
				loginUserRole.id = response.data.role.id;
				loginUserRole.roleName = response.data.role.roleName;
				while(response.data.scrumBoards.length > 0) {
					loginUserBoards.push(response.data.scrumBoards.pop());
				}
				$location.path("/mainMenu");
			}, function (error) {
				console.log(error);
				//The error object above has: 
				//data, 
				//status, 
				//config, 
				//statusText. (There are special rules about whether statusText is passed - browsers, mobile or not, web server etc.) 
				alert(error.status + " " + error.statusText + "\nThere was an error logging in!");
			}
		);
	}
});

app.controller("mainMenuController", function($scope, $location, loginUser, loginUserRole, loginUserBoards) {
	$scope.firstName = loginUser.firstName;
	$scope.lastName = loginUser.lastName;
	$scope.boards = loginUserBoards;
	$scope.role = loginUserRole.id;
	$scope.createScrumBoard = function() {
		$location.path("/createScrumBoard");
	}
});

app.controller("createScrumBoardController", function($scope, $location, scrumBoardService, loginUser, loginUserBoards) {
	$scope.create = function() {
		scrumBoardService.createNewScrumBoard($scope.sbName, $scope.startDate, $scope.duration).then(
			function (response) {
				//Refresh the data for the main menu without doing another server request (because you don't need to);
				loginUserBoards.push(response.data);
				$location.path("/mainMenu");
			}, function (error) {
				alert(error.status + " " + error.statusText + "\nThere was an error creating this board!");
			}
		);
	}
});

//Factory, Service, or Provider? Which to use?
app.factory("loginUserService", function($http) {
	return {
		login: function(username, password) {
			return $http.post("authenticateLogin", {username: username, password: password});
		}
	};
});

app.factory("scrumBoardService", function($http) {
	return {
		createNewScrumBoard: function(name, startDate, duration) {
			return $http.post("createNewScrumBoard", {name: name, startDate: startDate, duration: duration});
		}
	};
});

//TODO delete before pushing to master
function traverseObject(obj) {
	let s = getObjectString(obj, 0);
	console.log("traverse: " + s);
}

function getObjectString(obj, indent) {
	let s = "";
	for (p in obj) {
		s += "\t".repeat(indent) + "key: " + p + " value: " + obj[p] + "\n";
		if (typeof obj[p] == "object" && !Array.isArray(obj[p])) {
			s += getObjectString(obj[p], indent + 1);
		}
	}
	return s;
}