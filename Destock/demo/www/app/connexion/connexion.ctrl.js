angular.module('demo.connexion.ctrl', [])

  .controller('ConnexionCtrl', function ($scope, $http) {
	  
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

                console.log("Donnes envoyees au serveur : " + JSON.stringify(dataOBJ) );
                   
            $http.post('http://destock.u-strasbg.fr:8080/Destock/client/con', dataOBJ, config)
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });
                  
        };
        
        
  });
  
 
        
//action="http://destock.u-strasbg.fr:8080/Destock/client/add"
