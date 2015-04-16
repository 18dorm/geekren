"use strict";

var geekren = angular.module('geekren', 
	['ui.bootstrap','ngRoute',
	'geekren.regist','geekren.home']);

geekren.config(function($routeProvider){
	$routeProvider.otherwise({ redirectTo: '/home' });
});
