
var myApp= angular.module("myApp",[]);
myApp.controller("InscriptionController",function ($scope,$http) {

    $scope.etudiant={};
      $scope.saveEtudiant=function() {
        $http({
            method: 'POST',
            url: '/etudiants',
            data:$scope.etudiant,
        }).then(function successCallback(response) {
            $scope.etudiant=response;
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
});