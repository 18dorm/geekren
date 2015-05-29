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
				// display user info
				$scope.search_style="";
			});
		}
		//--------------------------------
	}]);
