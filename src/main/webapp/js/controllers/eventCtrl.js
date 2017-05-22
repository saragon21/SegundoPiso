/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('segundoPisoApp').controller('eventsCtrl', function($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    $scope.events = [];
    $scope.tiposClase = [];
    init();
    function init() {
        $scope.tiposClase = CatalogService.getTiposEvento();
        $scope.activos = CatalogService.getActivos();
        $scope.evento = {status: $scope.activos[0].value, clases: $scope.activos[0].value};
        $scope.tipoClase = $scope.tiposClase[0].value;
        $http({
            method: 'get',
            url: 'rest/event/getEvents'
        }).success(function(data, status, headers, config) {
            $scope.events = data;
        }).error(function(data, status, headers, config) {
        });
    }

    $scope.setEvent = function(event) {
        $scope.evento = event;
        $scope.evento.status = "" + event.status;
    };

    $scope.persistEvent = function() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;

        if ($scope.eventForm.$valid) {
            var evento = angular.toJson($scope.evento);
            $http({
                method: 'post',
                url: 'rest/event/persistEvent',
                data: evento,
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
                    $scope.evento = {};
                    init();
                    $scope.eventForm.$setPristine();
                }
            }).error(function(data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };

    $scope.numberOfPages = function() {
        return Math.ceil($scope.events.length / $scope.sizeOfPage);
    };
});