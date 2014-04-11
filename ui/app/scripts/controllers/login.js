'use strict';

angular.module('iwasthereApp')
  .controller('LoginCtrl', function ($scope, $rootScope) {
        $scope.login = { };

        $scope.submit = function() {
            // TODO use resource
            $rootScope.user = {
                connected: true,
                name: $scope.login.email
            }
        }
  });
