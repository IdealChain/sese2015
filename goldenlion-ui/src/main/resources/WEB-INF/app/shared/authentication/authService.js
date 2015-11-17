(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .service('authService', authService);

  function authService($cookies, $log, $rootScope) {
    var vm = this;
    var user = angular.fromJson($cookies.get('authtoken'));

    var onLoginSuccess = $rootScope.$on('loginSuccess', function (event, data) {
      $log.log(data);
      $cookies.put('authtoken', angular.toJson(data));
      user = data;
    });
    $rootScope.$on('$destroy', onLoginSuccess);

    vm.isLoggedIn = function () {
      if (user != null) {
        return true;
      }
      return false;
    };

    vm.getUsername = function () {
      if (user != null) {
        return user.username;
      }
    }
  }
})();
