/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('segundoPisoApp').controller('loginCtrl', function($scope, $http, $location, $rootScope) {
    $scope.login = function() {
        $scope.showErrorsCheckValidity = true;
        $scope.login.message = null;

        if ($scope.loginForm.$valid) {
            var user = angular.toJson($scope.usuario);
            $http({
                method: 'post',
                url: 'rest/login/validate',
                data: user,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function(data, status, headers, config) {
                if (!data.valid) {
                    $scope.login.message = data.message;
                } else {
                    $rootScope.showMenu = true;
                    $rootScope.parent = false;
                    $location.url('/start');
                }
            }).error(function(data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };
});