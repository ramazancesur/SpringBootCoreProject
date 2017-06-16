package com.stok.ramazan.helper;

import com.stok.ramazan.jaxb.sms.Body;
import com.stok.ramazan.jaxb.sms.Header;
import com.stok.ramazan.jaxb.sms.MainBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.bind.MarshalException;

/**
 * Created by Ramazan on 3.10.2016.
 */
public class SendMessage {
  private static final String USER_AGENT = "Mozilla/5.0";
  Helper helper = Helper.getInstance();

  private static String createSms(String smsContent, String telNo) {
    String marshallerData = "";
    Body body = new Body();
    Header header = new Header();
    body.setMessage(smsContent);
    body.setNo(telNo);
    header.setCompany("NETGSM");
    header.setUserCode("5423695847");
    header.setPassword("46CB72");
    header.setMessageHeader("HALiLARSLAN");
    header.setType("1:n");

    MainBody mainBody = new MainBody();
    mainBody.setBody(body);
    mainBody.setHeader(header);
    try {
      marshallerData = Helper.marshal(mainBody);
      System.out.println(marshallerData);
      return marshallerData;
    } catch (MarshalException e) {
      e.printStackTrace();
    }
    return "";
  }


  public void sendData(String messageContext, String telNumber) {
    System.out.println("SMS Gönderiliyor..");
    try {
      URL u = new URL("http://api.netgsm.com.tr/xmlbulkhttppost.asp");

      URLConnection uc = u.openConnection();
      HttpURLConnection connection = (HttpURLConnection) uc;
      connection.setDoOutput(true);
      connection.setDoInput(true);
      connection.setRequestMethod("POST");


      OutputStream out = connection.getOutputStream();
      OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");
      wout.write(createSms(messageContext, telNumber));

      wout.flush();
      out.close();
      InputStream in = connection.getInputStream();
      int c;
      while ((c = in.read()) != -1) System.out.write(c);
      System.out.println();
      in.close();
      out.close();

      connection.disconnect();
    } catch (IOException e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }

}