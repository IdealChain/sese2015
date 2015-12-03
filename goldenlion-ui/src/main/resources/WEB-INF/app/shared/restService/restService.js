(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .service('restService', restService);

  function restService($http, $location) {
    var vm = this;
    var backend = $location.absUrl().substr(0,$location.absUrl().indexOf('/#'));

    vm.login = function (credentials) {
      return $http({
        method: 'POST',
        data: credentials,
        url: backend + "/api/login"
      });
    };

    vm.register = function (registration) {
      return $http({
        method: 'POST',
        data: registration,
        url: backend + "/api/register"
      })
    };

    vm.createCustomer = function (customer) {
      return $http({
        method: 'POST',
        data: customer,
        url: backend + "/api/customers"
      })
    };

    vm.customerByIds = function (customerids) {
      return $http({
        method: 'GET',
        url: backend + "/api/customers/" + customerids.join(",")
      })
    };

    vm.allCustomer = function() {
      return $http({
        method: 'GET',
        url: backend + "/api/customers"
      })
    };

    vm.allRooms = function() {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms"
      })
    };

    vm.freeRooms = function(from, to) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms?freefrom=" + from + "&freeto=" + to
      })
    };

    vm.roomById = function (roomid) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms/" + roomid
      })
    };

    vm.roomByIds = function (roomids) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms/" + roomids.join(",")
      })
    };

    vm.allReservation = function() {
      return $http({
        method: 'GET',
        url: backend + "/api/reservation/"
      })
    };

    vm.allReservationByCustomerId = function(customerid) {
      return $http({
        method: 'GET',
        url: backend + "/api/reservation?customerid=" + customerid
      })
    };

    vm.createReservation = function(reservation) {
      return $http({
        method: 'POST',
        data: reservation,
        url: backend + "/api/reservation/"
      })
    };

    vm.deleteReservation = function(reservationid) {
      return $http({
        method: 'DELETE',
        url: backend + "/api/reservation/" + reservationid
      });
    };

    vm.createInvoice = function(reservationid) {
      return $http({
        method: 'POST',
        url: backend + "/api/invoice?reservationid=" + reservationid
      });
    };

    vm.allInvoices = function() {
      return $http({
        method: 'GET',
        url: backend + "/api/invoice"
      });
    }
  }
})();
