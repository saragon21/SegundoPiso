/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('segundoPisoApp').controller('homeCtrl', function ($scope, $http, $rootScope) {
    $rootScope.showMenu = true;
    $scope.students = [];
    $scope.sizeOfPage = 20;
    $scope.curPage = 0;
    init();
    function init() {
        $http({
            method: 'get',
            url: 'rest/home/students/expired',
            params: {'clasesRestantes': 3}
        }).success(function (data, status, headers, config) {
            $scope.students = data;
        }).error(function (data, status, headers, config) {
        });
    }

    $scope.numberOfPages = function () {
        return Math.ceil($scope.students.length / $scope.sizeOfPage);
    };
    
});