package com.stok.ramazan.helper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileOperations {
  public static byte[] encodeFileToBase64Byte(String filePath) throws IOException {

    byte[] bytes = convertFileToByte(filePath);
    return encodeFileToBase64Byte(bytes);
  }

  public static byte[] encodeFileToBase64Byte(byte[] bytes) {
    return Base64.encodeBase64(bytes);
  }

  public static String getMD5ofFileByPath(String filePath) throws NoSuchAlgorithmException, IOException {
    File file = new File(filePath);
    return getMD5ofFile(file);
  }

  public static String getMD5ofFile(File file) throws NoSuchAlgorithmException, IOException {
    return getMD5ofByte(convertFileToByte(file));
  }

  public static String getMD5ofByte(byte[] bytes) throws NoSuchAlgorithmException, IOException {
    String md5 = DigestUtils.md5Hex(bytes);
    return md5;
  }

  public static byte[] convertFileToByte(String filePath) throws IOException {
    File file = new File(filePath);
    return convertFileToByte(file);
  }

  public static byte[] convertFileToByte(File file) throws IOException {
    InputStream in = new FileInputStream(file);
    byte[] bFile = IOUtils.toByteArray(in);
    in.close();
    return bFile;
  }

  public static byte[] zipFile(String filename, String content) {
    byte[] buffer = new byte[1024];
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

    try {
      ZipOutputStream zos = new ZipOutputStream(byteArray);
      ZipEntry ze = new ZipEntry(filename + ".xml");
      zos.putNextEntry(ze);

      ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes("UTF-8"));

      int len;
      while ((len = in.read(buffer)) > 0) {
        zos.write(buffer, 0, len);
      }

      in.close();
      zos.closeEntry();
      zos.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return byteArray.toByteArray();

  }

  public static File convertBytesToFile(byte[] bytes, String fileName) throws IOException {
    FileInputStream fileInputStream = null;

    File file = null;
    file = new File(System.getProperty("java.io.tmpdir"), fileName);

    // convert array of bytes into file
    FileOutputStream fileOuputStream = new FileOutputStream(file);
    fileOuputStream.write(bytes);
    fileOuputStream.close();

    return file;
  }
}
