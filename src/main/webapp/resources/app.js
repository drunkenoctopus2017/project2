/**
 * Application module and control for app.html.
 * Written verbosely for clarity (hopefully).
 */

/**
 * NOTE: This file currently contains console.logs and dummy data/methods. These
 * should be deleted before being pushed to develop branch. If you use this file
 * to build new features, delete all console.logs and dummy methods from your
 * source (including the ones already here). And also delete this comment and
 * others.
 * 
 * Only descriptive/explanatory comments should remain.
 */

var app = angular.module("MainModule", ["ngRoute","ng-fusioncharts"]);

// This is an application-wide constant. If our url path changes, we can
// maintain it here.
app.constant("urlBase", "resources/");

// This is an application-wide variable.
app.value("loginUser", {
	firstName: "not logged in", 
	lastName: ""
	// Add more fields here only if necessary.
});

app.value("loginUserBoards", []);
app.value("allUsers", []);

// for controlling what shows up on createScrumBoardView
// false, if you're creating a board
// true, if you're editing a board
app.value("editing", {
	value: false,	
});

// variable that stores the id of a single board
// possibly useful for looking at a single board,
// editing a single board, etc
app.value("currentBoard", {
	id: 0,
	name: "no board selected",
	startDate: undefined,
	duration: 0
	// add properties as necessary
}); 

// The authorization level of the user.
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
	}).when("/createOrEditScrumBoard", {
		templateUrl: urlBase + "createOrEditScrumBoardView.html", 
		controller: "createOrEditScrumBoardController"
	}).when("/viewScrumBoard", {
		templateUrl: urlBase + "scrumBoardView.html",
		controller: "scrumBoardViewController"
	}).when("/getAllUsers", {
		templateUrl: urlBase + "addUserView.html", 
		controller: "getAllUsersController"
	});
});

app.controller("navbarController", function($scope, $rootScope, $location, loginUser, loginUserBoards, loginUserService) {
	$scope.logout = function(){
		loginUser.firstName = "not logged in";
		loginUser.lastName = "";
		while(loginUserBoards.length > 0){
			loginUserBoards.pop();	
		}
		loginUserService.logout().then(
			function(response){
				$rootScope.loggedIn = false; // make the dropdown thing on
												// the top right disappear
				$location.path("/");
			},
			function(error){
				alert(error.status + " " + error.statusText + "\nThere was an error logging out!");
			}
		);
  }
});

app.controller("loginController", function($scope, $rootScope, $location, loginUserService, loginUser, loginUserRole, loginUserBoards) {
	$scope.login = function() {
		// note that this anonymous function only has one line.
		loginUserService.login($scope.username, $scope.password).then(
			function (response) {
				// nested anonymous function for success
				/*
				 * The response object has these properties: data –
				 * {string|Object} – The response body transformed with the
				 * transform functions. status – {number} – HTTP status code of
				 * the response. headers – {function([headerName])} – Header
				 * getter function. config – {Object} – The configuration object
				 * that was used to generate the request. statusText – {string} –
				 * HTTP status text of the response. xhrStatus – {string} –
				 * Status of the XMLHttpRequest (complete, error, timeout or
				 * abort).
				 */
				// Save the data to the loginUser value, declared above.
				// Update if necessary to store more info, but the name is
				// currently all that is necessary.
				// All other data should be stored on the server.
				loginUser.firstName = response.data.firstName;
				loginUser.lastName = response.data.lastName;
				$rootScope.loggedIn = true; // makes the dropdown thing on the
											// top right appear once you're
											// logged in
				loginUserRole.id = response.data.role.id;
				loginUserRole.roleName = response.data.role.roleName;
				while(response.data.scrumBoards.length > 0) {
					loginUserBoards.push(response.data.scrumBoards.pop());
				}
				$location.path("/mainMenu");
			}, function (error) {
				// The error object above has:
				// data,
				// status,
				// config,
				// statusText. (There are special rules about whether statusText
				// is passed - browsers, mobile or not, web server etc.)
				alert(error.status + " " + error.statusText + "\nThere was an error logging in!");
			}
		);
	}
});


app.controller("mainMenuController", function($scope, $rootScope, $location, loginUser, loginUserRole, loginUserBoards, editing, currentBoard) {
	// reset the value of editing, so that it doesn't stay true forever after
	// you edit something
	$scope.percentComplete = 90;
	editing.value = false;
	// clear currentBoard
	currentBoard.id = 0;
	currentBoard.name = "no board selected";
	currentBoard.startDate = undefined;
	currentBoard.duration = 0;
	// fill in html field
	$rootScope.firstName = loginUser.firstName;
	$rootScope.lastName = loginUser.lastName;
	$scope.boards = loginUserBoards;
	// get the value for b's progress bar
	$scope.percentComplete = function(b){
		let sumPts = 0;
		let sumDone = 0;
		for(var i = 0; i < b.stories.length;i++){
			sumPts += b.stories[i].points;
			if(b.stories[i].laneId == 60){
				sumDone += b.stories[i].points;
			}
		}
		let percent = Math.floor((sumDone*100)/sumPts);
		return percent;
	}
	$scope.role = loginUserRole.id;
	$scope.createScrumBoard = function() {
		$location.path("/createOrEditScrumBoard");
	}
	$scope.viewBoard = function(b) {
		$rootScope.currentScrumBoard = b
		$location.path("/viewScrumBoard");
	}
	$scope.editScrumBoard = function(board) {
		editing.value = true;
		// set the current board properties to the properties of board
		// associated with the button that called this function
		currentBoard.id = board.id;
		currentBoard.name = board.name;
		currentBoard.startDate = board.startDate;
		currentBoard.duration = board.duration;
		$location.path("/createOrEditScrumBoard");
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

app.controller("createOrEditScrumBoardController", function($scope, $location, scrumBoardService, loginUser, loginUserBoards, editing, currentBoard) {
	$scope.editing = editing.value;
	if(editing.value){
		// fill in the fields with the old values
		$scope.sbName = currentBoard.name;
		// have to recast it as a Date in order for it to show up properly
		$scope.startDate = new Date(currentBoard.startDate);
		$scope.duration = currentBoard.duration;
	}
	$scope.boardName = currentBoard.name;
	$scope.createOrEdit = function() {
		// change functionality depending on value of editing
		if(!editing.value){
			scrumBoardService.createNewScrumBoard($scope.sbName, $scope.startDate, $scope.duration).then(
				function (response) {
					// Refresh the data for the main menu without doing another
					// server request (because you don't need to);
					loginUserBoards.push(response.data);
					$location.path("/mainMenu");
				}, function (error) {
					alert(error.status + " " + error.statusText + "\nThere was an error creating this board!");
				}
			);
		} else {
			// if any of the input is empty, use the previous value
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
					// Refresh the data for the main menu without doing another
					// server request (because you don't need to);
					// need to remove the outdated board from JS-side list
					loginUserBoards.forEach(function (board, index, array) {
						// find the outdated board
						if(board.id == currentBoard.id){
							// remove it
							loginUserBoards.splice(index, 1);
						}	
					});
					// adds the new one to the front of the JS-side list
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
			function(response){
				// no action necessary
			},
			function (error) {
				alert(error.status + " " + error.statusText + "\nThere was an error changing lanes!");
			}
		);
	}
	$scope.updateTask = function (task) {
		scrumBoardService.updateScrumBoardTaskStatus(task.id, task.status).then(
			function (response) {
				// no action necessary
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
	// make the burndown chart data
	let b = $rootScope.currentScrumBoard;
	$scope.burndownData = {
		chart:{
			caption: ""+b.name,
		    subCaption: "Burndown Chart",
		    xAxisName: "Days",
		    yAxisName: "Points",
		    theme: "fint",
	    	showValues: "0"
		},
		data:[]
	};
	// if current board has a startDate and stories
	if(b.stories.length > 0 && b.startDate != undefined){
		let doneStories = [];
		for(var t = 0;t < b.stories.length; t++){
			// if it's in done lane, do this:
			if(b.stories[t].laneId == 60){
				// add it to array of finished stories
				doneStories.push(b.stories[t]);
			}
		}
		// sort the done stories by days from b.startDate, ascending so earlier completed stories are first
		doneStories.sort(function(a, c){
			return daysBetween(new Date(b.startDate), new Date(a.finishTime)) - daysBetween(new Date(b.startDate), new Date(c.finishTime));
			}
		);
		let doneStoryCounter = 0;
		let sumPts = 0;
		for(var i = 0; i < b.stories.length;i++){
			sumPts += b.stories[i].points;
		}
		let prevValue = sumPts;
		for(var i = 1;i < b.duration+1; i++){
			// check the next story to see if it matches current day from startDate 
//			console.log("this is the board's startDate: "+b.startDate)
			if(doneStoryCounter < doneStories.length){
				let difference = daysBetween(new Date(b.startDate), new Date(doneStories[doneStoryCounter].finishTime));
				if(difference == i){
					// if it does AND you haven't already gone through all the finished stories
					// add a coordinate with an updated point level, representing a day in the chart where points got burned down
					prevValue = prevValue - doneStories[doneStoryCounter].points;
					// you've calculated burndown for this story, can start looking at next one
					doneStoryCounter++;
					// if there is a next one
					if(doneStoryCounter < doneStories.length){
						let nextDiff = daysBetween(new Date(b.startDate), new Date(doneStories[doneStoryCounter].finishTime));
						// if it also finished on the same day
						if(nextDiff == difference){
							// restart the process for this day
							i--;
						}
						else{
							// if it didn't then just make a point
							let coordinate = {label:"",value:""};
							coordinate.label = ""+i;
							coordinate.value = ""+prevValue;
							$scope.burndownData.data.push(coordinate);
						}
					}
					else{
						// if it's the last story, don't need to check for anymore stories that are possibly done on the same day
						let coordinate = {label:"",value:""};
						coordinate.label = ""+i;
						coordinate.value = ""+prevValue;
						$scope.burndownData.data.push(coordinate);
					}
				}
				else{
					// if it doesn't OR if you're already finished with all the done stories
					// add a filler coordinate with the same value as before for this day
					// only if there are still stories to be checked
					let coordinate = {label:"",value:""};
					coordinate.label = ""+i;
					coordinate.value = ""+prevValue;
					$scope.burndownData.data.push(coordinate);
					
					//if there are no more stories to be checked, don't add any more coordinates
				}
			}
			else{
				// if done with all stories and still have duration left
				// then fill with empty points
				let coordinate = {label:"",value:""};
				coordinate.label = ""+i;
				coordinate.value = "";
				$scope.burndownData.data.push(coordinate);
			}
		}
	}
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
		// Create
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
		// Update
		editExistingScrumBoard: function(id, name, startDate, duration) {
			return $http.post("editExistingScrumBoard", {id: id, name: name, startDate: startDate, duration: duration});
		}, 
		updateScrumBoardStory: function(storyId, storyDescription, storyPoints, storyFinishTime) {
			return $http.post("updateScrumBoardStory", {id: storyId, description: storyDescription, points: storyPoints, finishTime: storyFinishTime});
		}, 
		updateScrumBoardTaskStatus: function(id, status) {
			return $http.post("updateScrumBoardTask", {id: id, status: status});
		}, 
		changeScrumBoardStoryLane: function (storyId, laneId) {
			return $http.post("changeScrumBoardStoryLane", {storyId: storyId, laneId: laneId});
		}
		// Delete
		// none currently
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

// DON'T DELETE THIS, using to calculate burndown chart data
function daysBetween( date1, date2 ) {
	//Get 1 day in milliseconds
	var one_day=1000*60*60*24;

	// Convert both dates to milliseconds
	var date1_ms = date1.getTime();
	var date2_ms = date2.getTime();

	// Calculate the difference in milliseconds
	var difference_ms = date2_ms - date1_ms;
	    
	// Convert back to days and return
	return Math.floor(difference_ms/one_day); 
}

//TODO delete before pushing to master
function walkTheDOM(node, func) {
	func(node);
	node = node.firstChild;
	while (node) {
	    walkTheDOM(node, func);
	    node = node.nextSibling;
	}
}

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
