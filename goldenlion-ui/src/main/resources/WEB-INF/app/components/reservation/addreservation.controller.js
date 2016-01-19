(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('AddReservationController', AddReservationController);

  /** @ngInject */
  function AddReservationController($scope, $state, $location, $mdDialog, restService, $log) {
    var vm = this;
    var roomid = parseInt($location.search().roomid);
    var startdate = new Date($location.search().startdate);
    var enddate = new Date($location.search().enddate);
    var adults = parseInt($location.search().adults);
    var children = parseInt($location.search().children);

    if (isNaN(roomid) || isNaN(startdate.getTime()) || isNaN(enddate.getTime()) || isNaN(adults) || isNaN(children)) {
      alert('Error: Invalid input');
      return;
    }

    vm.startdate = startdate;
    vm.enddate = enddate;
    vm.adults = adults;
    vm.children = children;
    vm.duration = dateDiffInDays(startdate, enddate);
    if (vm.duration == 1) vm.duration += " Tag";
    else vm.duration += " Tage";
    vm.customerSearchText = "";
    vm.selectedCustomers = [];
    vm.selectedCustomer = null;

    vm.selectedItemChange = function (item) {
      if (angular.isUndefined(item))
        return;
      $log.log("selectedItemChange(" + item + ")");
      //vm.customers = $filter('filter')(vm.customers, {id: '!' + item.id});
    };

    vm.onAppend = function(chip) {
      $log.log("transform " + chip);
      if (angular.isObject(chip)) {
        return chip;
      }
    };

    vm.roomById = function () {
      restService.roomById(roomid).then(
        function successCallback(response) {
          vm.room = response.data;
        },
        function errorCallback() {
          alert('Error: Could not receive room data');
        });
    };
    vm.roomById();

    vm.allCustomer = function () {
      restService.allCustomer().then(
        function successCallback(response) {
          vm.customers = [];
          for (var i = 0; i < response.data.length; i++) {
            vm.customers.push({
              id: response.data[i].id,
              firstname: response.data[i].firstName,
              lastname: response.data[i].lastName,
              birthday: response.data[i].birthday
            });
          }
        },
        function errorCallback() {
          alert('Error: Could not receive customer data');
        });
    };
    vm.allCustomer();

    vm.createReservation = function () {
      var reservationRequest = {
        roomId: vm.room.id,
        startDate: vm.startdate,
        endDate: vm.enddate,
        numberOfAdults: vm.adults,
        numberOfChildren: vm.children,
        customerIds: []
      };
      for (var i = 0; i < vm.selectedCustomers.length; i++) {
        reservationRequest.customerIds.push(vm.selectedCustomers[i].id);
      }
      $log.log(angular.toJson(reservationRequest));
      restService.createReservation(reservationRequest).then(
        function successCallback(response) {
          $scope.$emit("reservationCreated", response.data);
          $state.go("home");
        },
        function errorCallback(response) {
          $log.log(response);
          var errorMessage = "Die Reservierung konnte nicht erstellt werden. (" + response.data.message + ")";
          if (response.status == 409)
            errorMessage = "Für die gegebenen Daten (Raumnummer und Zeitraum) besteht bereits eine Reservierung.";
          if (response.status == 400)
            errorMessage = "Die übermittelten Daten (Raumnummer, Zeitraum, Kunden) sind fehlerhaft.";

          var errorDlg = $mdDialog.alert( {
            title: "Fehler",
            content: errorMessage,
            ok: "Zurück zur Übersicht"
          });
          $mdDialog.show(errorDlg).then(function() {
            $state.go("reservation");
          });
        });
    };
  }

  //http://stackoverflow.com/questions/3224834/get-difference-between-2-dates-in-javascript
  function dateDiffInDays(a, b) {
    var utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
    var utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());
    return Math.floor((utc2 - utc1) / (1000 * 60 * 60 * 24));
  }
})();
