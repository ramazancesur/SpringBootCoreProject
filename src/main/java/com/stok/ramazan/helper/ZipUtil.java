package com.stok.ramazan.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
  private static final String INPUT_ZIP_FILE = "C:\\MyFile.zip";
  private static final String OUTPUT_FOLDER = System.getProperty("java.io.tmpdir");// +
  List<String> fileList;
  // System.getProperty("file.separator");

  public static void unZipIt(File zipFile, String outputFolder) {
    unZipIt(zipFile.getPath(), OUTPUT_FOLDER + System.getProperty("file.separator") + outputFolder);
  }

  public static void unZipIt(String zipFile, String outputFolder) {

    byte[] buffer = new byte[1024];

    try {

      // create output directory is not exists
      File folder = new File(OUTPUT_FOLDER);
      if (!folder.exists()) {
        folder.mkdir();
      }

      // get the zip file content
      ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
      // get the zipped file list entry
      ZipEntry ze = zis.getNextEntry();

      while (ze != null) {

        String fileName = ze.getName();
        File newFile = new File(outputFolder + File.separator + fileName);

        System.out.println("file unzip : " + newFile.getAbsoluteFile());

        // create all non exists folders
        // else you will hit FileNotFoundException for compressed folder
        new File(newFile.getParent()).mkdirs();

        FileOutputStream fos = new FileOutputStream(newFile);

        int len;
        while ((len = zis.read(buffer)) > 0) {
          fos.write(buffer, 0, len);
        }

        fos.close();
        ze = zis.getNextEntry();
      }

      zis.closeEntry();
      zis.close();

      System.out.println("Done");

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static File createZipMultipleFiles(File file, String fileName) throws Exception {
    List<File> files = new ArrayList<File>();
    files.add(file);
    return createZipMultipleFiles(files, fileName);
  }

  public static File createZipMultipleFiles(List<File> files, String fileName) throws Exception {
    File dest = null;
    try {

      // create byte buffer
      byte[] buffer = new byte[1024];

      dest = new File(System.getProperty("java.io.tmpdir"), fileName);

      ZipEntry ze = new ZipEntry(dest.getAbsolutePath());
      if (!dest.createNewFile() && !dest.exists()) {
        throw new Exception();
        // throw new RobeRuntimeException("ERROR in creating file
        // ",dest.getParent());
      }

      FileOutputStream fos = new FileOutputStream(dest);

      ZipOutputStream zos = new ZipOutputStream(fos);

      for (int i = 0; i < files.size(); i++) {

        File srcFile = files.get(i);

        FileInputStream fis = new FileInputStream(srcFile);

        // begin writing a new ZIP entry, positions the stream to the
        // start of the entry data
        zos.putNextEntry(new ZipEntry(srcFile.getName()));

        int length;

        while ((length = fis.read(buffer)) > 0) {
          zos.write(buffer, 0, length);
        }

        // zos.flush();
        zos.closeEntry();

        // close the InputStream
        fis.close();

      }

      // zos.finish();
      // close the ZipOutputStream
      zos.close();
      return dest;

    } catch (IOException ioe) {
      System.err.println("Error creating zip file: " + ioe);
    }

    return null;
  }

}