(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CustomerController', CustomerController);

  /** @ngInject */
  function CustomerController($scope, $state, $stateParams, $filter, restService, toastr) {
    var vm = this;
    vm.customerId = $stateParams.customerId;

    if (vm.customerId) {
      restService.customerById(vm.customerId).then(function successCallback(response) {
        vm.customer = response.data;
        vm.customer.street = response.data.billingAddress.street;
        vm.customer.streetExtension = response.data.billingAddress.streetNumber;
        vm.customer.state = response.data.billingAddress.country;
        vm.customer.postalCode = response.data.billingAddress.zipCode;
        vm.customer.city = response.data.billingAddress.city;
        vm.customer.birthday = new Date(response.data.birthday);
      });
    } else {
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
    }

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
      if (vm.customer.id) {
        restService.updateCustomer(vm.customer).then(
          function successCallback(response) {
            $scope.$emit("customerCreated", response.data);
            $state.go("customerList");
            toastr.info("Aktualisierung erfolgreich!", "Der Kunde wurde erfolgreich aktualisiert!");
          },
          function errorCallback() {
            toastr.error("Der Kunde konnte nicht aktualisiert werden!", "Kundendaten ungültig");
          });
      } else {
        restService.createCustomer(vm.customer).then(
          function successCallback(response) {
            $scope.$emit("customerCreated", response.data);
            $state.go("customerList");
            toastr.info("Erstellung erfolgreich!", "Der Kunde wurde erfolgreich erstellt!");
          },
          function errorCallback() {
            toastr.error("Der Kunde konnte nicht erstellt werden!", "Kundendaten ungültig");
          });
      }
    }
  }
})();
