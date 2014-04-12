'use strict';

angular.module('iwasthereApp')
    .factory('User', function ($resource) {
        return $resource('/api/users/:email');
    });
