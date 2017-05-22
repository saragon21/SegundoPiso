/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('segundoPisoApp').controller('movementsCtrl', function($scope, $rootScope, $timeout, $http, $location, $anchorScroll, CatalogService) {
    $rootScope.showMenu = true;
    $scope.curPage = 0;
    $scope.sizeOfPage = 5;
    $scope.curPageMov = 0;
    $scope.sizeOfPageMov = 5;
    $scope.students = [];
    $scope.movements = [];
    $scope.events = [];
    $scope.event;
    document.getElementById('searchCode').focus();
    init();
    function init() {
        $scope.movimiento = {fecha: new Date(), idEvento: {}, descuento: 0, idAlumno: null};

        CatalogService.getStudents(true).success(function(data) {
            $scope.students = data;
        });

        CatalogService.getEvents(true, true).success(function(data) {
            $scope.events = data;
            if (data.length > 0) {
                $scope.event = data[0];
                $scope.movimiento.idEvento = $scope.event;
                $scope.movimiento.pago = $scope.event.costo;
            }
        });
    }

    $scope.calculateDiscount = function() {
        if ($scope.movimiento.porcentaje) {
            var porcentaje = $scope.movimiento.descuento / 100;
            var descuento = $scope.movimiento.idEvento.costo * porcentaje;
            $scope.movimiento.pago = $scope.movimiento.idEvento.costo - descuento;
        } else {
            $scope.movimiento.pago = $scope.movimiento.idEvento.costo - $scope.movimiento.descuento;
        }
    };

    $scope.setStudent = function(student) {
        console.log(student.id);
        $scope.movimiento.idAlumno = student;
        CatalogService.getMovements(student, true).success(function(data) {
            $scope.movements = data;
            console.log(data);
        });
    };

    $scope.jumpTo = function(id) {
        var old = $location.hash();
        $location.hash(id);
        $anchorScroll();
        $location.hash(old);
    };

    $scope.numberOfPages = function() {
        return Math.ceil($scope.filtered.length / $scope.sizeOfPage);
    };

    $scope.numberOfPagesMov = function() {
        return Math.ceil($scope.movements.length / $scope.sizeOfPageMov);
    };

    $scope.saveMovement = function() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;

        if ($scope.movimiento.idAlumno === null) {
            $scope.generalMessage = "Se debe seleccionar un alumno";
            $scope.errorMessage = true;
            $timeout(function() {
                $scope.showMessage = true;
                $scope.jumpTo('messageContainer');
            }, 5000);
        } else {
            if ($scope.movementForm.$valid) {
                var movimiento = angular.toJson($scope.movimiento);
                $http({
                    method: 'post',
                    url: 'rest/movement/persistMovement',
                    data: movimiento,
                    headers: {
                        "Content-Type": "application/json; charset=utf-8",
                        "Accept": "application/json"
                    }
                }).success(function(data, status, headers, config) {
                    $scope.displayInfo(data);
                }).error(function(data, status, headers, config) {
                    alert("Contacte al adminsitrador");
                });
                $scope.jumpTo('tableMovements');
            }
        }
    };

    $scope.deleteMovement = function(idMovimiento) {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;
        $http({
            method: 'get',
            url: 'rest/movement/deleteMovement',
            params: {'idMovimiento': idMovimiento},
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                "Accept": "application/json"
            }
        }).success(function(data, status, headers, config) {
            $scope.displayInfo(data);
        }).error(function(data, status, headers, config) {
            alert("Contacte al adminsitrador");
        });
    };

    $scope.resetPage = function() {
        document.getElementById('searchCode').focus();
        $scope.movimiento = {fecha: new Date(), idEvento: {}, descuento: 0, idAlumno: null};
        $scope.movements = [];
        $scope.event = $scope.events[0];
        $scope.movimiento.idEvento = $scope.event;
        $scope.movimiento.pago = $scope.event.costo;
        $scope.search = '';

        CatalogService.getStudents(true).success(function(data) {
            $scope.students = data;
        });
    };

    $scope.selectStudent = function() {
        if ($scope.filtered.length === 1) {
            $scope.setStudent($scope.filtered[0]);
        }
    };

    $scope.displayInfo = function(data) {
        $scope.generalMessage = data.message;

        if (!data.valid) {
            $scope.errorMessage = true;
            $timeout(function() {
                $scope.showMessage = true;
                $scope.jumpTo('messageContainer');
            }, 5000);
        } else {
            $scope.successMessage = true;
            $timeout(function() {
                $scope.showMessage = true;
                $scope.jumpTo('messageContainer');
            }, 5000);
            student = $scope.movimiento.idAlumno;
            init();
            $scope.setStudent(student);
            $scope.movementForm.$setPristine();
        }
    }
});
