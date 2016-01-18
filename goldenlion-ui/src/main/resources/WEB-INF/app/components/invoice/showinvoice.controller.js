(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('ShowInvoiceController', ShowInvoiceController);

  /** @ngInject */
  function ShowInvoiceController(restService, $state, $stateParams, $mdDialog) {
    var vm = this;
    vm.loading = 0;

    vm.hotel = null;
    vm.invoice = null;
    vm.invoiceid = $stateParams.invoiceid;

    var restError = function (msg) {
      var errorDlg = $mdDialog.alert({
        title: "Fehler",
        content: msg,
        ok: "Ok"
      });
      $mdDialog.show(errorDlg).then(function () {
        $state.go("home");
      });
    };

    // fetch hotel data
    vm.loading++;
    restService.getHotel().then(
      function successCallback(response) {
        vm.hotel = response.data;
      },
      function errorCallback() {
        restError("Die Hoteldaten konnten nicht abgerufen werden.");
      }
    ).finally(function() {
      vm.loading--;
    });

    // fetch invoice
    vm.loading++;
    restService.getInvoice(vm.invoiceid).then(
      function successCallback(response) {
        vm.invoice = response.data;
      },
      function errorCallback() {
        restError("Die Rechnung konnte nicht abgerufen werden.");
      }
    ).finally(function() {
      vm.loading--;
    });

    vm.print = function() {
      window.print();
    }
  }
})();
