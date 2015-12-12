package sese2015.g3.goldenlion.reservation.mapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Mario on 09.12.2015.
 */
public class DateMapper {

    public Date asDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate asLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
