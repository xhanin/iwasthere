'use strict';

angular.module('iwasthereApp')
  .controller('SignupCtrl', function ($scope, $rootScope) {
        $scope.signup = { };
        $scope.submit = function() {
            // TODO use resource
            $rootScope.user = {
                connected: true,
                name: $scope.signup.name
            }
        }
    });
