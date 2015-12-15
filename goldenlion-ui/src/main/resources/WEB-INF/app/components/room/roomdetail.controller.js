(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('RoomDetailController', RoomDetailController);

  /** @ngInject */
  function RoomDetailController($stateParams, $scope, $compile, $mdDialog, $state, restService) {
    var vm = this;
    vm.roomid = $stateParams.roomid;
    vm.room = {};

    var restError = function (msg) {
      var errorDlg = $mdDialog.alert({
        title: "Fehler",
        content: msg,
        ok: "Ok"
      });
      $mdDialog.show(errorDlg).then(function () {
        $state.go("room");
      });
    };

    // lazy room reservation fetching
    var fetchReservations = function (start, end, timezone, callback) {
      restService.allReservationByRoomId(vm.roomid, moment(start).format("YYYY-MM-DD"), moment(end).format("YYYY-MM-DD")).then(
        function successCallback(response) {

          // translate reservation items to fullcalendar events
          // see http://fullcalendar.io/docs/event_data/Event_Object/
          var events = response.data.map(function (reservation) {
              return {
                id: reservation.id,
                title: reservation.customers.map(function (customer) {
                  return customer.firstName + ' ' + customer.lastName;
                }).join(", "),
                start: moment(reservation.startDate),
                end: moment(reservation.endDate),
                className: 'reservationEvent',
                allDay: true
              };
            }
          );

          callback(events);
        },
        function errorCallback() {
          restError("Die Zimmerreservierungen konnten nicht abgerufen werden.");
        }
      );
    };
    vm.eventSources = {events: fetchReservations};

    // add tooltip markup on rendering
    var eventRender = function (event, element) {
      var daterange = moment(event.start).format("DD.MM.") + " – " + moment(event.end).format("DD.MM.YYYY");
      element.append('<md-tooltip class="eventTooltip">Reservierung: ' + daterange + '</md-tooltip>');
      $compile(element)($scope);
    };

    // ui-calendar settings
    $scope.uiConfig = {
      calendar: {
        lang: 'de',
        header: {
          left: 'prev title next',
          center: '',
          right: 'today'
        },
        // completely hide event beginning times
        timeFormat: ' ',
        firstDay: 1,
        dayNames: ["Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"],
        dayNamesShort: ["So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"],
        monthNames: ["Jänner", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"],
        monthNamesShort: ["Jan", "Feb", "März", "Apr", "Mai", "Jun", "Jul", "Aug", "Sept", "Okt", "Nov", "Dez"],
        buttonText: {today: 'Aktuell', month: 'Monat', week: 'Woche', day: 'Tag'},
        eventRender: eventRender
      }
    };

    // fetch room data
    restService.roomById(vm.roomid).then(
      function successCallback(response) {
        vm.room = response.data;
      },
      function errorCallback() {
        restError("Die Zimmerdaten konnten nicht abgerufen werden.");
      }
    );

  }
})();
