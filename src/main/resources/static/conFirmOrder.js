 angular.module("myOrder",[ ])
			  .controller("myOrderController", function($scope,$http){
				var selectedItems  = sessionStorage.getItem("SelectedProduct");
				$scope.selectedItems = JSON.parse(selectedItems);
      		    $scope.getMail={
				     email: sessionStorage.getItem("loginmaild")
      		    
      		  };
      		    alert($scope.getMail.email);
      		   /* $scope.selectedItemList=function(){
      		  angular.forEach( $scope.selectedItems,function(item) {
  				var selectedList={
  						
  						total_price: item.price
  						
  				};
  			});
			  }*/
      		 
      		  $scope.storeDetails=function(item){
      		    	 alert("inside storedetails");
           		    
      		    	var myOrderdate = new Date(new Date().getTime()+(5*24*60*60*1000));
      		    	
      		    	var orderDetails={
      		    			//total_price : $scope.selectedItemList.selectedList.total_price,
      		    			//name: item.name,
      		    			delivery_address: $scope.address,
      		    			order_date: new Date(),
      		    			delivery_date: myOrderdate,
      		    			email: $scope.getMail.email
      		    	};
      		    	
      		    	$http.post("http://localhost:8080/Customer/create", orderDetails)
					.success(function(response) {
						 alert("inside post");
						$scope.orderResponse = response;
						
					});
      		    
      		    	window.location="success.html";
      		    };
      		
      		    
			  });