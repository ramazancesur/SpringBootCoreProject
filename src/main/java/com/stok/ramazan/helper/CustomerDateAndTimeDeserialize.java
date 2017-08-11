package com.stok.ramazan.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerDateAndTimeDeserialize extends JsonDeserializer<Date> {

  SimpleDateFormat oldFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
  //SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");

  @Override
  public Date deserialize(JsonParser paramJsonParser,
                          DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException {

    String str = paramJsonParser.getText().trim();
    try {
      Date date = oldFormat.parse(str);
      return date;
      //oldFormat.applyPattern("dd/MM/yyyy");
      //  return newFormat.parse(oldFormat.format(date));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return paramDeserializationContext.parseDate(str);
  }
}
