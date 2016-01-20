(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('InvoicesController', InvoicesController);

  /** @ngInject */
  function InvoicesController(restService) {
    var vm = this;

    vm.loading = 0;
    restService.allInvoicesIncludeInvalidated().then(
      function successCallback(response) {
        vm.invoices = response.data;
      },
      function errorCallback(response) {
        alert('Error: Could not receive invoice data');
      }
    ).finally(function () {
      vm.loading = false;
    });
  }
})();
