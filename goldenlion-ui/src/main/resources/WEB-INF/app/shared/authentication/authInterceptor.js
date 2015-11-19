(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .factory('authInterceptor', authInterceptor);

  function authInterceptor($q, authService) {
    return {
      request: function (config) {
        config.headers = config.headers || {};
        config.headers['X-Auth-Token'] = authService.getAuthToken();
        return config;
      },
      response: function (response) {
        if (response.status === 403 || response.status === 401) {
          // insert code to redirect to custom unauthorized page
        }
        return response || $q.when(response);
      }
    };
  }
})();
