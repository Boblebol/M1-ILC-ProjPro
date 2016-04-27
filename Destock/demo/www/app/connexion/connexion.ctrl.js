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
   
			if (angular.equals($scope.type, "particulier\""))
			{
			
			 var req = {
 method: 'POST',
 url: 'http://destock.u-strasbg.fr:8080/Destock/client/con',
 headers: {
   'Content-Type': 'application/x-www-form-urlencoded'
 },
 data: $httpParamSerializer(dataOBJ)
};			
			}
			else
			{
			
			 var req = {
 method: 'POST',
 url: 'http://destock.u-strasbg.fr:8080/Destock/magasin/con',
 headers: {
   'Content-Type': 'application/x-www-form-urlencoded'
 },
 data: $httpParamSerializer(dataOBJ)
};			
			}
			
           


$http(req)
            
            
            
            
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
                
                if (JSON.stringify(data) != "{}")
                {
                        $scope.Resultat = "Connexion reussie !!!!!";
						window.localStorage.setItem('CookieEmail', data.mail);
						window.localStorage.setItem('CookieType', $scope.type);
						window.localStorage.setItem('CookieConnecte', 'oui');
							$scope.CookieConnecte = 'oui';
							$scope.CookieEmail = window.localStorage.getItem('CookieEmail');
							$scope.CookieType = window.localStorage.getItem('CookieType');
						//TODO : 
						
						
                }
                else // TODO : gestion d'erreur
                {
                        $scope.Resultat = "<span style='color:red;'>Mauvais Login/Mot de passe</span>";
                
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });
                  
        };
        
        
  });
 
