'use strict';

angular.module('iwasthereApp')
  .controller('LoginCtrl', function ($scope, $rootScope, $http, $location, $baseUrl, md5) {
        $scope.login = { };

        $scope.submit = function() {
            $http.post($baseUrl + '/api/sessions',
                    {principal: {name: $scope.login.email, passwordHash: md5.createHash($scope.login.password)}},
                    {withCredentials: true}
                )
                .success(function(data, status, headers, config) {
                    console.log('authenticated', data, status);
                    $rootScope.$broadcast('AUTHENTICATED', data.principal);
                    $location.path('/events');
                }).error(function(data, status, headers, config) {
                    console.log('error', data, status);
                    alert("Authentication error, please try again.");
                });
        }
  });
