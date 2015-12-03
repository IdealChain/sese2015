(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('ProtocolController', CreateProtocolController);

  /** @ngInject */
  function CreateProtocolController(restService, $mdDialog) {
    var vm = this;

    //Load protocolEntries from Server
    vm.protocols = restService.getProtocols();
    vm.searchText = "";

    vm.formatToReadableString = function (iso8601Array) {
      return new Date(iso8601Array[0], iso8601Array[1] - 1, iso8601Array[2], iso8601Array[3], iso8601Array[4], iso8601Array[5]);
    }

    vm.querySearch = function (query) {
      var results = vm.protocols.filter(createFilterFor(query));
      return results;
    }

    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);
      return function filterFn(item) {
        var searchableString = item.user.firstName + " " + item.user.lastName + " " + item.protocolMessage;
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
