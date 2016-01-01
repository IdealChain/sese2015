(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CustomerController', CustomerController);

  /** @ngInject */
  function CustomerController($scope, $state, restService, toastr) {
    var vm = this;

    vm.customer = {
      firstName: '',
      lastName: '',
      birthday: new Date(),
      gender: '',
      street: '',
      streetExtension: '',
      state: '',
      postalCode: '',
      city: '',
      companyName: '',
      discount: '',
      telephone: '',
      fax: '',
      email: '',
      web: '',
      notes: ''
    };

    vm.genders = [
      {
        short: 'MALE',
        text: 'Männlich'
      },
      {
        short: 'FEMALE',
        text: 'Weiblich'
      },
      {
        short: 'NAN',
        text: 'Keine Angabe'
      }
    ]

    vm.createCustomer = function () {
      restService.createCustomer(vm.customer).then(
        function successCallback(response) {
          $scope.$emit("customerCreated", response.data);
          $state.go("home");
        },
        function errorCallback() {
          toastr.error("Der Kunde konnte nicht erstellt werden!", "Kundendaten ungültig");
        });
    }
  }
})();
