'use strict';

angular.module('iwasthereApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute',
  'angular-md5'
])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/events', {
        templateUrl: 'views/events.html',
        controller: 'EventsCtrl'
      })
      .when('/events/:key', {
        templateUrl: 'views/event.html',
        controller: 'EventCtrl'
      })
      .when('/signup', {
        templateUrl: 'views/signup.html',
        controller: 'SignupCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .otherwise({
        redirectTo: '/events'
      });
  })
    .constant('$baseUrl', (function() {
        // production, static files on github served on 'iwasthere.restx.io', api on heroku served from 'http://iwasthere-restx-io.herokuapp.com'
        if (location.host === 'iwasthere.restx.io') return 'http://iwasthere-restx-io.herokuapp.com';
        // dev with CORS, static files are served on localhost port 8077
        if (location.host === 'localhost:8077') return 'http://localhost:8080';
        // other cases, assume server serves api too
        return '';
    })())
;
