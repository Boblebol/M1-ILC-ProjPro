angular.module('demo.inscription.ctrl', [])

  .controller('InscriptionCtrl', function ($scope, $http) {



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
