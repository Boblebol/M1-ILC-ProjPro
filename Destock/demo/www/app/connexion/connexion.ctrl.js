angular.module('demo.connexion.ctrl', [])

  .controller('ConnexionCtrl', function ($scope, $http, $httpParamSerializer, $location, $timeout) {
	  

	  
   $scope.SendData = function () {
   $scope.Resultat = null;
   $scope.Erreur = null;
            var dataOBJ = {
                mail: $scope.mail,
                mdp: $scope.mdp
            };
        
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

                console.log("Donnes envoyees au serveur : " + JSON.stringify(dataOBJ) + " type : " + $scope.type );
   
			if (angular.equals($scope.type, "particulier"))
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
			else if (angular.equals($scope.type, "entreprise"))
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
			else
			{
			        $scope.Resultat = "Vous devez indiquer votre status ! (Entreprise ou client)";		
			}
           


            
            
            
$http(req)
            
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
				var page = function () {
					
					if($arg == true) {
						alert('Connexion réussie ! Vous allez être redirigé vers le menu.');
						$location.path('menu'); 
					}
					else {
						alert('Connexion échouée !');
					}
				  
				} 
                
                if (($scope.type == "entreprise" && data.id != null) || ($scope.type == "particulier" && data.id != null))
                {
                        $scope.Resultat = "Connexion reussie !!!!!" + data.infos;
						$arg = true;
						window.localStorage.setItem('CookieEmail', data.mail);
						window.localStorage.setItem('CookieType', $scope.type);
						window.localStorage.setItem('CookieConnecte', 'oui');
						window.localStorage.setItem('CookieId', data.id);
							$scope.CookieConnecte = 'oui';
							$scope.CookieEmail = window.localStorage.getItem('CookieEmail');
							$scope.CookieType = window.localStorage.getItem('CookieType');
							$scope.CookieId = window.localStorage.getItem('CookieId');
							alert('Chargement...');
							$timeout(page, 2000);
						//TODO : 
						
						
                }         
                else // TODO : gestion d'erreur
                {
                        $scope.Erreur = "Mauvais Login/Mot de passe";
						$arg = false;
						alert('Chargement...');
						$timeout(page, 2000);
                
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });
                  
        };
        
        
  });
 
