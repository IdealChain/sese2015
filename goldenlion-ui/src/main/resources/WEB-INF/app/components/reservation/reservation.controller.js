(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($scope, $state, $location, $filter, restService) {
    var vm = this;
    vm.reservations = [];

    vm.allReservation = function() {
      restService.allReservation().then(
        function successCallback(response) {
          vm.reservations = response.data;

          //TODO: call customerByIds and roomByIds
        },
        function errorCallback() {
          alert('Error: Could not receive customer data');
        });
    };
    vm.allReservation();

    vm.customerByIds = function (customerIds) {
      restService.customerByIds(customerIds).then(
        function successCallback(response) {
          //TODO
        },
        function errorCallback() {
          alert('Error: Could not receive customer data');
        });
    };

    vm.roomByIds = function (roomIds) {
      restService.roomByIds(roomIds).then(
        function successCallback(response) {
          //TODO
        },
        function errorCallback() {
          alert('Error: Could not receive room data');
        });
    };
  }
})();
