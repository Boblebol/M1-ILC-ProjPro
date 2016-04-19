angular.module('demo.inscription.ctrl', [])

  .controller('InscriptionCtrl', function ($scope, $http) {

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
                mdp: $scope.mdp
            };
        
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

                $scope.Donnees = dataOBJ;
                   
            $http.post('http://destock.u-strasbg.fr:8080/Destock/client/add', dataOBJ, config)
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
