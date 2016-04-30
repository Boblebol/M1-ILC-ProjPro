angular.module('demo.offres.ctrl', [])

  .controller('OffresCtrl', function ($scope, $http,$httpParamSerializer, $stateParams) {
  
  
  $scope.Voir = function ()
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
  
  
   $scope.GetData = function () {
   
    
        

                console.log("Recuperation des donnees");
            
            var req = {
 method: 'GET',
 url: 'http://destock.u-strasbg.fr:8080/Destock/promo/liste',
 headers: {
   'Content-Type': 'application/x-www-form-urlencoded'
 }
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
        
     
  });
  
  
