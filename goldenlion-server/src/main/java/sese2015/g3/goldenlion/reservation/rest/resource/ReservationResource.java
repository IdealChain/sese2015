package sese2015.g3.goldenlion.reservation.rest.resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sese2015.g3.goldenlion.reservation.rest.domain.request.CreateReservationRequest;
import sese2015.g3.goldenlion.reservation.rest.domain.response.CreateReservationResponse;
import sese2015.g3.goldenlion.reservation.service.ReservationService;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class ReservationResource {
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(
            value = "/reservation",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public CreateReservationResponse createReservation(@RequestBody CreateReservationRequest request) {
        log.info("Incoming Create Reservation Request... ");

        long reservationId = reservationService.createReservation(request);
        log.info("Success");

        return new CreateReservationResponse(reservationId);
    }

    @RequestMapping(
            value = "/reservation",
            method = RequestMethod.GET,
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public String isAvailableForReservation(@RequestParam(value="roomid", required=true) Long roomId,
                                            @RequestParam(value="startdate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
                                            @RequestParam(value="enddate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
        log.info("Incoming Reservation Availability Request... ");
        boolean available = reservationService.isAvailable(roomId, startDate, endDate);

        return BooleanUtils.toStringTrueFalse(available);
    }
}