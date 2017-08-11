package com.stok.ramazan.helper;

import com.google.common.io.BaseEncoding;
import com.stok.ramazan.settings.OSDetector;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Helper {
  private static final String file_DIR = "/C:/FileServer/fileMRP";
  private static final String file_DIR_LINUX = "/fileFiles/tempFile";
  private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);
  public static Map<String, Response> fileMap = new LinkedHashMap<>();
  private static Helper instance;

  private Helper() {

  }

  public static Helper getInstance() {
    if (instance == null) {
      instance = new Helper();
    }
    return instance;
  }


  public List<String> getSystemUserNameList() {
    List<String> lstMacUser = new LinkedList<>();

    File actual = new File("/Users/");
    for (File f : actual.listFiles()) {
      if (f.isDirectory()) {
        System.out.println(f.getName());
        lstMacUser.add(f.getName());
      }
    }
    return lstMacUser;
  }


  public String createCurrentDate() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    String formatted = format1.format(cal.getTime());
    return formatted;
  }
  // Android için bitmap
  public byte[] decodeBase64(String input) {
    byte[] imageByte;

    BASE64Decoder decoder = new BASE64Decoder();
    try {
      imageByte = decoder.decodeBuffer(input);
      return imageByte;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Marshalling supplied object to XML document by JAXB annotations and
   * serializing it to String
   * @param obj object to be marshalled
   * @return serialized XML document
   * @throws MarshalException
   */
  public static String marshal(Object obj) throws MarshalException {
    try {
      StringWriter sw = new StringWriter();
      JAXBContext jc = JAXBContext.newInstance(obj.getClass());
      Marshaller m = jc.createMarshaller();
      // m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      m.setProperty(CharacterEscapeHandler.class.getName(),
          new CharacterEscapeHandler() {
            @Override
            public void escape(char[] ac, int i, int j, boolean flag,
                               Writer writer) throws IOException {
              writer.write(ac, i, j);
            }
          });
      m.marshal(obj, sw);
      return sw.toString();
    } catch (JAXBException jbe) {
      throw new MarshalException("Error when marshalling " + obj.getClass().getCanonicalName(), jbe);
    }
  }

  /**
   * Unmarshalling itself from XML document by JAXB annotations
   * @param xml   xml document serialized as String
   * @param clazz Class to which shoud be object unmarshalled
   * @return serialized XML document
   * @throws JAXBException
   */
  public static Object unmarshal(String xml, Class clazz) throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance(clazz);
    Unmarshaller u = jc.createUnmarshaller();
    return u.unmarshal(new StringReader(xml));
  }

  public static long generateEightDigtNumber() {
    long timeSeed = System.nanoTime(); // to get the current date time value
    double randSeed = Math.random() * 1000; // random number generation
    long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
    String s = midSeed + "";
    String subStr = s.substring(0, 9);
    int finalSeed = Integer.parseInt(subStr); // integer value

    return finalSeed;
  }

  public static String readUrlNonCookie(String urlString) throws Exception {
    BufferedReader reader = null;
    try {
      URL url = new URL(urlString);
      URLConnection connection = url.openConnection();
      BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"), 8);
      StringBuffer buffer = new StringBuffer();
      int read;
      char[] chars = new char[1024];
      while ((read = reader2.read(chars)) != -1)
        buffer.append(chars, 0, read);
      return buffer.toString();
    } finally {
      if (reader != null)
        reader.close();
    }
  }

  public static synchronized String generateUnique() {
    String ts = String.valueOf(System.currentTimeMillis());
    String rand = UUID.randomUUID().toString();
    return new String(Hex.encodeHex(DigestUtils.sha384(ts + rand)));
  }

  public static void criteriaToSql(Criteria criteria) {
    CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
    SessionImplementor session = criteriaImpl.getSession();
    SessionFactoryImplementor factory = session.getFactory();
    CriteriaQueryTranslator translator = new CriteriaQueryTranslator(factory, criteriaImpl,
        criteriaImpl.getEntityOrClassName(), CriteriaQueryTranslator.ROOT_SQL_ALIAS);
    String[] implementors = factory.getImplementors(criteriaImpl.getEntityOrClassName());

    CriteriaJoinWalker walker = new CriteriaJoinWalker(
        (OuterJoinLoadable) factory.getEntityPersister(implementors[0]), translator, factory, criteriaImpl,
        criteriaImpl.getEntityOrClassName(), session.getLoadQueryInfluencers());

    String sql = walker.getSQLString();
    System.out.println("************** Sql Query Sistemi ********* \n ");
    System.out.println(sql);
    System.out.println("************** Sql Query Sistemi ********* \n\n\n");
  }

  public String readWebUrl(String url) {
    URL uri;
    InputStream is = null;
    BufferedReader br;
    String line;
    StringBuilder sb = new StringBuilder();
    try {
      uri = new URL(url);
      is = uri.openStream(); // throws an IOException
      br = new BufferedReader(new InputStreamReader(is));

      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
    } catch (MalformedURLException mue) {
      mue.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      try {
        if (is != null)
          is.close();
      } catch (IOException ioe) {
        // nothing to see here
      }
    }
    return sb.toString();
  }

  public boolean isValidEmailAddress(String email) {
    boolean result = true;
    if (email != null) {
      try {
        InternetAddress emailAddr = new InternetAddress(email);
        emailAddr.validate();
      } catch (AddressException ex) {
        result = false;
      }
      return result;
    } else {
      return false;
    }
  }

  public int getJavaVersion() {
    return Integer.parseInt(System.getProperty("java.version").split("\\.")[1]);
  }

  public String convertBase64OutputStream(ByteArrayOutputStream outputStream) {
    byte[] exporterDataByte = outputStream.toByteArray();
    if (getJavaVersion() <= 8) {
      return BaseEncoding.base64().encode(exporterDataByte);
    } else {
      // Java8 ile birlikte gelen bir tane özellik
      return Base64.getEncoder().encodeToString(exporterDataByte);
    }
  }

  public synchronized Date rumiToMiladi(Date rumi) {
    Calendar c = Calendar.getInstance();
    c.setTime(rumi);
    c.add(Calendar.DATE, 13);
    c.add(Calendar.YEAR, 584);
    Date dt = c.getTime();
    return dt;
  }

  public synchronized String getDate(String format, Date date) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return simpleDateFormat.format(date);

  }

  public Object containesControl(Object obj, String searchingTest) throws IllegalAccessException {
    Class<?> objClass = obj.getClass();
    FilterControl:
    for (Field field : objClass.getDeclaredFields()) {
      field.setAccessible(true); // You might want to set modifier to
      // public first.
      Object value = field.get(obj);
      if (value != null) {
        if (value.toString().trim().toLowerCase().contains(searchingTest.toLowerCase())) {
          // System.out.println(field.getName() + "=" + value);
          return obj;
        }

      }
    }
    return null;
  }

  public String uploadFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail,
                           String locationName) {

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy HH:mm:ss.SS");
    String strDate = sdf.format(cal.getTime());

    // fileMRPConfiguration fileMRPConfiguration =
    // GuiceBundle.getInjector().getInstance(fileMRPConfiguration.class);
    // String path =
    // fileMRPConfiguration.getFileServer().getPath()+"/"+strDate.trim().replace(":","_").replace("
    // ","_").replace("/","_").replace(".","_");;
    String path = "";
    if (OSDetector.isWindows()) {
      path = file_DIR;
    } else if (OSDetector.isUnix() || OSDetector.isMac() || OSDetector.isSolaris()) {
      path = file_DIR_LINUX;
    }

    if (locationName.contains("/")) {
      path = path + "_" + strDate.replace(" ", "").replace("_", "").replace(":", "") + "/" + locationName;

    } else {
      path = path + "/" + strDate.replace(" ", "").replace("_", "").replace(":", "") + "/" + locationName;
    }
    String fileLocation = path + "/" + fileDetail.getFileName();

    File file1 = new File(path);
    file1.setExecutable(true, false);
    file1.setReadable(true, false);
    file1.setWritable(true, false);
    file1.mkdirs();

    try {
      FileOutputStream out = new FileOutputStream(new File(fileLocation));
      int read = 0;
      byte[] bytes = new byte[1024];
      out = new FileOutputStream(new File(fileLocation));
      while ((read = uploadedInputStream.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String output = "File successfully uploaded to : " + fileLocation;
    LOGGER.info(output);
    return fileLocation;
  }

  public Response getFileResponse(String cyriptedText) {
    // String cyriptedText =
    // uriInfo.getQueryParameters().getFirst("cyriptedText");
    Response response = (Response) fileMap.get(cyriptedText);
    try {
      fileMap.remove(cyriptedText);
    } catch (Exception ex) {
      ex.printStackTrace();
      return Response.serverError().build();
    }
    return response;
  }

  public synchronized String calculateAge(Date dogumTarihi, Date hesaplanacakTarih) {
    Integer years = 0;
    Integer months = 0;
    Integer days = 0;
    // create calendar object for birth day
    Calendar birthDay = Calendar.getInstance();
    birthDay.setTimeInMillis(dogumTarihi.getTime());
    // create calendar object for current day

    long currentTime = 0;
    if (hesaplanacakTarih == null) {
      currentTime = System.currentTimeMillis();
    } else {
      currentTime = hesaplanacakTarih.getTime();
    }

    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(currentTime);
    // Get difference between years
    years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
    int currMonth = now.get(Calendar.MONTH) + 1;
    int birthMonth = birthDay.get(Calendar.MONTH) + 1;
    // Get difference between months
    months = currMonth - birthMonth;
    // if month difference is in negative then reduce years by one and
    // calculate the number of months.
    if (months < 0) {
      years--;
      months = 12 - birthMonth + currMonth;
      if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
        months--;
    } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
      years--;
      months = 11;
    }
    // Calculate the days
    if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
      days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
    else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
      int today = now.get(Calendar.DAY_OF_MONTH);
      now.add(Calendar.MONTH, -1);
      days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
    } else {
      days = 0;
      if (months == 12) {
        years++;
        months = 0;
      }
    }
    // Create new Age object
    return days.toString() + "g" + months.toString() + "a" + years.toString() + "y";
  }

  private synchronized boolean isValidTckn(Long tckn) {
    try {
      String tmp = tckn.toString();

      if (tmp.length() == 11) {
        int totalOdd = 0;

        int totalEven = 0;

        for (int i = 0; i < 9; i++) {
          int val = Integer.valueOf(tmp.substring(i, i + 1));

          if (i % 2 == 0) {
            totalOdd += val;
          } else {
            totalEven += val;
          }
        }

        int total = totalOdd + totalEven + Integer.valueOf(tmp.substring(9, 10));

        int lastDigit = total % 10;

        if (tmp.substring(10).equals(String.valueOf(lastDigit))) {
          int check = (totalOdd * 7 - totalEven) % 10;

          if (tmp.substring(9, 10).equals(String.valueOf(check))) {
            return true;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  public synchronized String formatTempField(String prefix, String suffix, String body) {
    String template = body;
    int seqDigitValue = suffix.length();
    template = template.substring(0, template.length() - seqDigitValue);
    String formattedString = prefix + template + suffix;
    return formattedString;
  }

  public synchronized String validateObject(Object object) {
    String invalidFields = "";
    for (Field field : object.getClass().getDeclaredFields()) {
      Column annotation = field.getAnnotation(Column.class);
      field.setAccessible(true);
      Object value = new Object();
      // TODO: Check jpa annotations
      if (annotation != null && !field.getName().equals("id") && !field.getName().equals("createDate")) {
        if (!annotation.nullable()) {
          try {
            value = field.get(object);
            if (value == null) {
              invalidFields += (field.getName() + "\n");
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
    return invalidFields;
  }
}
