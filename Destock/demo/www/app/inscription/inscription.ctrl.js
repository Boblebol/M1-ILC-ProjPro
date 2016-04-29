angular.module('demo.inscription.ctrl', [])

  .controller('InscriptionCtrl', function ($scope, $http, $httpParamSerializer) {
	
	//Verification champs remplis
   $scope.veriftype= function () {
	   if($scope.type == "provider" || $scope.type == "customer") {
		   $scope.erreur = 'Selection valide !!';
		   return false;
	   }
		   
	   else {
		   $scope.erreur = 'Selection invalide !';
			return true;
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
			
			var reqq = {
				method: 'POST',
				url: 'http://192.168.1.28:8100',
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
                        $scope.Resultat = "Inscription reussie !!!!!";
						$state.go('https://docs.angularjs.org');
                }
                else
                {
                        $scope.Resultat = "Inscription echouee !!!!!";
                
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });                
        };		           				
  });