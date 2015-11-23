(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('ReservationController', ReservationController);

  /** @ngInject */
  function ReservationController($scope, $state, $location, restService) {
    var vm = this;
    var roomid = parseInt($location.search().roomid);
    var startdate = new Date($location.search().startdate);
    var enddate = new Date($location.search().enddate);

    if (isNaN(roomid) || isNaN(startdate.getTime()) || isNaN(enddate.getTime())) {
      alert('Error: Invalid input');
      return;
    }

    vm.startdate = startdate;
    vm.enddate = enddate;
    vm.duration = dateDiffInDays(startdate, enddate);
    if (vm.duration == 1)
      vm.duration += " Tag";
    else
      vm.duration += " Tage";
    vm.customerSearchText = "";

    vm.selectedItemChange = function(item) {
      console.log("selectedItemChange(" + item + ")");
      vm.customerSearchText = "";
    };
    vm.querySearch = function(customerSearchText) {
      console.log("querySearch(" + customerSearchText + ")");
      return vm.customers;
    };

    vm.roomById = function() {
      restService.roomById(roomid).then(
        function successCallback(response) {
          vm.room = response.data;
        },
        function errorCallback() {
          alert('Error: Could not receive room data');
        });
    };
    vm.roomById();

    vm.allCustomer = function() {
      restService.allCustomer().then(
        function successCallback(response) {
          vm.customers = [];
          for (var i = 0; i < response.data.length; i++) {
            vm.customers.push({
              id: response.data[i].id,
              firstname: response.data[i].firstName,
              lastname: response.data[i].lastName,
              birthday: response.data[i].birthday
            });
          }
        },
        function errorCallback() {
          alert('Error: Could not receive customer data');
        });
    };
    vm.allCustomer();
  }

  //http://stackoverflow.com/questions/3224834/get-difference-between-2-dates-in-javascript
  function dateDiffInDays(a, b) {
    var utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
    var utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());
    return Math.floor((utc2 - utc1) /(1000 * 60 * 60 * 24));
  }
})();
