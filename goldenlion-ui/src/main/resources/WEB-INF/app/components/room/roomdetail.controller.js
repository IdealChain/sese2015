(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('RoomDetailController', RoomDetailController);

  /** @ngInject */
  function RoomDetailController(restService, $stateParams) {
    var vm = this;
    vm.roomid = $stateParams.roomid;

    // fetch room data
    restService.roomById(vm.roomid).then(
      function successCallback(response) {
        vm.room = response.data;
      },
      function errorCallback() {
        alert('Error: Could not receive room data');
      }
    );

    // fetch room reservations
    // TODO
  }
})();
