angular.module('demo.geolocation.ctrl', [])

  .controller('GeolocationCtrl', function ($scope, $state, $cordovaGeolocation) {


var options = {timeout: 10000, enableHighAccuracy: true};
 
  $cordovaGeolocation.getCurrentPosition(options).then(function(position){
 
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
 
  var marker = new google.maps.Marker({
      map: $scope.map,
      animation: google.maps.Animation.DROP,
      position: latLng
  });      

  var marker2 = new google.maps.Marker({
      map: $scope.map,
      animation: google.maps.Animation.DROP,
      position: test
  });      
 
   var infoWindow = new google.maps.InfoWindow({
      content: "Here I am!"
  });
 
  google.maps.event.addListener(marker, 'click', function () {
      infoWindow.open($scope.map, marker);
  });
  
 
}); 
    
 
  }, function(error){
    console.log("Could not get location");
  });



  });
