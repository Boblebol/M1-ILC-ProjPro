angular.module('demo.inscription.ctrl', [])

  .controller('InscriptionCtrl', function ($scope, $http, $httpParamSerializer, $location, $timeout) {



function MyCtrl($scope) {
    $scope.gPlace;
}

   $scope.SendData = function () {
                var formulaireValide = true;
                        $scope.Resultat = null;
                        $scope.Erreur = null; 
           // use $.param jQuery function to serialize data from JSON 

        
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

     
	   if($scope.mdp != $scope.mdp_confirm) {
		   $scope.Erreur = 'Vous n"avez pas renseigné le même mot de passe !';
		   formulaireValide = false;
	   }      
	   
	 	   if($scope.mail != $scope.mail_confirm) {
		   $scope.Erreur = 'Vous n"avez pas renseigné le même mail !';
		   formulaireValide = false;
	   }
	   

		if (!/^([a-zA-Z0-9._-]+)@([a-zA-Z0-9._-]+)\.([a-zA-Z]{2,6})$/.test($scope.mail)) {
			$scope.Erreur = 'Format de mail invalide';
		        formulaireValide = false;
		}			   
		   
		          
  
        if (formulaireValide)
        {  
                     
            if ($scope.type == 'customer')
            {
				
				var dataOBJ = {
					nom: $scope.nom,
					prenom: $scope.prenom,
					mail: $scope.mail,
					pseudo: $scope.pseudo,
					mdp: $scope.mdp
				};
				
				console.log("Donnes envoyees au serveur customer: " + JSON.stringify(dataOBJ) );
				
			   var req = {
					method: 'POST',
					url: 'http://destock.u-strasbg.fr:8080/Destock/client/add',
					headers: {
							'Content-Type': 'application/x-www-form-urlencoded'
							},
					data: $httpParamSerializer(dataOBJ)
				};          
            }
			
            else if ($scope.type == 'provider')
            {
				var dataOBJ = {
				mail: $scope.mail,
				mdp: $scope.mdp,
                nom: $scope.nom,            
                latitude: 0,
                longitude: 0,
                adresse: $scope.chosenPlace
				};
           
           console.log("Donnes envoyees au serveur provider: " + JSON.stringify(dataOBJ) );
		   
           var req = {
				method: 'POST',
				url: 'http://destock.u-strasbg.fr:8080/Destock/magasin/add',
				headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
						},
				data: $httpParamSerializer(dataOBJ)
			};         
           }
		   
           else
           {
                $scope.Erreur = "ERREUR type";
           }
   
			
	
			
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
				
                if (JSON.stringify(data) == "{}")
                {
                        $arg = true;
                        $scope.Resultat = "Inscription reussie !!!!!";	
						alert('Chargement...');
						$timeout(page, 3000);
                }
                else
                {
                        $arg = true;
                        $scope.Resultat = "Inscription echoue !!!!!";
						alert('Chargement...');
						$timeout(page, 3000);	
                        $scope.Erreur = data.error;
                
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });
            
            }                
        };		           				
  })
  
  .directive('googleplace', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, model) {
            var options = {
                types: [],
                componentRestrictions: {}
            };
            scope.gPlace = new google.maps.places.Autocomplete(element[0], options);

            google.maps.event.addListener(scope.gPlace, 'place_changed', function() {
                scope.$apply(function() {
                    model.$setViewValue(element.val());                
                });
            });
        }
    };
})
  
  ;

