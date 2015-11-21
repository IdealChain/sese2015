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
      }).state('customer', {
      url: '/customer',
      templateUrl: 'app/components/customer/customer.html',
      controller: 'CustomerController',
      controllerAs: 'customerCtrl',
      ncyBreadcrumb: {
        label: 'Kunde',
        parent: 'home'
      },
      authenticate: true
    });
    $urlRouterProvider.otherwise('/');
  }
})();
