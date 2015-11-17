document.addEventListener('deviceready', function(){
	// Ici du code au lancement de l'appli
}, false);

angular
.module('Destock', ['ngNewRouter'])
.controller('AppController', function($scope, $router) {
	$scope.title = "This is demo of application";
	$router.config([
		{path:"/", component: "/home"},
		{path:"/home", component: "home"},
		{path:"/about", component: "about"},
	]);
});