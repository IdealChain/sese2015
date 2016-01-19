(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('main', {
        url: '/',
        templateUrl: 'app/components/main/main.html',
        controller: 'MainController',
        controllerAs: 'mainCtrl'
      })
      .state('customerReservation', {
        url: '/customerReservation',
        templateUrl: 'app/components/reservation/customerReservation.html',
        controller: 'CustomerReservationController',
        controllerAs: 'customerReservationCtrl'
      })
      .state('home', {
        url: '/home',
        templateUrl: 'app/components/home/home.html',
        controller: 'HomeController',
        controllerAs: 'homeCtrl',
        ncyBreadcrumb: {
          label: 'Übersicht'
        },
        authenticate: true
      })
      .state('customer', {
        url: '/customer',
        templateUrl: 'app/components/customer/customer.html',
        controller: 'CustomerController',
        controllerAs: 'customerCtrl',
        ncyBreadcrumb: {
          label: 'Kunden hinzufügen',
          parent: 'home'
        },
        authenticate: true
      })
      .state('customerDetail', {
        url: '/customerDetail/:customerId',
        templateUrl: 'app/components/customer/customer.html',
        controller: 'CustomerController',
        controllerAs: 'customerCtrl',
        ncyBreadcrumb: {
          label: 'Kunden bearbeiten',
          parent: 'customerList'
        },
        authenticate: true
      })
      .state('customerList', {
        url: '/customerList',
        templateUrl: 'app/components/customer/customerList.html',
        controller: 'CustomerListController',
        controllerAs: 'customerListCtrl',
        ncyBreadcrumb: {
          label: 'Kundenübersicht',
          parent: 'home'
        },
        authenticate: true
      })
      .state('room', {
        url: '/room',
        templateUrl: 'app/components/room/room.html',
        controller: 'RoomController',
        controllerAs: 'roomCtrl',
        ncyBreadcrumb: {
          label: 'Zimmerübersicht',
          parent: 'home'
        },
        authenticate: true
      })
      .state('roomdetail', {
        url: '/room/:roomid',
        templateUrl: 'app/components/room/roomdetail.html',
        controller: 'RoomDetailController',
        controllerAs: 'roomDetailCtrl',
        ncyBreadcrumb: {
          label: 'Zimmer',
          parent: 'room'
        },
        authenticate: true
      })
      .state('reservation', {
        url: '/reservation',
        templateUrl: 'app/components/reservation/reservation.html',
        controller: 'ReservationController',
        controllerAs: 'reservationCtrl',
        ncyBreadcrumb: {
          label: 'Reservierungen',
          parent: 'home'
        },
        authenticate: true
      }).state('protocol', {
        url: '/protocol',
        templateUrl: 'app/components/protocol/protocol.html',
        controller: 'ProtocolController',
        controllerAs: 'protocolCtrl',
        ncyBreadcrumb: {
          label: 'Protokoll',
          parent: 'home'
        },
        authenticate: true
      })
      .state('addreservation', {
        url: '/reservation/add?roomid&startdate&enddate',
        templateUrl: 'app/components/reservation/addreservation.html',
        controller: 'AddReservationController',
        controllerAs: 'addReservationCtrl',
        ncyBreadcrumb: {
          label: 'Reservierung hinzufügen',
          parent: 'reservation'
        },
        authenticate: true
      })
      .state('createinvoice', {
        url: '/invoice/create',
        templateUrl: 'app/components/invoice/createinvoice.html',
        controller: 'CreateInvoiceController',
        controllerAs: 'createInvoiceCtrl',
        ncyBreadcrumb: {
          label: 'Rechnung erstellen',
          parent: 'home'
        },
        authenticate: true
      })
      .state('showinvoice', {
        url: '/invoice/:invoiceid',
        templateUrl: 'app/components/invoice/showinvoice.html',
        controller: 'ShowInvoiceController',
        controllerAs: 'showInvoiceCtrl',
        ncyBreadcrumb: {
          label: 'Rechnung',
          parent: 'home'
        },
        authenticate: true
      });
    $urlRouterProvider.otherwise('/');
  }
})();
