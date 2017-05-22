/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function CatalogService($http) {
    var CatalogService = {};

    CatalogService.getStudents = function(status) {
        return $http({
            method: 'get',
            params: {'status': status},
            url: 'rest/student/getStudents/status'
        });
    };

    CatalogService.getClasses = function(status) {
        return $http({
            method: 'get',
            params: {'status': status},
            url: 'rest/clase/getClases/status'
        });
    };

    CatalogService.getTeachersByClass = function(idClase, status) {
        return $http({
            method: 'get',
            params: {'idClase': idClase, 'status': status},
            url: 'rest/teacher/getTeachers/byClass'
        });
    };

    CatalogService.getMovements = function(estudiante, status) {
        return $http({
            method: 'get',
            params: {'idAlumno': estudiante.id, 'status': status},
            url: 'rest/movement/getMovements/student'
        });
    };

    CatalogService.getEvents = function(status, clase) {
        return $http({
            method: 'get',
            params: {'status': status, 'clase': clase},
            url: 'rest/event/getEvents/status'
        });
    };

    CatalogService.getTeachers = function(status) {
        return $http({
            method: 'get',
            params: {'status': status},
            url: 'rest/teacher/getTeachers/status'
        });
    };

    CatalogService.getActivos = function() {
        var status = [];
        status.push({status: 'Si', value: true});
        status.push({status: 'No', value: false});

        return status;
    };

    CatalogService.getTiposEvento = function() {
        var tiposEvento = [];

        tiposEvento.push({tipoClase: 'Clase', value: false});
        tiposEvento.push({tipoClase: 'Otro', value: true});

        return tiposEvento;
    };

    CatalogService.getAttendences = function(idAlumno) {
        return $http({
            method: 'get',
            params: {'idAlumno': idAlumno},
            url: 'rest/attendence/getAttendence/byStudent'
        });
    };

    CatalogService.getWorkshops = function() {
        return $http({
            method: 'get',
            url: 'rest/workshop/getWorkshops'
        });
    };

    CatalogService.getAllStudents = function(status) {
        return $http({
            method: 'get',
            params: {'status': status},
            url: 'rest/student/getAllStudents'
        });
    };
    
    CatalogService.getWorkshopsByDate = function(date) {
        return $http({
            method: 'get',
            params: {'date': date},
            url: 'rest/workshop/getWorkshops/byDate'
        });
    };

    CatalogService.getClassesByTeacher = function(idTeacher, status) {
        return $http({
            metho: 'get',
            params: {'idMaestro': idTeacher, 'status':status},
            url: 'rest/clase/byMaestro'
        });
    };

    return CatalogService;
}

angular.module('segundoPisoApp').factory('CatalogService', CatalogService);