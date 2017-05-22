/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('segundoPisoApp').filter('pagination', function() {
    return function(input, start) {
        if (!input || !input.length) {
            return;
        }
        start = +start; //parse to int
        return input.slice(start);
    };
});

angular.module('segundoPisoApp').filter('discount', function() {
    return function(input, isPercentage) {
        if (input !== 0) {
            if (isPercentage) {
                return input + "%";
            } else {
                return '$' + input.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
            }
        }
    }
});
