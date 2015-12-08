(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('RoomController', RoomController);

  /** @ngInject */
  function RoomController(restService) {
    var vm = this;
    vm.freeRoomsOnly = false;
    vm.startDate = new Date();
    vm.endDate = new Date();
    vm.endDate.setDate(vm.startDate.getDate() + 7);

    restService.allRooms().then(
      function successCallback(response) {
        vm.rooms = response.data;
        console.log(vm.rooms);
      },
      function errorCallback(response) {
        alert('Error: Could not receive room data');
      }
    );
  }
})();
