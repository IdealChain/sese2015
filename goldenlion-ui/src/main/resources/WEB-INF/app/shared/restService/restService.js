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
  }
})();
