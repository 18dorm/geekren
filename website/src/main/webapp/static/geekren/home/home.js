"use strict";

var geekren = angular.module('geekren.home', []);

geekren.config(function($routeProvider){
	$routeProvider
			.when('/home', {
                templateUrl : 'static/geekren/home/home.html',
            })
});
