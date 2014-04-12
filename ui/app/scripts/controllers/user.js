'use strict';

angular.module('iwasthereApp')
  .controller('UserCtrl', function ($rootScope, $scope, $location, Session, User) {
        function onConnected(principal) {
            Session.user.connected = true;
            Session.user.email = principal.email;
            Session.user.fullName = principal.fullName
        }

        $scope.user = Session.user;
        $scope.$on('AUTHENTICATED', function(event, principal) {
            onConnected(principal);
        })

        User.get({email: 'current' }).$promise
            .then(onConnected)
            .catch(function() {
                Session.user.connected = false;
                delete Session.user.fullName;
                delete Session.user.email;
            });

        $scope.login = function() {
            $location.path('/login');
        }

        $scope.logout = function() {
            Session.delete(function() { location.reload() });
        }
    });
