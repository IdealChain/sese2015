(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CustomerReservationController', CustomerReservationController);

  /** @ngInject */
  function CustomerReservationController($mdDialog, restService, $state, $log, $filter, toastr) {
    var vm = this;
    initializeCustomerReservation();

    function initializeCustomerReservation() {
      vm.fromDate = new Date();
      vm.toDate = new Date(
        vm.fromDate.getFullYear(),
        vm.fromDate.getMonth(),
        vm.fromDate.getDate() + 1);
      vm.adults = 2;
      vm.children = 0;
      vm.searched = false;
      vm.rooms = [];
      vm.selectedRoom = null;
      vm.customer = null;
    }

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
    ];

    vm.maxDate = new Date(
      vm.fromDate.getFullYear() + 1,
      vm.fromDate.getMonth(),
      vm.fromDate.getDate());
    vm.minDate = vm.fromDate;

    vm.searchAvailableRooms = function () {
      vm.rooms = [];
      vm.selectedRoom = null;
      vm.searched = false;
      vm.searching = 0;

      var personCount = vm.adults + vm.children;
      var formattedFromDate = $filter('date')(new Date(vm.fromDate), 'yyyy-MM-dd');
      var formattedToDate = $filter('date')(new Date(vm.toDate), 'yyyy-MM-dd');

      if (angular.isUndefinedOrNull(vm.fromDate) || angular.isUndefinedOrNull(vm.toDate) || vm.fromDate < vm.minDate || vm.toDate < vm.minDate || vm.toDate <= vm.fromDate || vm.fromDate > vm.maxDate || vm.toDate > vm.maxDate) {
        toastr.error("Der eingegebene Datumsbereich ist nicht gültig!", "Datumsbereich ungültig");
      } else {
        vm.searching++;
        restService.freeRoomsForPersons(formattedFromDate, formattedToDate, personCount).then(function (response) {
          vm.rooms = response.data;
          if (vm.rooms.length == 0) {
            vm.searched = false;
            toastr.info("Für dieses Datum und diese Personenzahl wurde kein Raum gefunden!", "Keine Räume gefunden!");
          } else {
            vm.searched = true;

            // fetch room rates
            vm.rooms.forEach(function(room) {
              room.rate = 0;
              restService.roomRate(room.id, vm.adults, vm.children).then(
                function successCallback(response) {
                  room.rate = response.data;
                },
                function errorCallback(response) {
                  $log.warn(response);
                }
              );
            });
          }
        }).finally(function() {
          vm.searching--;
        })
      }
    };

    vm.submitRoomReservation = function () {
      vm.reservation = {
        roomId: vm.selectedRoom.id,
        startDate: vm.fromDate.toISOString(),
        endDate: vm.toDate.toISOString(),
        numberOfAdults: vm.adults,
        numberOfChildren: vm.children,
        customer: vm.customer
      };
      restService.createCustomerReservation(vm.reservation).then(function successCallback(response) {
        toastr.success("Ihre Reservierung konnte erfolgreich im System eingetragen werden!", "Reservierung erfolgreich!");
        initializeCustomerReservation();
      }, function errorCallback(response) {
        var errorMsg = '';
        if (!angular.isUndefinedOrNull(response.data) && !angular.isUndefinedOrNull(response.data.errors)) {
          response.data.errors.forEach(function (error) {
            errorMsg += error.defaultMessage;
          })
        }
        toastr.error("Folgende Fehler sind aufgetreten: " + errorMsg, "Reservierung fehlgeschlagen!");
      });
    };

    vm.selectRoom = function (room) {
      vm.selectedRoom = room;
    };

    vm.unselectRoom = function () {
      vm.selectedRoom = null;
    };
  }
})();
