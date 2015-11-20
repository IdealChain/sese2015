(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .service('authService', authService);

  function authService($cookies, $location, $log, $rootScope) {
    var vm = this;
    var AUTHTOKEN_KEY = 'authtoken';
    var user;

    var tokenInfo = angular.fromJson($cookies.get(AUTHTOKEN_KEY));
    if (tokenInfo != null) {
      user = getUserFromToken(tokenInfo);
    }

    var onLoginSuccess = $rootScope.$on('loginSuccess', function (event, data) {
      $log.log(data);
      $cookies.put(AUTHTOKEN_KEY, angular.toJson(data));
      user = getUserFromToken(data);
    });
    $rootScope.$on('$destroy', onLoginSuccess);

    vm.isLoggedIn = function () {
      if (checkToken()) {
        return true;
      }
      return false;
    };

    vm.getUsername = function () {
      if (checkToken()) {
        return user.username;
      }
    }

    vm.getAuthToken = function () {
      if (checkToken()) {
        return user.token;
      }
    }

    vm.logout = function () {
      $cookies.remove(AUTHTOKEN_KEY);
      $location.path('/');
    }

    function checkToken() {
      if (user != null && user.expires != null && user.expires > ((new Date).getTime())) {
        return true;
      }
      return false;
    }

    function getUserFromToken(cookieToken) {
      if (cookieToken != null) {
        var tokenJsonString = atob(cookieToken.token.split(":")[0]);
        return angular.fromJson(tokenJsonString);
      }
    }
  }
})();
