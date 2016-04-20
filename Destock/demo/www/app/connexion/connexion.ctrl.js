angular.module('demo.connexion.ctrl', [])

  .controller('ConnexionCtrl', function ($scope, $http, $httpParamSerializer) {
	  
   $scope.SendData = function () {
   
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
            
            var req = {
 method: 'POST',
 url: 'http://destock.u-strasbg.fr:8080/Destock/client/con',
 headers: {
   'Content-Type': 'application/x-www-form-urlencoded'
 },
 data: $httpParamSerializer(dataOBJ)
};

$http(req)
            
            
            
            
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
                
                if (JSON.stringify(data) != "{}")
                {
                        $scope.Resultat = "Connexion reussie !!!!!";
                }
                else
                {
                        $scope.Resultat = "Connexion echouee :'( !!!!!";
                
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });
                  
        };
        
        
  });
 
