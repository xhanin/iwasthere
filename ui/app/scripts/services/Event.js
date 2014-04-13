'use strict';

angular.module('iwasthereApp')
    .factory('Event', function ($resource, $baseUrl) {
        return $resource($baseUrl + '/api/events/:key', {key: '@_id'}, {
            'get':    {method:'GET', withCredentials: true},
            'save':    {method:'POST', withCredentials: true}
            });
    })
    .factory('Attendee', function ($resource, $baseUrl) {
        return $resource($baseUrl + '/api/events/:eventKey/attendees', {eventKey: '@eventRef'},
            {
                'get':    {method:'GET', withCredentials: true},
                'save':    {method:'POST', withCredentials: true}
            });
    })
    .factory('Message', function ($resource, $baseUrl) {
        return $resource($baseUrl + '/api/events/:eventKey/attendees/:attendeeKey/messages', null,
            {
                'save':    {method:'POST', withCredentials: true}
            });
    })
;
