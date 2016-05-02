angular.module('demo.geolocation.ctrl', [])

  .controller('GeolocationCtrl', function ($scope, $state, $cordovaGeolocation, $http) {


var options = {timeout: 10000, enableHighAccuracy: true};
 
  $cordovaGeolocation.getCurrentPosition(options).then(function(position){







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














 
    var latLng = new     google.maps.LatLng(position.coords.latitude, position.coords.longitude);
 
    var mapOptions = {
      center: latLng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 
    $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);
    
 var marker = new google.maps.Marker({
      map: $scope.map,
      animation: google.maps.Animation.DROP,
      position: latLng
  });      
 
  var infoWindow = new google.maps.InfoWindow({
      content: "C'est vous !"
  });
 
  google.maps.event.addListener(marker, 'click', function () {
      infoWindow.open($scope.map, marker);
  });
  
      
    
   //Wait until the map is loaded
google.maps.event.addListenerOnce($scope.map, 'idle', function(){
 
 
var geocoder = new google.maps.Geocoder();
 
 
angular.forEach($scope.Resultat, function(valeur, cle){



geocoder.geocode( { "address": valeur.adresse }, function(results, status) {

if (status == google.maps.GeocoderStatus.OK)
{



  var marker = new google.maps.Marker({
      map: $scope.map,
      animation: google.maps.Animation.DROP,
      position: results[0].geometry.location,
      title: valeur.referencePromo
  });   
  
        var contenu = " <img src='/img/Offres/nirvana.jpg' style='width:100px;'/> <br /><b>" + valeur.referencePromo + "<br /><br />Description :</b><br />" + valeur.description;
  var infowindow = new google.maps.InfoWindow({
    content: contenu
  });

  marker.addListener('click', function() {
    infowindow.open($scope.map, marker);
  });
 

     } else {
      alert("Le geocodage n\'a pu etre effectue pour la raison suivante: " + status);
     }
    });




});






 
 
}); 
    
 
  }, function(error){
    console.log("Could not get location");
  });



  });
