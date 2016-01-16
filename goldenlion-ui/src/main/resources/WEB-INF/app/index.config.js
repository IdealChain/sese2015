(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .config(config)
    .config(function ($breadcrumbProvider) {
      $breadcrumbProvider.setOptions({
        prefixStateName: 'home',
        template: '<div style="padding-left: 1em; padding-top: 1em"> <span class="md-title" ng-repeat="step in steps" ng-class="{active: $last}" ng-switch="$last || !!step.abstract"> <a ng-switch-when="false" href="{{step.ncyBreadcrumbLink}}">{{step.ncyBreadcrumbLabel}}</a> <span class="md-title" ng-switch-when="false"> > </span> <span class="md-title" ng-switch-when="true">{{step.ncyBreadcrumbLabel}} </span> </span> </div>'
      });
    })
    .config(function (paginationTemplateProvider) {
      paginationTemplateProvider.setPath('shared/pagination/dirPagination.tpl.html');
    })
    .
    config(function ($httpProvider) {
      $httpProvider.interceptors.push('authInterceptor');
    });

  angular.isUndefinedOrNull = function (val) {
    return angular.isUndefined(val) || val === null
  };

  /** @ngInject */
  function config($logProvider, $mdThemingProvider) {
    // Enable log
    $logProvider.debugEnabled(true);
    $mdThemingProvider.theme('default')
      .primaryPalette('blue')
      .accentPalette('orange');
  }

})();
