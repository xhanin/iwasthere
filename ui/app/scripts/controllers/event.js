'use strict';

angular.module('iwasthereApp')
  .controller('EventCtrl', function ($scope, $routeParams, Event, Session, Attendee, Message, md5) {
    $scope.user = Session.user;

    $scope.showImage = true;

    function loadDefaultMessage() {
        $scope.message = { text: '', mood: 'blue' };
    }

    loadDefaultMessage();

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
            $scope.event.iwasthere = true;
        });
    }

    $scope.attendees = Attendee.query({eventKey: $routeParams.key});

    $scope.sendMessage = function(message) {
        var m = new Message(message);
        m.$save({eventKey: $routeParams.key, attendeeKey: md5.createHash($scope.user.email)}, function() {
            // reload attendees
            $scope.attendees = Attendee.query({eventKey: $routeParams.key});
            loadDefaultMessage();
        });
    }
  });
