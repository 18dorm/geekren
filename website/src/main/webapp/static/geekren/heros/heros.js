"use strict";

var geekren_heros = angular.module('geekren.heros', []);

geekren_heros.config(function($routeProvider){
	$routeProvider
			.when('/heros', {
      		templateUrl : 'static/geekren/heros/heros.html',
      })
});
