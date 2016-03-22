angular.module('demo.offres.ctrl', [])

  .controller('OffresCtrl', function ($scope) {



/*

// Here we use Promises then
// GET /accounts
baseAccounts.getList().then(function (accounts) {
  // Here we can continue fetching the tree :).

  var firstAccount = accounts[0];
  // This will query /accounts/123/buildings considering 123 is the id of the firstAccount
  $scope.buildings = firstAccount.getList("buildings");

  // GET /accounts/123/places?query=param with request header: x-user:mgonto
  $scope.loggedInPlaces = firstAccount.getList("places", {query: param}, {'x-user': 'mgonto'})

  // This is a regular JS object, we can change anything we want :)
  firstAccount.name = "Gonto"

  // If we wanted to keep the original as it is, we can copy it to a new element
  var editFirstAccount = Restangular.copy(firstAccount);
  editFirstAccount.name = "New Name";


  // PUT /accounts/123. The name of this account will be changed from now on
  firstAccount.put();
  editFirstAccount.put();

  // PUT /accounts/123. Save will do POST or PUT accordingly
  firstAccount.save();

  // DELETE /accounts/123 We don't have first account anymore :(
  firstAccount.remove();

  var myBuilding = {
    name: "Gonto's Building",
    place: "Argentina"
  };

  // POST /accounts/123/buildings with MyBuilding information
  firstAccount.post("Buildings", myBuilding).then(function() {
    console.log("Object saved OK");
  }, function() {
    console.log("There was an error saving");
  });

*/




  });

