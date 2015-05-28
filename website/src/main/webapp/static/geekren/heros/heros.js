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
var search_controller = geekren_heros.controller('heros_search', function ($scope, $http) {
		//--------------------------------
		$scope.githuber = {
			url:"https://github.com/peterfuture",
			icon:"https://avatars0.githubusercontent.com/u/3294183?v=3&amp;s=460",
			id:"peter-s",
			name:"peterfuture",
			email:"peter_future@outlook.com",
			company:"18dorm",
			location:"Shanghai.China",
			github_in_time:"2013-01-17",
			followers:10,
			starred:10,
			following:5,
			tags:["多媒体","业余前端"]
		};
		$scope.search_style="display:none";
		$scope.search_content = "peterfuture";
		$scope.github_search=function(){
			//alert("searching:" + $scope.search_content);
			$scope.search_style="";
		}
		//--------------------------------
	});
