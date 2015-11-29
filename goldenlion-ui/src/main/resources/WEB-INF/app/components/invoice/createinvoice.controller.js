(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CreateInvoiceController', CreateInvoiceController);

  /** @ngInject */
  function CreateInvoiceController(restService) {
    var vm = this;

    vm.customers = [];
    vm.createInvoiceCtrl = "";

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

  }
})();
