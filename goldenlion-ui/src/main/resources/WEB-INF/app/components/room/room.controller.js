(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('RoomController', RoomController)
    .config(mdDatepickerCfg);

  /** @ngInject */
  function RoomController(restService) {
    var vm = this;
    vm.freeRoomsOnly = false;
    vm.displayAll = true;
    vm.loading = 0;

    vm.startDate = moment().toDate();
    vm.endDate = moment().add(7, 'days').toDate();
    vm.minStartDate = moment().add(-1, 'days').toDate();
    vm.minEndDate = vm.startDate;

    vm.adults = 2;
    vm.children = 0;

    vm.loading++;
    restService.allRooms().then(
      function successCallback(response) {
        vm.rooms = response.data;
      },
      function errorCallback(response) {
        alert('Error: Could not receive room data');
      }
    ).finally(function () {
      vm.loading = false;
    });

    vm.onStartDateChange = function () {
      vm.minEndDate = moment(vm.startDate).add(1, 'days').toDate();
      if (vm.endDate < vm.minEndDate) {
        vm.endDate = moment(vm.minEndDate).toDate();
      }
    };

    vm.filter = function () {
      vm.displayAll = false;
      vm.roomsFiltered = [];
      vm.loading++;
      var persons = vm.adults + vm.children;
      restService.freeRoomsForPersons(moment(vm.startDate).format("YYYY-MM-DD"), moment(vm.endDate).format("YYYY-MM-DD"), persons).then(
        function successCallback(response) {
          vm.roomsFiltered = response.data;
        },
        function errorCallback(response) {
          alert('Error: Could not receive filtered room data');
        }
      ).finally(function () {
        vm.loading--;
      });
    };

    vm.getRooms = function () {
      if (vm.displayAll)
        return vm.rooms;
      else {
        return vm.roomsFiltered;
      }
    };

    vm.freeRoomsChange = function () {
      if (!vm.freeRoomsOnly) {
        vm.displayAll = true;
        vm.roomsFiltered = [];
      }
    };
  }

  function mdDatepickerCfg($mdDateLocaleProvider) {
    $mdDateLocaleProvider.formatDate = function (date) {
      return moment(date).format('DD.MM.YYYY');
    }
  }
})();
