'use strict';

angular.module('iwasthereApp')
  .controller('UserCtrl', function ($rootScope, $scope, $location) {
        $rootScope.user = { connected: false };
        $scope.login = function() {
            $location.path('/login');
        }
    });
