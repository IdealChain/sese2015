(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .directive('login', loginDir);

  function loginDir() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/shared/login/login.html',
      controller: ['$scope', '$state', 'restService', LoginController],
      controllerAs: 'loginCtrl'
    };

    return directive;

    /** @ngInject */
    function LoginController($scope, $state, restService) {
      var vm = this;

      vm.credentials = {
        username: '',
        password: ''
      };

      vm.login = function () {
        restService.login(vm.credentials).then(
          function successCallback(response) {
            $scope.$emit("loginSuccess", response.data);
            $state.go("home");
          },
          function errorCallback() {
            alert("Error: login failed");
          });
      };
    }
  }
})();
