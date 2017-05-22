/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('segundoPisoApp').controller('insAndOutsCtrl', function($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    $scope.insAndOuts = [];
    init();
    function init() {
        $scope.events = [];
        $scope.entradasSalidas = {};
        CatalogService.getEvents(true).success(function(data) {
            $scope.events = data;
            $scope.entradaSalida= {idEvento : data[0]};
        });
        $http({
            method: 'get',
            url: 'rest/inOut/getInsAndOuts'
        }).success(function(data, status, headers, config) {
            $scope.entradasSalidas = data;
        }).error(function(data, status, headers, config) {
        });
    }

    $scope.setClazz = function(clazz) {
        $scope.clase = clazz;
        $scope.clase.status = "" + clazz.status;
    };

    $scope.persistInsAndOuts = function() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;

        if ($scope.inAndOutForm.$valid) {
            var entradaSalida = angular.toJson($scope.entradaSalida);
            $http({
                method: 'post',
                url: 'rest/inOut/persistInAndOut',
                data: entradaSalida,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function(data, status, headers, config) {
                $scope.generalMessage = data.message;

                if (!data.valid) {
                    $scope.errorMessage = true;
                    $timeout(function() {
                        $scope.showMessage = true;
                    }, 5000);
                } else {
                    $scope.successMessage = true;
                    $timeout(function() {
                        $scope.showMessage = true;
                    }, 5000);
                    init();
                    $scope.inAndOutForm.$setPristine();
                    $scope.entradaSalida = {};
                }
            }).error(function(data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };

    $scope.numberOfPages = function() {
        return Math.ceil($scope.insAndOuts.length / $scope.sizeOfPage);
    };

});