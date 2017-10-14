package com.stok.ramazan.helper;

import com.stok.ramazan.jaxb.sms.Body;
import com.stok.ramazan.jaxb.sms.Header;
import com.stok.ramazan.jaxb.sms.MainBody;

/**
 * Created by Ramazan on 3.10.2016.
 */
public class SmsSender {
    private static final String USER_AGENT = "Mozilla/5.0";
    Helper helper = Helper.getInstance();

    private static String createSms(String smsContent, String telNo) {
        String marshallerData = "";
        Body body = new Body();
        telNo = "90" + telNo;
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
            return marshallerData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void sendData(String messageContext, String telNumber) {
        System.out.println("SMS Gönderiliyor..");
        String url = "http://api.netgsm.com.tr/bulkhttppost.asp?usercode=5423695847&password=46CB72&gsmno=90" + telNumber + "&message=" + messageContext + "&msgheader=HALiLARSLAN";
        try {
            Helper.readUrlNonCookie(new String(url.replace("\n", "").replace(" ", "%20").getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    /*    try {
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
        }*/
    }

}