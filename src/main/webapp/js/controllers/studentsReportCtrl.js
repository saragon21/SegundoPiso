angular.module('segundoPisoApp').controller('studentsReportCtrl', 
    function($scope, $rootScope, $http, $timeout, CatalogService) {
        $scope.showTable = false;
        $rootScope.showMenu = true;
        $scope.curPage = 0;
        $scope.sizeOfPage = 20;
        $scope.events = [];
        $scope.filters = {};
        $scope.report = {};
        
        init();
        
        function init() {
            CatalogService.getEvents(true, true).success(function(data) {
                $scope.events = data;
            });
        }
        
        $scope.createReport = function () {
            initializeVariables();
            var filters = angular.toJson($scope.filters);
            console.log(filters)
            $http({
                method: 'post',
                url: 'rest/report/movements',
                data: filters,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function(data, status, headers, config) {
                $scope.report = data;
                console.log($scope.report.movimientos)
                if ($scope.report.movimientos === undefined) {
                    $scope.filters = {};
                    $scope.showTable = false;
                    $scope.generalMessage = "No se encontraron registros";
                    $scope.errorMessage = true;
                        $timeout(function () {
                        $scope.showMessage = true;
                    }, 5000);
                } else {
                    $scope.showTable = true;
                }
            }).error(function(data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        };
        
        $scope.numberOfPages = function() {
            if ($scope.report.movimientos !== undefined) {
                return Math.ceil($scope.report.movimientos.length / $scope.sizeOfPage);
            } else {
                return 0;
            }
        };
        
        function initializeVariables() {
            $scope.generalMessage = null;
            $scope.showMessage = false;
            $scope.errorMessage = false;
            $scope.successMessage = false;
        }
    });