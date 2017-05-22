angular.module('segundoPisoApp').controller('teachersPaymentCtrl', function ($scope, $http, $rootScope, $timeout, CatalogService) {
    $rootScope.showMenu = true;
    $scope.curPage = 0;
    $scope.sizeOfPage = 10;
    $scope.payment = {};
    $scope.teachersPayment = {idClase: {}, idMaestro: {}};
    $scope.teacher = {};
    $scope.classes = [];
    $scope.disablePaymentBtn = true;

    init();
    function init() {
        CatalogService.getClasses(true).success(function (data) {
            $scope.classes = data;
            if ($scope.classes.length > 0) {
                $scope.teachersPayment.idClase = $scope.classes[0];
                CatalogService.getTeachersByClass($scope.teachersPayment.idClase.idClase, true).success(function (data) {
                    $scope.teachers = data;
                    if ($scope.teachers.length > 0) {
                        $scope.teachersPayment.idMaestro = $scope.teachers[0];
                    }
                });
            }
        });
    }

    $scope.teacherByClass = function () {
        CatalogService.getTeachersByClass($scope.teachersPayment.idClase.id, true).success(function (data) {
            $scope.teachers = data;
            if ($scope.teachers.length > 0) {
                $scope.teachersPayment.idMaestro = $scope.teachers[0];
            }
        });
    };

    $scope.classByTeacher = function () {
        CatalogService.getClassesByTeacher($scope.teachersPayment.idMaestro.id, true).success(function (data) {
            $scope.classes = data;
            if ($scope.classes.length > 0) {
                $scope.teachersPayment.idClase = $scope.classes[0];
            }
        });
    };
    
    $scope.consultarPago = function () {
        $http({
            method: 'get',
            url: 'rest/attendence/clase',
            params: {idMaestro: $scope.teachersPayment.idMaestro.id, idClase: $scope.teachersPayment.idClase.id, pagadas : 'false'}
        }).success(function (data, status, headers, config) {
            $scope.payment =  data;
            if ($scope.payment.attendence !== undefined) {
                $scope.disablePaymentBtn = false;
            }
        }).error(function (data, status, headers, config) {});
    };
    
    $scope.persistPayment = function () {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;

        if ($scope.teachersPaymentForm.$valid) {
            var pago = angular.toJson($scope.payment);
            $http({
                method: 'post',
                url: 'rest/pagos/maestros/persist',
                data: pago,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "Accept": "application/json"
                }
            }).success(function (data, status, headers, config) {
                $scope.generalMessage = data.message;
                $scope.disablePaymentBtn = false;

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
                    $scope.teachersPayment = {idClase: {}, idMaestro: {}};
                    init();
                    $scope.teachersPaymentForm.$setPristine();
                }
            }).error(function (data, status, headers, config) {
                alert("Contacte al adminsitrador");
            });
        }
    };

    
    $scope.numberOfPages = function () {
        var size = 0;
        if ($scope.payment.attendence !== undefined) {
            size = Math.ceil($scope.payment.attendence.length / $scope.sizeOfPage);
        }
        
        return size;
    };

});