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

    vm.roomById = function (roomid) {
      return $http({
        method: 'GET',
        url: backend + "/api/room/" + roomid
      })
    };

    vm.roomByIds = function (roomids) {
      return $http({
        method: 'GET',
        url: backend + "/api/room/" + roomids.join(",")
      })
    };

    vm.allReservation = function() {
      return $http({
        method: 'GET',
        url: backend + "/api/reservation/"
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
    }
  }
})();
