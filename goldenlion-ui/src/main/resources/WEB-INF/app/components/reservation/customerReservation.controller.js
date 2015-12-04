(function () {
  'use strict';

  angular
    .module('goldenlionUi')
    .controller('CustomerReservationController', CustomerReservationController);

  /** @ngInject */
  function CustomerReservationController($mdDialog, restService, $state, $log) {
    var vm = this;

    vm.searchAvailableRooms = function () {
      vm.searched = true;
    }

    vm.selectRoom = function (room) {
      vm.selectedRoom = room;
    }


    vm.rooms = [
      {
        name: "Doppelzimmer Superior",
        imgSrc: "assets/images/room1.jpeg",
        price: "125",
        description: "Die Zimmer der Economy-Class befinden sich zum Teil im straßenseitigen Trakt des Hotelvordergebäudes und sind teilrenoviert. Jedes Zimmer hat selbstverständlich Bad oder Dusche. Die Zimmer sind in harmonischen Farben eingerichtet und verfügen über Flat-Screen-TVs, Radio, hochwertige Matratzen, Minibar, Telefon sowie freien Internetzugang."
      },
      {
        name: "Einzelzimmer lounge",
        imgSrc: "assets/images/room2.jpg",
        price: "75",
        description: "Die 106 elegant eingerichteten Zimmer mit je 30m2 sind mit Badewanne oder Erlebnisdusche ausgestattet. In 14 unserer Superior-Zimmer sind zwei getrennte Betten vorhanden, in den übrigen finden Sie für Ihren Schlafkomfort ein Doppelbett vor. Einige Zimmer sind durch eine Verbindungstür flexibel kombinierbar – perfekt für Familien mit Kindern."
      }
    ]

  }
})();
