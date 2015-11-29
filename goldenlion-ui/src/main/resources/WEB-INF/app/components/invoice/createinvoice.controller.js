(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CreateInvoiceController', CreateInvoiceController);

  /** @ngInject */
  function CreateInvoiceController(restService, $mdDialog, $state) {
    var vm = this;

    vm.customers = [];
    vm.customerSearchText = "";
    vm.selectedCustomer = null;

    restService.allCustomer().then(
      function successCallback(response) {
        vm.customers = [];
        for (var i = 0; i < response.data.length; i++) {
          vm.customers.push({
            id: response.data[i].id,
            firstname: response.data[i].firstName,
            lastname: response.data[i].lastName,
          });
        }
      },
      function errorCallback() {
        alert('Error: Could not receive customer data');
      }
    );

    vm.reservationsbycustomer = [];
    vm.selectedCustomerChange = function (customer) {
      if (customer === undefined)
        return;

      restService.allReservationByCustomerId(customer.id).then(
        function successCallback(response) {
          vm.reservationsbycustomer = response.data;
        },
        function errorCallback() {
          alert('Error: Could not receive reservation data');
        }
      );

    }

    vm.createInvoice = function(reservationid) {
      var confirm = $mdDialog.confirm( {
        title: "Rechnung erstellen?",
        content: "Möchten Sie für diese Reservierung eine Rechnung erstellen?",
        cancel: "Nein (zurück)",
        ok: "Ja (erstellen)"
      });
      $mdDialog.show(confirm).then(function() {
          restService.createInvoice(reservationid).then(
            function successCallback(response) {
              //done. back to home
              vm.reservationsbycustomer.splice(findReservation(reservationid), 1);
            },
            function errorCallback(response) {
              alert('Error: Could not create Invoice')
            }
          );
      });
    }

    var findReservation = function(reservationId) {
      for (var i = 0; i < vm.reservationsbycustomer.length; i++) {
        if (vm.reservationsbycustomer[i].id == reservationId) {
          return i;
        }
      }
    };
  }
})();
