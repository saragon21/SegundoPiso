angular.module('segundoPisoApp').controller('workshopAtendenceCtrl', function ($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.workshopAttendence = {};
    $scope.workshops = [];
    $scope.alumnos = [];
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    $scope.workshopsAttendences = [];
    
    function init() {
        CatalogService.getWorkshopsByDate(new Date()).success(function(data) {
            $scope.workshops = data;
            if (data.length > 0) {
                $scope.workshopAttendence.idTaller = data[0];
                $scope.getWorkshopAttendence($scope.workshopAttendence.idTaller.idTaller);
            }
        });
        CatalogService.getAllStudents(true).success(function(data) {
           $scope.alumnos = data; 
        });
    }
    init();
    
    $scope.getWorkshopAttendence = function (workshopId) {
        $http({
                method: 'get',
                url: 'rest/workshopAttendence/getWorkshopAttendence/byId',
                params: {'idWorkshop' : workshopId},
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function (data, status, headers, config) {
                $scope.workshopsAttendences = data;
            }).error(function (data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
    };

    $scope.saveWorkshopAttendence = function () {
        initializeVariables();

        if ($scope.workshopForm.$valid) {
            $scope.workshopAttendence.status = 'true';
            var workshop = angular.toJson($scope.workshopAttendence);
            $http({
                method: 'post',
                url: 'rest/workshopAttendence/persistWorkshopAttendence',
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
                    $scope.getWorkshopAttendence($scope.workshopAttendence.idTaller.idTaller);
                    init();
                    $scope.workshopForm.$setPristine();
                    $scope.workshopAttendence = {};
                }
            }).error(function (data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };

    $scope.delete = function (workshopAttendence) {
        initializeVariables();
        var workshop = angular.toJson(workshopAttendence);
        $http({
            method: 'post',
            url: 'rest/workshopAttendence/deleteWorkshopAttendence',
            data: workshop,
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                "Accept": "application/json"
            }
        }).success(function (data, status, headers, config) {
            $scope.generalMessage = data.message;
            $scope.getWorkshopAttendence($scope.workshopAttendence.idTaller.idTaller);
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
                }
        }).error(function (data, status, headers, config) {
            alert("Contacte al adminsitrador");
        });
    };

    $scope.numberOfPages = function () {
        return Math.ceil($scope.workshopsAttendences.length / $scope.sizeOfPage);
    };

    function initializeVariables() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;
    }
    
});