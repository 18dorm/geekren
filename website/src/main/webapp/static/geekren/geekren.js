"use strict";

var geekren = angular.module('geekren',
	['ui.bootstrap','ngRoute',
	'geekren.regist','geekren.home',
	'geekren.heros','geekren.heroinfo']);

geekren.config(function($routeProvider){
	$routeProvider.otherwise({ redirectTo: '/home' });
});
