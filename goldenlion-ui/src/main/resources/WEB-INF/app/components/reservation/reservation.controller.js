(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($scope, $location) {
    var vm = this;
    var roomid = $location.search().roomid;
    var startdate = $location.search().startdate;
    var enddate = $location.search().enddate;

    //TODO
    $scope.roomnr = roomid;
    $scope.startdate = startdate;
    $scope.enddate = enddate;
    $scope.duration = "10 Tage";

    activate();

    function activate() {
    }
  }
})();
