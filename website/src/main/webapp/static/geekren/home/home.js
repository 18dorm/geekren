"use strict";

var geekren = angular.module('geekren.home', []);

geekren.config(function($routeProvider){
	$routeProvider
			.when('/home', {
                templateUrl : 'static/geekren/home/home.html',
            })
});

var search_controller = geekren.controller('home_controller', ['$scope', '$routeParams', '$http',function ($scope, $routeParams, $http) {
		//--------------------------------
		$scope.githuber ={};
		$scope.search_style="display:none";
		$scope.loading_style="display:none";
		$scope.search_content = "";
		$scope.github_search=function(){
			$scope.loading_style="";
			$scope.search_style="display:none";
			var url = "https://api.github.com/users/" + $scope.search_content;
			$http.get(url)
			.success(function(data){
				$scope.githuber = data;
				get_repo_info($scope.githuber.repos_url);
			});
		}

		// get repo info
		var show_details = function(url){
			alert("hello");
			window.location.href="#heros";
		}

		//--------------------------------
	}]);
