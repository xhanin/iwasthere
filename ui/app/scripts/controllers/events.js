'use strict';

angular.module('iwasthereApp')
  .controller('EventsCtrl', function ($scope, Event) {
    $scope.events = Event.query();
  });
