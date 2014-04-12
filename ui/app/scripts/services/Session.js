'use strict';

angular.module('iwasthereApp')
    .factory('Session', function ($resource) {
        var s = $resource('/api/sessions/:sessionKey', {sessionKey: 'current'});
        s.user = { connected: false };
        return  s;
    })
    .factory('SecurityHttpInterceptor', function($q, $location) {
        return function(promise) {
            return promise.then(function(response) {
                return response;
            }, function(response) {
                if (response.status == 401 || response.status == 403) {
                    $location.path('/login');
                }
                return $q.reject(response);
            });
        }
    })
    .config(function($httpProvider) {
        $httpProvider.responseInterceptors.push('SecurityHttpInterceptor');
    })
;
