(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .service('restService', restService);

  function restService($http, $location) {
    var vm = this;
    var backend = $location.absUrl().substr(0,$location.absUrl().indexOf('#'));	// TODO: Get this parameter from backend

    vm.login = function (credentials) {
      return $http({
        method: 'POST',
        data: credentials,
        url: backend + "/login"
      });
    };

    vm.register = function (registration) {
      return $http({
        method: 'POST',
        data: registration,
        url: backend + "/register"
      })
    };
  }
})();
