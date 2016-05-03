angular.module('demo.avis.ctrl', [])

  .controller('AvisCtrl', function ($scope) {

  $scope.SendData = function () {
	$scope.Resultat = "Avis envoyé ! Merci à vous !";
	}
  });
