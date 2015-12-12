(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('RoomController', RoomController)
    .config(mdDatepickerCfg)

  /** @ngInject */
  function RoomController(restService) {
    var vm = this;
    vm.freeRoomsOnly = false;
    vm.displayAll = true;

    vm.startDate = moment().toDate();
    vm.endDate = moment().add(7, 'days').toDate();
    vm.minStartDate = moment().add(-1, 'days').toDate();
    vm.minEndDate = vm.startDate;

    restService.allRooms().then(
      function successCallback(response) {
        vm.rooms = response.data;
      },
      function errorCallback(response) {
        alert('Error: Could not receive room data');
      }
    );

    vm.onStartDateChange = function () {
      vm.minEndDate = moment(vm.startDate).add(1, 'days').toDate();
      if (vm.endDate < vm.minEndDate) {
        vm.endDate = moment(vm.minEndDate).toDate();
      }
    };

    vm.filter = function() {
      vm.displayAll = false;
      restService.freeRooms(moment(vm.startDate).format("YYYY-MM-DD"), moment(vm.endDate).format("YYYY-MM-DD")).then(
        function successCallback(response) {
          vm.roomsFiltered = response.data;
        },
        function errorCallback(response) {
          alert('Error: Could not receive filtered room data');
        }
      );
    };

    vm.getRooms = function() {
      if (vm.displayAll)
        return vm.rooms;
      else {
        return vm.roomsFiltered;
      }
    };

    vm.freeRoomsChange = function() {
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
