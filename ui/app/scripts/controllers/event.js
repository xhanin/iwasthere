'use strict';

angular.module('iwasthereApp')
  .controller('EventCtrl', function ($scope, $routeParams, Event, Session, Attendee, md5) {
        // TODO use resource
    $scope.user = Session.user;

    $scope.event =
        Event.get( { key: $routeParams.key } );

    $scope.addSelfAsAttendee = function() {
        var attendee = new Attendee({
            eventRef: $scope.event._id,
            emailHash: md5.createHash($scope.user.email),
            fullName: $scope.user.fullName,
            img: 'http://www.gravatar.com/avatar/' + md5.createHash($scope.user.email) + '?s=350'
        });
        attendee.$save(function(attendee) {
            $scope.attendees.push(attendee);
        });
    }

    $scope.attendees = Attendee.query({eventKey: $routeParams.key});
  });
