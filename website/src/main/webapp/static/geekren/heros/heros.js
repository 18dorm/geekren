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
