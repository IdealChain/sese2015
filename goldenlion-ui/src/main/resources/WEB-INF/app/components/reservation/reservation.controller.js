(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($mdDialog, restService) {
    var vm = this;
    vm.reservations = [];

    vm.allReservation = function() {
      restService.allReservation().then(
        function successCallback(response) {
          vm.reservations = response.data;
        },
        function errorCallback() {
          alert('Error: Could not receive customer data');
        });
    };
    vm.allReservation();

    vm.confirmDelete = function(reservationId) {
      console.log("delete: " + reservationId);
      console.log($mdDialog);
      var confirm = $mdDialog.confirm( {
        title: "Reservierung löschen?",
        content: "Möchten Sie diese Reservierung wirklich löschen?",
        cancel: "Ähm, lieber nicht.",
        ok: "Ja, unbedingt!"
      });
      $mdDialog.show(confirm).then(function() {
        //TODO: delete
      }, function() {
        //cancel
      });
    }
  }
})();
