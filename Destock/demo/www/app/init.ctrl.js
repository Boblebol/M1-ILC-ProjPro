angular.module('demo.init.ctrl', [])

  .controller('InitCtrl', function ($scope, $http, $httpParamSerializer) {
	  $scope.Init = function(){

      						if (window.localStorage.getItem('CookieEmail') != null)
						{
							$scope.CookieConnecte = 'oui';
							$scope.CookieEmail = window.localStorage.getItem('CookieEmail');
							$scope.CookieType = window.localStorage.getItem('CookieType');
							$scope.CookieId = window.localStorage.getItem('CookieId');
						}    
						
						
						}
						
						
						
					$scope.Deconnexion = function ()
					  {
					        window.localStorage.removeItem('CookieConnecte');
					        window.localStorage.removeItem('CookieEmail');
					        window.localStorage.removeItem('CookieType');
					        window.localStorage.removeItem('CookieId');
							$scope.CookieConnecte = null;
							$scope.CookieEmail = null;
							$scope.CookieType = null;
							$scope.CookieId = null;
					
					}	
						
						}
);
