angular.module('itemModule', [])
.controller('orderController', $scope.submit=function($scope, $http) {
	alert("inside http");
    $http.get('http://localhost:8080/ordrer/order').
        then(function(response) {
        	alert("After Response")
            $scope.greeting = response.data;
        });
});