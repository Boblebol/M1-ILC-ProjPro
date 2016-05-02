angular.module('demo.admin.ctrl', [])

  .controller('AdminCtrl', function ($scope, $http,$httpParamSerializer, $location, $stateParams) {
	  
	    $scope.Init = function(){

      						if (window.localStorage.getItem('CookieEmail') != null)
						{
							$scope.CookieConnecte = 'oui';
							$scope.CookieEmail = window.localStorage.getItem('CookieEmail');
							$scope.CookieType = window.localStorage.getItem('CookieType');
							$scope.CookieId = window.localStorage.getItem('CookieId');
						}    
						
						
						}
						

	  $scope.GetData = function () {
   
			$scope.CookieId = window.localStorage.getItem('CookieId');
            var dataOBJ = {idMag: $scope.CookieId};
        

                console.log("Recuperation des donnees");
            
            var req = {
			 method: 'POST',
			 url: 'http://destock.u-strasbg.fr:8080/Destock/promo/listeMag',
			 headers: {
			   'Content-Type': 'application/x-www-form-urlencoded'
			 },
			 data : $httpParamSerializer(dataOBJ)
			};

			$http(req)
                                           
            .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
                        $scope.Resultat = data;
                        $scope.nomprod = data.nomProduit;
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });
                  
        };
		
		
		
	$scope.GetDataPromo = function ()
  {
        $scope.id = $stateParams.id;
   
            var dataOBJ = {
                idPromo: $scope.id
            };      
        
        

                console.log("Envoye au serveur : " + JSON.stringify(dataOBJ));
            
            var req = {
 method: 'POST',
 url: 'http://destock.u-strasbg.fr:8080/Destock/promo/det',
 headers: {
   'Content-Type': 'application/x-www-form-urlencoded'
 },
 data : $httpParamSerializer(dataOBJ)
};

$http(req)
                  .success(function (data, status, headers, config) {
                console.log("Donnes recues par le serveur : " + JSON.stringify(data) );
                
                        $scope.Resultat = data;
                
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });            
        
  };
		
		
		
		
		
		 $scope.AjoutOffre = function () {
			
           // use $.param jQuery function to serialize data from JSON 


            var dataOBJ = {
                ref: $scope.ref,
                marque: $scope.marque,
				nomprod: $scope.nomprod,
                categorie: $scope.categorie,
				description: $scope.description,				
                ancienprix: $scope.ancienprix,
                nouveauprix: $scope.nouveauprix,				
				duree: $scope.duree,
				active: $scope.active,
				idmag: $scope.CookieId
            };
			
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

                
            console.log("Donnes envoyees au serveur : " + JSON.stringify(dataOBJ) );
            
            var req = {
				method: 'POST',
				url: 'http://destock.u-strasbg.fr:8080/Destock/promo/add',
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
						$arg = false;
                        $scope.Resultat = "Offre enregistrer !!!!!";
                }
                else
                {
					    $arg = true;
                        $scope.Resultat = "Offre non enregistrer !!!!!";	
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });       

			
        };		
		
		
		
		$scope.ModifOffre = function ($id, $mag) {
   
           // use $.param jQuery function to serialize data from JSON 
            var dataOBJ = {
                ref: $scope.ref,
                marque: $scope.marque,
				nomprod: $scope.nomprod,
                categorie: $scope.categorie,
				description: $scope.description,				
                ancienprix: $scope.ancienprix,
                nouveauprix: $scope.nouveauprix,				
				duree: $scope.duree,
				active: $scope.active,
				idmag: $scope.idmag
            };
        
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

                
            console.log("Donnes envoyees au serveur : " + JSON.stringify(dataOBJ) );
            
            var req = {
				method: 'POST',
				url: 'http://destock.u-strasbg.fr:8080/Destock/promo/add',
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
						$arg = false;
                        $scope.Resultat = "Offre enregistrer !!!!!";
                }
                else
                {
					    $arg = true;
                        $scope.Resultat = "Offre non enregistrer !!!!!";	
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });       

			
        };
		
		
		
        $scope.Ajout = function () { 
           $location.path('admin/ajouter');     		
        };
		
		
		
		
		
		
		
		$scope.Modif = function () { 
			
           $location.path('admin/modifier');  
			
        };
		
		
		
		
		
		
		
		$scope.Del = function ($ref, $mag) { 
           // use $.param jQuery function to serialize data from JSON 
            var dataOBJ = {
                ref: $ref,
				idmag: $mag
            };
        
		
            var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }

                
            console.log("Donnes envoyees au serveur pour supprimer: " + JSON.stringify(dataOBJ) );
            
            var req = {
				method: 'POST',
				url: 'http://destock.u-strasbg.fr:8080/Destock/promo/del',
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
						$arg = false;
                        $scope.Resultat = "Suppression appliquer !!!!!";
                }
                else
                {
					    $arg = true;
                        $scope.Resultat = "Suppression non appliquer  !!!!!";	
                }
                
                
            })
            .error(function (data, status, header, config) {
                console.log("ERREUR : " + JSON.stringify(data) + JSON.stringify(status) );
            });       
		  		
        };
     
  });  
	
