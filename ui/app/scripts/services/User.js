'use strict';

angular.module('iwasthereApp')
    .factory('User', function ($resource, $baseUrl) {
        return $resource($baseUrl + '/api/users/:email', null,
            {
                'get':    {method:'GET', withCredentials: true},
                'save':    {method:'POST', withCredentials: true}
            });
    });
