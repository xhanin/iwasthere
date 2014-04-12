'use strict';

angular.module('iwasthereApp')
  .controller('EventCtrl', function ($scope, Event) {
        // TODO use resource

    $scope.event =
        {id: '123', name: 'RESTX @ DevoxxFr 2014', count: 150, iwasthere: true };

    $scope.attendees = [
        {name: 'Xavier', img: 'http://www.gravatar.com/avatar/823cc38b227e3b03babd0b3e4642f8f1.jpg?s=350' },
        {name: 'Damien', img: 'http://www.gravatar.com/avatar/6c68102c47795908cc014fc4d017db1d.jpg?s=350'},
        {name: 'Fred', img: 'http://www.gravatar.com/avatar/8982d345f5955665f31cfb2bfe66c214.jpg?s=350'},
        {name: 'David', img: 'http://www.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf.jpg?s=350'}
    ];
  });
