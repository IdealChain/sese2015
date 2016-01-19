(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CustomerListController', CustomerListController);

  /** @ngInject */
  function CustomerListController(restService) {
    var vm = this;
    vm.loading = 0;
    vm.loading++;
    vm.searchText = "";

    restService.allCustomer().then(
      function successCallback(response) {
        vm.customers = response.data;
      },
      function errorCallback(response) {
        alert('Error: Could not receive customers data');
      }
    ).finally(function () {
      vm.loading = false;
    });

    vm.querySearch = function (query) {
      if (query) {
        var results = vm.customers.filter(createFilterFor(query));
        return results;
      } else {
        return vm.customers;
      }
    }

    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);
      return function filterFn(item) {
        var searchableString = item.firstName + " " + item.lastName + " " + item.email;
        searchableString = angular.lowercase(searchableString);
        var splitBySpace = lowercaseQuery.split(" ");
        var result = true;
        splitBySpace.forEach(function (searchPart) {
          if (searchableString.indexOf(searchPart) < 0) {
            result = false;
          }
        });
        return result;
      };
    }

  }
})();
