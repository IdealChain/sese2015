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
          for (var i = 0; i < vm.reservations.length; i++) {
            var roomsMerged = "";
            for (var j = 0; j < vm.reservations[i].rooms.length; j++) {
              roomsMerged += vm.reservations[i].rooms[j].roomNumber + " ";
            }
            vm.reservations[i].roomsMerged = roomsMerged;
            var customerMerged = "";
            for (var j = 0; j < vm.reservations[i].customers.length; j++) {
              customerMerged += vm.reservations[i].customers[j].firstName + " ";
              customerMerged += vm.reservations[i].customers[j].lastName + " ";
            }
            vm.reservations[i].customerMerged = customerMerged;
          }
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
