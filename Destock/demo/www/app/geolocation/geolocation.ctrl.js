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














 
    var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    var test = new google.maps.LatLng(49.6019858, 7.8835216999999375);
 
    var mapOptions = {
      center: latLng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 
    $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);
    
    
    
   //Wait until the map is loaded
google.maps.event.addListenerOnce($scope.map, 'idle', function(){
 
 
 
 





var geocoder = new google.maps.Geocoder();
geocoder.geocode( { "address": Resultat.adresse }, function(results, status) {

if (status == google.maps.GeocoderStatus.OK)
{

  var infoWindow = new google.maps.InfoWindow({
      content: "Here I am!"
  });

  var marker = new google.maps.Marker({
      map: $scope.map,
      animation: google.maps.Animation.DROP,
      position: results[0].geometry.location
  });      

     } else {
      alert("Le geocodage n\'a pu etre effectue pour la raison suivante: " + status);
     }
    });


 
 
}); 
    
 
  }, function(error){
    console.log("Could not get location");
  });



  });
