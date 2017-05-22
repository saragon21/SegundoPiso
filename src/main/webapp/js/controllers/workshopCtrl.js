/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('segundoPisoApp').controller('workshopCtrl', function ($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.taller = {};
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    $scope.workshops = [];
    $scope.edit = false;
    init();
    function init() {
        $scope.workshop = {};
        CatalogService.getWorkshops().success(function(data) {
            $scope.workshops = data;
        });
    }

    $scope.setWorkshop = function (workshop) {
        workshop.fecha = new Date(parseDate(workshop.fecha));
        $scope.taller = workshop;
        $scope.edit = true;
    };

    $scope.persistWorkshop = function () {
        initializeVariables();

        if ($scope.workshopForm.$valid) {
            var workshop = angular.toJson($scope.taller);
            $http({
                method: 'post',
                url: 'rest/workshop/persistWorkshop',
                data: workshop,
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
                    $scope.workshopForm.$setPristine();
                    $scope.taller = {};
                    $scope.edit = false;
                }
            }).error(function (data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };

    $scope.validateDate = function () {
        var today = new Date();
        var selectedDate = $scope.taller.fecha;

        if (selectedDate < today) {
            $scope.taller.fecha = "";
            $scope.generalMessage = "La fecha debe ser mayor a hoy";
            $scope.errorMessage = true;
            $timeout(function () {
                $scope.showMessage = true;
            }, 5000);
        }
    }

    $scope.calculateSpPercentage = function () {
        var sp = $scope.taller.porcentajeSp;

        if (sp !== undefined) {
            if (sp > 100) {
                $scope.generalMessage = "El porcentaje no puede ser mayor a 100";
                $scope.errorMessage = true;
                $timeout(function () {
                    $scope.showMessage = true;
                }, 5000);
                $scope.taller.porcentajeArtista = 0;
            } else {
                $scope.taller.porcentajeArtista = 100 - sp;
            }
        } else {
            $scope.taller.porcentajeArtista = 0;
        }
    };

    $scope.calculateArtistPercentage = function () {
        var artist = $scope.taller.porcentajeArtista;

        if (artist !== undefined) {
            if (artist > 100) {
                $scope.generalMessage = "El porcentaje no puede ser mayor a 100";
                $scope.errorMessage = true;
                $timeout(function () {
                    $scope.showMessage = true;
                }, 5000);
                $scope.taller.porcentajeSp = 0;
            } else {
                $scope.taller.porcentajeSp = 100 - artist;
            }
        } else {
            $scope.taller.porcentajeSp = 0;
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
        return Math.ceil($scope.workshops.length / $scope.sizeOfPage);
    };

    function initializeVariables() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;
    }
    
    function parseDate(date) {
        if (date.length > 10) {
            var year = date.substring(0,4);
            var month = date.substring(5,7);
            var day = date.substring(8,10);
        
            return month + "/" + day + "/" + year;
        } else {
            return date;
        }
    }
});