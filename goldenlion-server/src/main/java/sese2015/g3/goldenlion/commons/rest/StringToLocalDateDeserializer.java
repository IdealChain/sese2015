package sese2015.g3.goldenlion.commons.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Mario on 22.11.2015.
 */
public class StringToLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDate date = LocalDate.parse(jp.getValueAsString(), formatter);
        return date;
    }
}
