var geekren = angular.module('geekren', ['ui.bootstrap','ngRoute']);

geekren.config(function($routeProvider){
	$routeProvider
			.when('/', {
                templateUrl : 'static/geekren/home.html',
            })
});
