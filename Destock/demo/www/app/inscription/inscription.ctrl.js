angular.module('demo.inscription.ctrl', [])

  .controller('InscriptionCtrl', function ($scope, $http, $httpParamSerializer, $location, $timeout) {

$scope.reloadRoute = function() {
   location.reload(); 
}

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
        
                console.log("type : " + $scope.type);
                     
            if ($scope.type == 'customer')
            {
				
				var dataOBJ = {
					nom: $scope.nom,
					prenom: $scope.prenom,
					mail: $scope.mail,
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
                              
                              
     
			
			$http(req)
            
			
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
                var page = function () {
					
					if($arg == true) {
						$scope.Resultat = "Inscription reussie !!!!!";	
						$location.path('menu'); 						
					}
					
					else {
						$scope.Resultat = "Inscription echoue !!!!!";
						$scope.Erreur = data.error;  
					}				  
				}  
				
                if (JSON.stringify(data) == "{}")
                {
                        $arg = true;                      
						$scope.Loading = "Chargement...";
						$timeout(page, 2000);				
                }
                else
                {
                        $arg = false;                      
						$scope.Loading = "Chargement...";
						$timeout(page, 2000);						                                   
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });                               
                              
                              
                              
                              
            }
			
            else if ($scope.type == 'provider')
            {
            
            
            
     
   

var geocoder = new google.maps.Geocoder();
  geocoder.geocode({'address': $scope.chosenPlace}, function(results, status) {
  
  
      console.log("dans le geocode " + $scope.chosenPlace);
  
    if (status === google.maps.GeocoderStatus.OK) {
    

      console.log("Status geocode OK");
      console.log(results[0].geometry.location);
      
      
      
      
      
      

            
				var dataOBJ = {
				mail: $scope.mail,
				mdp: $scope.mdp,
                nom: $scope.nom,            
                latitude: results[0].geometry.location.lat(),
                longitude: results[0].geometry.location.lng(),
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
     
			
			$http(req)
            
			
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
                var page = function () {
					
					if($arg == true) {
						$scope.Resultat = "Inscription reussie !!!!!";	
						$location.path('menu'); 						
					}
					
					else {
						$scope.Resultat = "Inscription echoue !!!!!";
						$scope.Erreur = data.error;  
					}				  
				}  
				
                if (JSON.stringify(data) == "{}")
                {
                        $arg = true;                      
						$scope.Loading = "Chargement...";
						$timeout(page, 2000);				
                }
                else
                {
                        $arg = false;                      
						$scope.Loading = "Chargement...";
						$timeout(page, 2000);						                                   
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            }); 
            
            
                        
                } else {
      alert('Geocode was not successful for the following reason: ' + status);
      var stop = true;
    }
    
            });
           }
		   
           else
           {
                $scope.Erreur = "ERREUR type";
           }       
               
    

     
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

