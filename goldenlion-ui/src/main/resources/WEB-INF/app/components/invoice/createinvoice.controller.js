(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CreateInvoiceController', CreateInvoiceController);

  /** @ngInject */
  function CreateInvoiceController(restService) {
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
  }
})();
