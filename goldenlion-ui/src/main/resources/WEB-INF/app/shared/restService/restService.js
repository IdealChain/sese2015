(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .service('restService', restService);

  function restService($http, $location, $resource) {
    var vm = this;
    var backend = $location.absUrl().substr(0, $location.absUrl().indexOf('/#'));
    var protocol = $resource(backend + '/api/protocol');

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

    vm.updateCustomer = function (customer) {
      return $http({
        method: 'PUT',
        data: customer,
        url: backend + "/api/customers/" + customer.id
      })
    };

    vm.customerByIds = function (customerids) {
      return $http({
        method: 'GET',
        url: backend + "/api/customers/" + customerids.join(",")
      })
    };

    vm.customerById = function (customerId) {
      return $http({
        method: 'GET',
        url: backend + "/api/customers/" + customerId
      })
    };

    vm.allCustomer = function () {
      return $http({
        method: 'GET',
        url: backend + "/api/customers"
      })
    };

    vm.allRooms = function () {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms"
      })
    };

    vm.freeRooms = function (from, to) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms?freefrom=" + from + "&freeto=" + to
      })
    };

    vm.freeRoomsForPersons = function (from, to, maxPerson) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms?freefrom=" + from + "&freeto=" + to + "&maxPersons=" + maxPerson
      })
    };

    vm.roomById = function (roomid) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms/" + roomid
      })
    };

    vm.roomRate = function (roomid, numberOfAdults, numberOfChildren) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms/" + roomid + "/rate?numberOfAdults=" + numberOfAdults + "&numberOfChildren=" + numberOfChildren
      })
    };

    vm.roomByIds = function (roomids) {
      return $http({
        method: 'GET',
        url: backend + "/api/rooms/" + roomids.join(",")
      })
    };

    vm.allReservation = function () {
      return $http({
        method: 'GET',
        url: backend + "/api/reservation/"
      })
    };

    vm.allReservationByCustomerId = function (customerid) {
      return $http({
        method: 'GET',
        url: backend + "/api/reservation?customerid=" + customerid
      })
    };

    vm.allReservationByRoomId = function (roomid, start, end) {
      return $http({
        method: 'GET',
        url: backend + "/api/reservation?roomid=" + roomid + "&startdate=" + start + "&enddate=" + end
      })
    };

    vm.createReservation = function (reservation) {
      return $http({
        method: 'POST',
        data: reservation,
        url: backend + "/api/reservation/"
      })
    };

    vm.deleteReservation = function (reservationid) {
      return $http({
        method: 'DELETE',
        url: backend + "/api/reservation/" + reservationid
      });
    };

    vm.createInvoice = function (reservationid, billedcustomerid) {
      return $http({
        method: 'POST',
        url: backend + "/api/invoice?reservationid=" + reservationid + "&billedcustomerid=" + billedcustomerid
      });
    };

    vm.getInvoice = function (invoiceid) {
      return $http({
        method: 'GET',
        url: backend + "/api/invoice/" + invoiceid
      });
    };

    vm.invalidateInvoice = function (invoiceid) {
      return $http({
        method: 'DELETE',
        url: backend + "/api/invoice/" + invoiceid
      });
    };

    vm.allInvoices = function () {
      return $http({
        method: 'GET',
        url: backend + "/api/invoice"
      });
    };

    vm.allInvoicesIncludeInvalidated = function() {
      return $http({
        method: 'GET',
        url: backend + "/api/invoice?includeInvalidated=true"
      });
    };

    vm.getHotel = function () {
      return $http({
        method: 'GET',
        url: backend + "/api/hotel"
      });
    };

    vm.getProtocols = function () {
      return protocol.query();
    };

    vm.createCustomerReservation = function (customerReservation) {
      return $http({
        method: 'POST',
        data: customerReservation,
        url: backend + "/api/customerReservation"
      })
    };
  }
})();
