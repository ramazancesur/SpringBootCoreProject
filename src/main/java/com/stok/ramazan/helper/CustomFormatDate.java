package com.stok.ramazan.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ramazancesur on 02/10/2017.
 */
public class CustomFormatDate extends Date {

    private static DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public CustomFormatDate() {
        super();
    }

    public CustomFormatDate(long date) {
        super(date);
    }

    public CustomFormatDate(Date date) {
        super(date.getTime());
    }


    @JsonCreator
    public static CustomFormatDate forValue(String value) {
        try {
            return new CustomFormatDate(myDateFormat.parse(value));
        } catch (ParseException e) {
            return null;
        }
    }

    @JsonValue
    public String toValue() {
        return myDateFormat.format(this);
    }

    @Override
    public String toString() {
        return toValue();
    }
}
