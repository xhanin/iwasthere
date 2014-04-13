'use strict';

angular.module('iwasthereApp')
    .factory('Event', function ($resource) {
        return $resource('/api/events/:key', {key: '@_id'}, {
            'update': { method:'PUT' }
            });
    })
    .factory('Attendee', function ($resource) {
        return $resource('/api/events/:eventKey/attendees', {eventKey: '@eventRef'});
    })
    .factory('Message', function ($resource) {
        return $resource('/api/events/:eventKey/attendees/:attendeeKey/messages');
    })
;
