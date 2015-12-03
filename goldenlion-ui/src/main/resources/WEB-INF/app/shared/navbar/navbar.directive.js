(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .directive('navbar', navbar);

  /** @ngInject */
  function navbar() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/shared/navbar/navbar.html',
      controller: ['authService', NavbarController],
      controllerAs: 'navbarCtrl'
    };

    return directive;

    /** @ngInject */
    function NavbarController(authService) {
      var vm = this;
      vm.username = authService.getUsername();

      vm.logout = function () {
        authService.logout();
      }

      vm.hasRole = function (roleName) {
        return authService.hasRole(roleName);
      }
    }
  }
})();
