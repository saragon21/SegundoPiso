/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('segundoPisoApp').controller('externCtrl', function ($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.extern = {status: "true", alumno: "false"};
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    $scope.externs = [];
    $scope.edit = false;
    init();
    function init() {
        $scope.activos = CatalogService.getActivos();
        $scope.extern = {status: $scope.activos[0].value, alumno: "false"};

        $http({
            method: 'get',
            url: 'rest/student/getStudents',
            params: {'alumnos': false}
        }).success(function (data, status, headers, config) {
            $scope.externs = data;
        }).error(function (data, status, headers, config) {
        });
    }

    $scope.setExtern = function (extern) {
        $scope.extern = extern;
        $scope.extern.status = "" + extern.status;
        $scope.edit = true;
    };

    $scope.persistExtern = function () {
        initializeVariables();
        
        if ($scope.externForm.$valid) {
            var extern = angular.toJson($scope.extern);
            $http({
                method: 'post',
                url: 'rest/student/persistStudent',
                data: extern,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function (data, status, headers, config) {
                $scope.generalMessage = data.message;


                if (!data.valid) {
                    $scope.errorMessage = true;
                    $timeout(function () {
                        $scope.showMessage = true;
                    }, 5000);
                } else {
                    $scope.successMessage = true;
                    $timeout(function () {
                        $scope.showMessage = true;
                    }, 5000);
                    init();
                    $scope.externForm.$setPristine();
                    $scope.extern = {};
                    $scope.extern = {status: "true", alumno: "false"};
                    $scope.edit = false;
                }
            }).error(function (data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };

    $scope.changeToStudent = function (externo) {
        initializeVariables();

        var extern = angular.toJson(externo);
        $http({
            method: 'post',
            url: 'rest/student/changeToStudent',
            data: extern,
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                "Accept": "application/json"
            }
        }).success(function (data, status, headers, config) {
            $scope.generalMessage = data.message;

            if (!data.valid) {
                $scope.errorMessage = true;
                $timeout(function () {
                    $scope.showMessage = true;
                }, 5000);
            } else {
                $scope.successMessage = true;
                $timeout(function () {
                    $scope.showMessage = true;
                }, 5000);
                init();
            }
        }).error(function (data, status, headers, config) {
            alert("Contacte al adminsitrador");
        });
    };

    $scope.numberOfPages = function () {
        return Math.ceil($scope.externs.length / $scope.sizeOfPage);
    };
    
    function initializeVariables() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;
        $scope.extern.fromStudent = false;
    }
});