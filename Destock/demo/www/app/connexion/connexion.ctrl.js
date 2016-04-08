angular.module('demo.connexion.ctrl', [])

  .controller('ConnexionCtrl', function ($scope, $http) {
	
   
   $scope.mail = {
        text : 'me@example.com',
		regex :'[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'
      };
	  
   $scope.SendData = function () {
   
           // use $.param jQuery function to serialize data from JSON 
            var dataOBJ = {
                mail: $scope.mail,
                mdp: $scope.mdp
            };
        
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

                $scope.Donnees = dataOBJ;
                   
            $http.post('http://destock.u-strasbg.fr:8080/Destock/client/con', dataOBJ, config)
            .success(function (data, status, headers, config) {
                $scope.PostDataResponse = data;
            })
            .error(function (data, status, header, config) {
                $scope.ResponseDetails = "Data: " + data +
                    " Status: " + status;
            });
                  
        };
        
        
  });
  
 
        
//action="http://destock.u-strasbg.fr:8080/Destock/client/add"
