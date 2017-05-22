angular.module('segundoPisoApp').controller('workshopsReportCtrl', function ($scope, $http, $rootScope, $timeout, CatalogService) {
    $scope.clases = [];
    $scope.teachers = [];
    $scope.filters = {};
    $scope.report = [];
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    
    $scope.init = function () {
        $scope.filters = {};
        CatalogService.getWorkshops(true).success(function (data) {
            $scope.workshops = data;
            console.log($scope.workshops)
        });
    };
    
    $scope.init();
    
    $scope.teacherByClass = function () {
        CatalogService.getTeachersByClass($scope.filters.clases, true).success(function (data) {
            $scope.teachers = data;
        });
    };

    $scope.classByTeacher = function () {
        CatalogService.getClassesByTeacher($scope.filters.maestro, true).success(function (data) {
            $scope.classes = data;
        });
    };
    
    $scope.createReport = function () {
            initializeVariables();
            $scope.filters.status = true;
            var filtros = angular.toJson($scope.filters);
            $http({
                method: 'post',
                url: 'rest/report/teachers/payment',
                data: filtros,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function(data, status, headers, config) {
                $scope.report = data;
                if ($scope.report.pagoMaestros.length <= 0) {
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
        
    function initializeVariables() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;
    }
});