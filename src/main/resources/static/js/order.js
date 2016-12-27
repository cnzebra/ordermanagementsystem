angular.module('orderModule', [])
.controller('orderController', function($scope, $http) {
	
	$scope.SendData = function () {
        // use $.param jQuery function to serialize data from JSON
		
         var data ={ 
        	 to: $scope.to,
             subject: "OderConfirmation",
             text: "Your Order Successfully Placed. Order Id is 10101"
          }
            
    var res = $http.post('http://localhost:8080/mail/send',data);
    res.success(function(data, status, headers, config) {
    });
    
	};
	
});