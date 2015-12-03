(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .service('authService', authService);

  function authService($cookies, $location, $log, $rootScope) {
    var vm = this;
    var AUTHTOKEN_KEY = 'authtoken';
    var user;
    var tokenInfo;

    tokenInfo = angular.fromJson($cookies.get(AUTHTOKEN_KEY));
    if (tokenInfo != null) {
      user = getUserFromToken(tokenInfo);
    }

    var onLoginSuccess = $rootScope.$on('loginSuccess', function (event, data) {
      $log.log(data);
      tokenInfo = angular.toJson(data);
      $cookies.put(AUTHTOKEN_KEY, tokenInfo);
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

    vm.hasRole = function (roleName) {
      if (checkToken()) {
        var rolesObject = user.roles;
        var hasRole = false;
        Object.keys(rolesObject).forEach(function (key) {
          if (key == roleName && rolesObject[key]) {
            hasRole = true;
          }
        })
        return hasRole;
      }
    }

    vm.getAuthToken = function () {
      if (checkToken() && tokenInfo != null) {
        return tokenInfo.token;
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
