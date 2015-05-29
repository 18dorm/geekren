"use strict";

var geekren_heros = angular.module('geekren.heros', []);

geekren_heros.config(function($routeProvider){
	$routeProvider
			.when('/heros', {
      		templateUrl : 'static/geekren/heros/heros.html',
      })
});

var geekren_heroinfo = angular.module('geekren.heroinfo', []);

geekren_heroinfo.config(function($routeProvider){
	$routeProvider
			.when('/heroinfo', {
      		templateUrl : 'static/geekren/heros/heroinfo.html',
      })
});

/*
geekren_heros search controller
*/
var search_controller = geekren_heros.controller('heros_search', ['$scope', '$routeParams', '$http',function ($scope, $routeParams, $http) {
		//--------------------------------
		$scope.githuber ={};
		$scope.search_style="display:none";
		$scope.search_content = "";
		$scope.github_search=function(){
			var url = "https://api.github.com/users/" + $scope.search_content;
			$http.get(url)
			.success(function(data){
				$scope.githuber = data;
				get_repo_info($scope.githuber.repos_url);
				// display user info
				$scope.search_style="";
			});
		}

		// get repo info
		var get_repo_info = function(url){
			$http.get(url)
			.success(function(data){
				$scope.githuber.repos = data;
				var event_url = $scope.githuber.events_url;
				get_events(event_url.substr(0, event_url.length - 10));
			});
		}

		// get events
		var get_events = function(url){
			$http.get(url)
			.success(function(data){
				if(data.length > 5)
					data.length=5;
				$scope.githuber.events = data;
				//alert($scope.githuber.events[0].type);
			});
		}

		//--------------------------------
	}]);
