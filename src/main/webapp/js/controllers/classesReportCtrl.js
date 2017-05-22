angular.module('segundoPisoApp').controller('classesReportCtrl', 
    function($scope, $rootScope, $http, $timeout, CatalogService) {
        $scope.showTable = false;
        $rootScope.showMenu = true;
        $scope.curPage = 0;
        $scope.sizeOfPage = 20;
        $scope.classes = [];
        $scope.filters = {};
        $scope.report = {};
        
        init();
        
        function init() {
            CatalogService.getClasses(true).success(function(data) {
                $scope.classes = data;
            });
        }
        
        $scope.createReport = function () {
            initializeVariables();
            var filters = angular.toJson($scope.filters);
            $http({
                method: 'post',
                url: 'rest/report/classes',
                data: filters,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function(data, status, headers, config) {
                $scope.report = data;
                console.log($scope.report)
                if ($scope.report.length <= 0) {
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
            if ($scope.report.length > 0) {
                return Math.ceil($scope.report.length / $scope.sizeOfPage);
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