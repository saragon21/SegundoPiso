/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('segundoPisoApp').controller('attendenceCtrl', function($scope, $rootScope, $timeout, $http, $location, $anchorScroll, CatalogService) {
    $rootScope.showMenu = true;
    $scope.curPage = 0;
    $scope.sizeOfPage = 5;
    $scope.curPageAtt = 0;
    $scope.sizeOfPageAtt = 5;
    $scope.students = [];
    $scope.attendences = [];
    $scope.classes = [];
    $scope.teachers = [];
    $rootScope.alert = {showMessage: false, message: ''};
    document.getElementById('searchCode').focus();
    init();
    function init() {
        $scope.asistencia = {fecha: new Date(), idMaestro: {}, idClase: {}, idAlumno: null};

        CatalogService.getStudents(true).success(function(data) {
            $scope.students = data;
        });

        CatalogService.getClasses(true).success(function(data) {
            $scope.asistencia.idClase = data[0];
            $scope.classes = data;
            CatalogService.getTeachersByClass($scope.asistencia.idClase.idClase, true).success(function(data) {
                $scope.asistencia.idMaestro = data[0];
                $scope.teachers = data;
            });
        });
    }

    $scope.setStudent = function(student) {
        $scope.asistencia.idAlumno = student;
        CatalogService.getAttendences(student.idAlumno).success(function(data) {
            $scope.attendences = data;
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

    $scope.numberOfPagesAtt = function() {
        return Math.ceil($scope.attendences.length / $scope.sizeOfPageAtt);
    };

    $scope.saveAttendence = function() {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;

        if ($scope.asistencia.idAlumno === null) {
            $scope.generalMessage = "Se debe seleccionar un alumno";
            $scope.errorMessage = true;
            $timeout(function() {
                $scope.showMessage = true;
                $scope.jumpTo('messageContainer');
            }, 5000);
        } else {
            if ($scope.attendenceForm.$valid) {
                var asistencia = angular.toJson($scope.asistencia);
                $http({
                    method: 'post',
                    url: 'rest/attendence/persistAttendence',
                    data: asistencia,
                    headers: {
                        "Content-Type": "application/json; charset=utf-8",
                        "Accept": "application/json"
                    }
                }).success(function(data) {
                    $scope.alert.showMessage = true;
                    $scope.alert.message = data.message;
                    student = $scope.asistencia.idAlumno;
                    init();
                    $scope.setStudent(student);
                    $scope.attendenceForm.$setPristine();
                }).error(function() {
                    alert("Contacte al adminsitrador");
                });
                $scope.jumpTo('tableAttendences');
            }
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
            student = $scope.asistencia.idAlumno;
            init();
            $scope.setStudent(student);
            $scope.attendenceForm.$setPristine();
        }
    };

    $scope.deleteAttendence = function(idAsistencia) {
        $scope.generalMessage = null;
        $scope.showMessage = false;
        $scope.errorMessage = false;
        $scope.successMessage = false;
        $http({
            method: 'get',
            url: 'rest/attendence/deleteAttendence',
            params: {'idAsistencia': idAsistencia},
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
        $scope.asistencia = {fecha: new Date(), idMaestro: {}, idClase: {}, idAlumno: null};
        $scope.attendences = [];
        $scope.asistencia.idMaestro = $scope.teachers[0];
        $scope.asistencia.idClase = $scope.classes[0];
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

    $scope.getTeachers = function() {
        CatalogService.getTeachersByClass($scope.asistencia.idClase.idClase, true).success(function(data) {
            $scope.asistencia.idMaestro = data[0];
            $scope.teachers = data;
        });
    };
});