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
          label: 'Kunde',
          parent: 'home'
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
      })
      .state('addreservation', {
        url: '/reservation/addreservation',
        templateUrl: 'app/components/reservation/addreservation.html',
        controller: 'ReservationController',
        controllerAs: 'reservationCtrl',
        ncyBreadcrumb: {
          label: 'Reservierung hinzufügen',
          parent: 'reservation'
        },
        authenticate: true
      });
    $urlRouterProvider.otherwise('/');
  }
})();
