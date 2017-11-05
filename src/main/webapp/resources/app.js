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
app.value("allUsers", []);

//for controlling what shows up on createScrumBoardView
//false, if you're creating a board
//true, if you're editing a board
app.value("editing", {
	value: false,	
});

//variable that stores the id of a single board
//possibly useful for looking at a single board,
//editing a single board, etc
app.value("currentBoard", {
	id: 0,
	name: "no board selected",
	startDate: undefined,
	duration: 0
	//add properties as necessary
}); 

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
	}).when("/viewScrumBoard", {
		templateUrl: urlBase + "scrumBoardView.html",
		controller: "scrumBoardViewController"
	}).when("/editScrumBoard", {
		templateUrl: urlBase + "editScrumBoardView.html",
		controller: "editScrumBoardController"
	}).when("/getAllUsers", {
		templateUrl: urlBase + "addUserView.html", 
		controller: "getAllUsersController"
	});
});

app.controller("navbarController", function($scope, $location, loginUser, loginUserBoards, loginUserService) {

	$scope.logout = function(){
		loginUser.firstName = "not logged in";
		loginUser.lastName = "";
		while(loginUserBoards.length > 0){
			loginUserBoards.pop();	
		}
		loginUserService.logout().then(
			function(response){
				$location.path("/");
			},
			function(error){
				alert(error.status + " " + error.statusText + "\nThere was an error logging out!");
			}
		);
  }
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


app.controller("mainMenuController", function($scope, $rootScope, $location, loginUser, loginUserRole, loginUserBoards, editing, currentBoard) {
	//reset the value of editing, so that it doesn't stay true forever after you edit something
	editing.value = false;
	//clear currentBoard
	currentBoard.id = 0;
	currentBoard.name = "no board selected";
	currentBoard.startDate = undefined;
	currentBoard.duration = 0;
	//fill in html field
	$scope.firstName = loginUser.firstName;
	$scope.lastName = loginUser.lastName;
	$scope.boards = loginUserBoards;
	$scope.role = loginUserRole.id;
	$scope.createScrumBoard = function() {
		$location.path("/createScrumBoard");
	}
  $scope.viewBoard = function(b) {
		$rootScope.currentScrumBoard = b
		//traverseObject(b);
		$location.path("/viewScrumBoard");

	}
  $scope.editScrumBoard = function(board) {
		editing.value = true;
		//set the current board properties to the properties of board associated with the button that called this function
		currentBoard.id = board.id;
		currentBoard.name = board.name;
		currentBoard.startDate = board.startDate;
		currentBoard.duration = board.duration;
		$location.path("/createScrumBoard");
	}

	if($scope.role == 200){
		$scope.getAllUsers = function(board) {
			currentBoard.id = board.id;
			currentBoard.name = board.name;
			currentBoard.startDate = board.startDate;
			currentBoard.duration = board.duration;
			$location.path("/getAllUsers");
		}
	}
});

app.controller("createScrumBoardController", function($scope, $location, scrumBoardService, loginUser, loginUserBoards, editing, currentBoard) {
	$scope.editing = editing.value;
	if(editing.value){
		//fill in the fields with the old values
		$scope.sbName = currentBoard.name;
		//have to recast it as a Date in order for it to show up properly
		$scope.startDate = new Date(currentBoard.startDate);
		$scope.duration = currentBoard.duration;
	}
	$scope.boardName = currentBoard.name;
	$scope.createOrEdit = function () {
		//change functionality depending on value of editing
		if(!editing.value){
			scrumBoardService.createNewScrumBoard($scope.sbName, $scope.startDate, $scope.duration).then(
				function (response) {
					//Refresh the data for the main menu without doing another server request (because you don't need to);
					loginUserBoards.push(response.data);
					$location.path("/mainMenu");
				}, function (error) {
					alert(error.status + " " + error.statusText + "\nThere was an error creating this board!");
				}
			);
		} else {
			//if any of the input is empty, use the previous value
			nameToUse = $scope.sbName;
			if($scope.sbName == undefined){
				nameToUse = currentBoard.name;
			}
			startDateToUse = $scope.startDate;
			if($scope.startDate == undefined){
				startDateToUse = currentBoard.startDate;
			}
			durationToUse = $scope.duration;
			if($scope.duration == undefined){
				durationToUse = currentBoard.duration;
			}
			scrumBoardService.editExistingScrumBoard(currentBoard.id, nameToUse, startDateToUse, durationToUse).then(
				function (response) {
					//Refresh the data for the main menu without doing another server request (because you don't need to);
					//need to remove the outdated board from JS-side list
					loginUserBoards.forEach(function (board, index, array) {
						//find the outdated board
						if(board.id == currentBoard.id){
							//remove it
							loginUserBoards.splice(index, 1);
						}	
					});
					//adds the new one to the front of the JS-side list
					loginUserBoards.unshift(response.data);
					$location.path("/mainMenu");
				}, function (error) {
					alert(error.status + " " + error.statusText + "\nThere was an error editing this board!");
				}
			);
		}
	}
});

app.controller("getAllUsersController", function($scope, $location, getAllUsersService, allUsers, currentBoard) {
	
	$scope.users = [];
	$scope.getAvailableUsers = function(){
		return $scope.users.filter(function(u){
			
			let boards = u.scrumBoards;
			for(let i=0; i < boards.length; i++){
				let boardId =  boards[i].id;
				if(boardId == currentBoard.id){
					return false;
				}
			}
			return true;
		})
	}
	$scope.getBoardUsers = function(){
		return $scope.users.filter(function(u){
			
			let boards = u.scrumBoards;
			for(let i=0; i < boards.length; i++){
				let boardId =  boards[i].id;
				if(boardId == currentBoard.id){
					return true;
				}
			}
			return false;
		})
	}
	
	getAllUsersService.getAllExistingUsers().then (
		function(response) {
			$scope.users = response.data;
			$scope.board = currentBoard.name;
			
		}
	);
	
	$scope.addUserToBoard = function(user){
		getAllUsersService.addUserToBoard(user.id, currentBoard.id).then(
			function (response) {
				//no action necessary
				let temp = $scope.users;
				$location.path("/mainMenu");
				$scope.board = response.data.name;
			}, function (error) {
				alert(error.status + " " + error.statusText + "\nThere was an error!");
			}
		);
	}
});

app.controller("scrumBoardViewController", function ($scope, $rootScope, scrumBoardService, loginUserRole) {
	$scope.scrumBoardName = $rootScope.currentScrumBoard.name;
	$scope.scrumBoardStories = $rootScope.currentScrumBoard.stories;
	$scope.role = loginUserRole.id;
	$scope.filterStoriesByLane = function (laneId) {
		let stories = $rootScope.currentScrumBoard.stories;
		return stories.filter(s => s.laneId == laneId);
	}
	$scope.changeLane = function (story, lane) {
		console.log("story: " + story.id + " lane dir: " + lane.id);
		story.laneId = lane.id;
		scrumBoardService.changeScrumBoardStoryLane(story.id, lane.id).then(
			function (response) {
				//no action necessary
			},
			function (error) {
				alert(error.status + " " + error.statusText + "\nThere was an error changing lanes!");
			}
		);
	}
	$scope.updateTask = function (task) {
		scrumBoardService.updateScrumBoardTaskStatus(task.id, task.status).then(
			function (response) {
				//no action necessary
			}, function (error) {
				alert(error.status + " " + error.statusText + "\nTask could not be updated!");
			}
		);
	}
	
	$scope.setEditStoryTarget = function (story) {
		$rootScope.$emit("editStory", story);
	}
	
	$scope.setNewTaskTarget = function (story) {
		$rootScope.$emit("createNewTask", story)
	}
	scrumBoardService.getScrumBoardLanes().then(
		function (response) {
			$scope.lanes = response.data;
		}, function (error) {
		}
	);
});

app.controller("newTaskController", function ($scope, $rootScope, scrumBoardService) {
	$rootScope.$on("createNewTask", function (event, story) {
		$scope.story = story;
	});
	$scope.saveTask = function () {
		scrumBoardService.createNewTask($scope.taskName, $scope.story.id).then(
			function (response) {
				//traverseObject(response.data);
				$scope.story.tasks.push(response.data);
			}, function (error) {
				alert(error.status + " " + error.statusText + "\nTask could not be saved!");
			}
		);
	}
});

app.controller("updateStoryController", function ($scope, $rootScope, scrumBoardService) { 
	$rootScope.$on("editStory", function (event, story) {
		$scope.points = story.points;
		$scope.desc = story.description;
		$scope.updateStory = function(){
			$scope.points = document.getElementById('storyPoints').value;
			$scope.descript = document.getElementById('storyText').value;
			story.points = $scope.points;
			story.description = $scope.desc;
			scrumBoardService.updateScrumBoardStory(story.id, story.description, story.points, story.finishTime).then(
				function(response){
					//no action necessary
				}, function(error){
					alert(error.status + " " + error.statusText + "\nThere was an error updating story!");
				}
			);
		}
	});
});

//Factory, Service, or Provider? Which to use?
app.factory("loginUserService", function($http) {
	return {
		login: function(username, password) {
			return $http.post("authenticateLogin", {username: username, password: password});
		}, 
		logout: function() {
			return $http.get("logout");
		}
	};
});

app.factory("scrumBoardService", function($http) {
	return {
		//Create
		createNewScrumBoard: function(name, startDate, duration) {
			return $http.post("createNewScrumBoard", {name: name, startDate: startDate, duration: duration});
		}, 
		createNewTask: function (desc, storyId) {
			return $http.post("createNewScrumBoardTask", {description: desc, storyId: storyId});
		}, 
		//Read
		getScrumBoardLanes: function() {
			return $http.get("getScrumBoardLanes");
		}, 
		//Update
		editExistingScrumBoard: function(id, name, startDate, duration) {
			return $http.post("editExistingScrumBoard", {id: id, name: name, startDate: startDate, duration: duration});
		}, 
		updateScrumBoardTaskStatus: function(id, status) {
			return $http.post("updateScrumBoardTask", {id: id, status: status});
		}, 
		changeScrumBoardStoryLane: function (storyId, laneId) {
			return $http.post("changeScrumBoardStoryLane", {storyId: storyId, laneId: laneId});
		}
		//Delete
		//none currently
	};
});

app.factory("getAllUsersService", function($http){
	return {
		getAllExistingUsers: function(){
			return $http.get("getAllExistingUsers");
		}, 
		addUserToBoard: function(userId, boardId){
			
			return $http.post("addUserToBoard", {userId: userId, boardId: boardId});
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

//function checkId(array){
//	
//}