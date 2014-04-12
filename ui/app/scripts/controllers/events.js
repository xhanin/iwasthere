'use strict';

angular.module('iwasthereApp')
  .controller('EventsCtrl', function ($scope, Event) {
        $scope.events = Event.query();

        $scope.addEvent = function() {
            var ev = new Event($scope.event);
            ev.$save(function(ev) {
                $scope.events.push(ev);
                $scope.showAddEvent = false;
            });
        }
  });
