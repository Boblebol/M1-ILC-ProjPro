'use strict';
var utilisateursDatabase = [{'login':'ngcode','password':'tutoriel'}];

/* Controllers */
function LoginController($scope) {
    $scope.connexion = function(){
        var connected = false;
        for(var i in utilisateursDatabase){
            var utilisateur = utilisateursDatabase[i];
            if(_.isEqual(utilisateur,$scope.identifiants)){
                connected = true;
            }
        }
        if(connected){
            $scope.resultat = "Connexion OK";
        }else{
            $scope.resultat = "Connexion KO";
        }
    }
}