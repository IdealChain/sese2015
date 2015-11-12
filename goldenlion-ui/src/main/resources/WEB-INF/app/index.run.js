(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log) {

    $log.debug('runBlock end');
  }

})();
