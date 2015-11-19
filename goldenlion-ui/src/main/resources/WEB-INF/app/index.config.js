(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .config(config)
    .config(function ($httpProvider) {
      $httpProvider.interceptors.push('authInterceptor');
    });

  /** @ngInject */
  function config($logProvider) {
    // Enable log
    $logProvider.debugEnabled(true);
  }

})();
