/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('segundoPisoApp').controller('clazzesCtrl', function($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    $scope.clazzes = [];
    init();
    function init() {
        $scope.teachers = [];
        $scope.activos = CatalogService.getActivos();
        $scope.clase = {status: $scope.activos[0].value};
        CatalogService.getTeachers(true).success(function(data) {
            $scope.teachers = data;
        });
        $http({
            method: 'get',
            url: 'rest/clase/getClases'
        }).success(function(data, status, headers, config) {
            $scope.clazzes = data;
            $scope.clase.idMaestro = {id: data[0].id};
        }).error(function(data, status, headers, config) {
        });
    }

    $scope.setClazz = function(clazz) {
        $scope.clase = clazz;
    };

    $scope.persistClass = function() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;

        if ($scope.clazzForm.$valid) {
            delete $scope.clase.statusStr;
            var clase = angular.toJson($scope.clase);
            $http({
                method: 'post',
                url: 'rest/clase/persistClass',
                data: clase,
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
                    $scope.clazzForm.$setPristine();
                    $scope.clase = {};
                    $scope.clase = {nombreClase: ""};
                }
            }).error(function(data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };

    $scope.numberOfPages = function() {
        return Math.ceil($scope.clazzes.length / $scope.sizeOfPage);
    };

});