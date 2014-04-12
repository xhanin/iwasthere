'use strict';

angular.module('iwasthereApp')
  .controller('SignupCtrl', function ($scope, $rootScope, $location, User, md5) {
        $scope.signup = { };
        $scope.submit = function() {

            var signup = new User($scope.signup);
            signup.passwordHash = md5.createHash(signup.password);
            delete signup.password;

            signup.$save(function(u) {
                $rootScope.$broadcast('AUTHENTICATED', u);
                $location.path('/events');
            });
        }
    });
