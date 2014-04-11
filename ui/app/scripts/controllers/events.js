'use strict';

angular.module('iwasthereApp')
  .controller('EventsCtrl', function ($scope) {
        // TODO use resource
    $scope.events = [
        {id: '123', name: 'RESTX @ DevoxxFr 2014', count: 150, iwasthere: true },
        {id: '456', name: 'Meet and Greet @ DevoxxFr 2014', count: 1200, iwasthere: false }
    ];
  });
