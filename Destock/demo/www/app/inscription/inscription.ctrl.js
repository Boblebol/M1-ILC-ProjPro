angular.module('demo.inscription.ctrl', [])
	

	  
  .controller('InscriptionCtrl', function ($scope, $http, $httpParamSerializer, $location, $timeout) {
	
	//Verification champs remplis
   $scope.veriftype= function () {
	   if($scope.type == "provider" || $scope.type == "customer") {
		   $scope.erreur = 'Selection valide !!';
		   return true;
	   }
		   
	   else {
		   $scope.erreur = 'Selection invalide !';
			return false;
	   }
   };
   
   
	//Verification MAIL
	$scope.verifSyntMail = function() {
		
		if (/^([a-z0-9._-]+)@([a-z0-9._-]+)\.([a-z]{2,6})$/.test($scope.mail)) {
			$scope.erreur = 'MAIL VALIDE !';
			return true;
		}	 
			
		else 
		{
			$scope.erreur = 'MAIL INVALIDE !';
			return false;	
		}
	}
	
	//Verification mail et mail_confirm
   $scope.verifMail= function () {
	   if($scope.mail != $scope.mail_confirm) {
		   $scope.erreur = 'Vous n"avez pas renseigné le même mail !';
		   return false;
	   }
		   
	   else {
		   $scope.erreur = 'Vous même mail !';
			return true;
	   }
   };
	
    //Verification mot de passe
   $scope.verifMdp= function () {
	   if($scope.mdp != $scope.mdp_confirm) {
		   $scope.erreur = 'Vous n"avez pas renseigné le même mot de passe !';
		   return false;
	   }
		   
	   else {
		   $scope.erreur = 'Vous même mot de passe !';
			return true;
	   }
   };
   
   //
   $scope.SendData = function () {
   
           // use $.param jQuery function to serialize data from JSON 
            var dataOBJ = {
                nom: $scope.nom,
                prenom: $scope.prenom,
                mail: $scope.mail,
                pseudo: $scope.pseudo,
                mdp: $scope.mdp,
				type: $scope.type
            };
        
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

                
            console.log("Donnes envoyees au serveur : " + JSON.stringify(dataOBJ) );
            
            var req = {
				method: 'POST',
				url: 'http://destock.u-strasbg.fr:8080/Destock/client/add',
				headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
						},
				data: $httpParamSerializer(dataOBJ)
			};
			
			$http(req)
            
			
			
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
				var page = function () {
					
					if($arg == true) {
						alert('Inscription réussie ! Vous allez être redirigé vers le menu.');
						$location.path('menu'); 
					}
					else {
						alert('Inscription échouée !');
					}
				  
				}  
			  
                if (JSON.stringify(data) != "{}")
                {
						$arg = false;
                        $scope.Resultat = "Inscription echouee !!!!!";	
						alert('Chargement...');
						$timeout(page, 3000);
                }
                else
                {
					    $arg = true;
                        $scope.Resultat = "Inscription reussie !!!!!";
						alert('Chargement...');
						$timeout(page, 3000);						
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });       

			
        };		           				
  });