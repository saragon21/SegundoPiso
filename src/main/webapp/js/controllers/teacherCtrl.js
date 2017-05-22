/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('segundoPisoApp').controller('teachersCtrl', function($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.curPage = 0;
    $scope.teachers = [];
    $scope.sizeOfPage = 10;
    $scope.paymentLabel = "Porcentaje";
    init();
    function init() {
        $scope.activos = CatalogService.getActivos();
        $scope.maestro = {status: $scope.activos[0].value};
        $http({
            method: 'get',
            url: 'rest/teacher/getTeachers'
        }).success(function(data, status, headers, config) {
            $scope.teachers = data;
        }).error(function(data, status, headers, config) {
        });
    }

    $scope.setTeacher = function(teacher) {
        $scope.maestro = teacher;
        $scope.maestro.porcentaje = "" + teacher.porcentaje;
    };

    $scope.persistTeacher = function() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;

        if ($scope.teacherForm.$valid) {
            delete $scope.maestro.statusStr;
            var maestro = angular.toJson($scope.maestro);
            console.log(maestro)
            $http({
                method: 'post',
                url: 'rest/teacher/persistTeacher',
                data: maestro,
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
                    $scope.maestro = {};
                    init();
                    $scope.teacherForm.$setPristine();
                }
            }).error(function(data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };
    
    $scope.configurePayment = function () {
        if ($scope.maestro.tipoPago === 'true') {
            $scope.paymentLabel = "Porcentaje";
        } else {
            $scope.paymentLabel = "Fijo";
        }
    };
    
    $scope.numberOfPages = function () {
        return Math.ceil($scope.teachers.length / $scope.sizeOfPage);
    };
});