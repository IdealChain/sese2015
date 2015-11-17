(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .run(runBlock);

  /** @ngInject */
  function runBlock($rootScope, $location, $log, authService) {
    var onStateChangeStart = $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
      if (toState.authenticate && !authService.isLoggedIn()) {
        $rootScope.returnToState = toState.url;
        $rootScope.returnToStateParams = toParams.Id;
        $location.path('/');
      }
    });
    $rootScope.$on('$destroy', onStateChangeStart);

    $log.debug('runBlock end');
  }

})();
