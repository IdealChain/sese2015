(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($mdDialog, restService, $state) {
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
      console.log("confirm deleting: " + reservationId);
      var confirm = $mdDialog.confirm( {
        title: "Reservierung löschen?",
        content: "Möchten Sie diese Reservierung wirklich löschen?",
        cancel: "Nein (zurück)",
        ok: "Ja (löschen)"
      });
      $mdDialog.show(confirm).then(function() {
        restService.deleteReservation(reservationId).then(
          function successCallback(response) {
            //
            // Success: reservation was deleted. Now update the client model
            //
            vm.reservations.splice(findReservation(reservationId), 1);
          },
          function errorCallback(response) {
            //
            // Error: reservation was not deleted. Show a meaningful error message
            //
            var errorMessage = "Die Reservierung nicht storniert werden. (" + response.data.message + ")";
            if (response.status == 409)
              errorMessage = "Diese Reservierung wurde bereits in Rechnung gestellt und kann deshalb nicht storniert werden.";

            var errorDlg = $mdDialog.alert( {
              title: "Fehler",
              content: errorMessage,
              ok: "Ok"
            });
            $mdDialog.show(errorDlg).then(function() {
              $state.go("reservation");
            });
          }
        );
      }, function() {});
    };

    var findReservation = function(reservationId) {
      for (var i = 0; i < vm.reservations.length; i++) {
        if (vm.reservations[i].id == reservationId) {
          return i;
        }
      }
    };
  }
})();
