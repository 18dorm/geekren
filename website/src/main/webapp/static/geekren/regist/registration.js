'use strict';
var ctx='website';
angular.module('geekren.regist', ['ngRoute'])
	.config(['$routeProvider', function($routeProvider) {
	  $routeProvider.when('/regist', {
	    templateUrl: "static/geekren/regist/registration.html",
	    controller: 'regist'
	  });
	}])

	.controller('regist', function ($scope, $http) {
		$scope.user_name="";
		$scope.user_email="";
		$scope.user_github="";
		$scope.saveData=function(){
			$scope.userNameRequired='';
			$scope.userGithubRequired='';
			$scope.emailRequired='';
			$scope.passwordRequired='';
			$scope.passwordConfirmedRequired='';
			$scope.passwordConfirmedRequiredToBeSame='';

			if (!$scope.user_email) {
			    $scope.emailRequired = '请填写Email';
	      	}

	      	if (!$scope.password) {
			    $scope.passwordRequired = '请填写密码';
	      	}

	      	if (!$scope.passwordConfirmed) {
			    $scope.passwordConfirmedRequired = '请确认填写密码';
	      	}

	      	if($scope.password && $scope.passwordConfirmed && $scope.passwordConfirmed != $scope.password){
	      		$scope.passwordConfirmedRequiredToBeSame='两次输入密码不一致';
	      	}

	      	if($scope.emailRequired == "" && $scope.passwordRequired == ""
	      		&& $scope.passwordConfirmedRequired == "" && $scope.passwordConfirmedRequiredToBeSame == "") {
	      		var form_data = {
	      			email:$scope.user_email,
	      			password:$scope.password
	      		};
	      		$http.post('api/regist' ,form_data )
		      		.success(function(data,status,headers,config){
		      			alert(data);
		      		});
	      	}
		}
	});
