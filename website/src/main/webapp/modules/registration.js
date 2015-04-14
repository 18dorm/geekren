var geekren = angular.module('geekren', ['ui.bootstrap','ngRoute']);

geekren.config(function($routeProvider){
	$routeProvider
			.when('/', {
                templateUrl : 'modules/registration.html',
                controller  : 'regist'
            })


});

geekren.controller('regist', function ($scope) {
	$scope.email="rosicky"
	$scope.saveData=function(){
		$scope.emailRequired='';
		$scope.passwordRequired='';
		$scope.passwordConfirmedRequired='';
		$scope.passwordConfirmedRequiredToBeSame='';

		if (!$scope.email) {
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

	}
}

);