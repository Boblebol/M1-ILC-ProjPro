angular.module('demo.inscription.ctrl', [])

  .controller('InscriptionCtrl', function ($scope, $http, $httpParamSerializer) {

    

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
	   

		if (!/^([a-z0-9._-]+)@([a-z0-9._-]+)\.([a-zA-Z]{2,6})$/.test($scope.mail)) {
			$scope.Erreur = 'Format de mail invalide';
		        formulaireValide = false;
		}			   
		   
		          
  
              if (formulaireValide)
              {  
            console.log("Donnes envoyees au serveur : " + JSON.stringify(dataOBJ) );
            
            if ($scope.type == 'customer')
            {
            
            
                        var dataOBJ = {
                nom: $scope.nom,
                prenom: $scope.prenom,
                mail: $scope.mail,
                pseudo: $scope.pseudo,
                mdp: $scope.mdp
            };
            
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
                nom: $scope.nom,
                mail: $scope.mail,
                lattitude: 0,
                longitude: 0,
                adresse: null,
                mdp: $scope.mdp
            };
           
           
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
                
                
                if (JSON.stringify(data) == "{}")
                {
                        $scope.Resultat = "Inscription reussie !!!!!";
                }
                else
                {
                        $scope.Resultat = "Inscription echouee !!!!!";
                        $scope.Erreur = data.error;
                
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });
            
            }                
        };		           				
  });
