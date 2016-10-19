<!DOCTYPE html>
<html lang="en-US">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script>
var app = angular.module("myApp", []);
app.controller('myCtrl', function($scope, $http) {   
    $scope.submit = function() {    	
    	$scope.response = "";
    	var dataObj = {
    			name : $scope.name,
				description : $scope.description,
				price : $scope.price,
				currency: $scope.currency
		};	    	
    	$http.post('/api/offers', dataObj).then(function(response) {    		
            $scope.response = "Offer created sucessfully!";            
        },
        function(response) {           	
            $scope.response = "Failed to create offer! " +  response.data.message ;
        });
      }
    });
</script>
<body ng-app="myApp" ng-controller="myCtrl">
   <div  align="center">
    <h2> Create Offer </h2>  
        <form ng-submit="submit()" name="offersForm" >
           	<table>        	
            <tr>
                <td><label id="name">Name:</label></td>
                <td><input name="name" ng-model="name" size="20" required/>
                <div ng-show="offersForm.name.$dirty">
					<span ng-show="offersForm.name.$error.required">This is a required field</span>
					<span ng-show="offersForm.name.$invalid">This field is invalid </span>
				</div>
				</td>
            </tr>  
            <div>  
            <tr>        
                <td><label id="description">Description:</label></td>
                <td><input name="description" ng-model="description" size="20"/></td>            
            </tr>
            </div>
            <div>
            <tr>
                <td><label id="price">Price:</label></td>
                <td><input name="price" ng-model="price" size="20" type="number" required/></td> 
                <div ng-show="offersForm.price.$touched&&offersForm.$dirty">
					<span ng-show="offersForm.price.$error.required">This is a required field</span>
					<span ng-show="offersForm.price.$invalid">This field is invalid </span>
                </div>                          
            </tr>
            </div>
            <tr>             
                <td><label id="currency">Currency:</label></td>
                <td><input name="currency" ng-model="currency" size="20" type="string" min="3" maxlength="3"/></td>   
                <div ng-show="offersForm.$dirty">
                      <span ng-show="offersForm.currency.$error.required">This is a required field</span>
                      <span ng-show="offersForm.currency.$invalid">This field is invalid </span>
                      <span ng-show="offersForm.currency.$error.minlength">Minimum length required is 3</span>
                </div>         
           </tr>
            <tr>           
           </table>
            <div align="center">
                <input type="submit" value="Create" />
            </div>
        </form>        
    </div>  
  <center><pre>{{response}}</pre></center>
</form>
</body>
</html>