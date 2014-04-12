'use strict';

angular.module('iwasthereApp')
    .factory('Event', function ($resource) {
        return $resource('/api/events/:key', {key: '@_id'}, {
            'update': { method:'PUT' }
            });
    });
