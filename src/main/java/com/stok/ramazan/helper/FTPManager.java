package com.stok.ramazan.helper;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FTPManager {
  private static FtpConfiguration ftpConfiguration;

  public static void initialize(FtpConfiguration ftpConfiguration) {
    FTPManager.ftpConfiguration = ftpConfiguration;
  }

  public static boolean save(File file, String path, String filename) {
    boolean isSend = false;
    try {
      isSend = sendToConfiguration(file, path, filename, ftpConfiguration);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return isSend;
  }

  public static FtpConfiguration getFtpConfiguration() {
    return ftpConfiguration;
  }

  public static boolean checkFtpFileExist(String path, String filename) {
    return checkFtpFileExist(path, filename, ftpConfiguration);
  }

  public static boolean checkFtpFileExist(String path, String filename, FtpConfiguration ftpConfiguration) {
    boolean isExist = false;
    FTPClient ftp = null;
    try {
      ftp = connectFtp(ftpConfiguration);
      if (ftp == null)
        return isExist;
      isExist = checkFtpFileExist(path, filename, ftp);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (ftp != null && ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
          // do nothing
        }
      }
    }
    return isExist;
  }

  public static File readFtpFile(String ftpFilePath, String ftpFileName) {
    String fileName = ftpFilePath.substring(ftpFilePath.lastIndexOf('/') + 1);
    String filePath = ftpFilePath.substring(0, ftpFilePath.lastIndexOf('/'));
    if (ftpFileName == null) {
      ftpFileName = fileName;
    }
    if (fileName.equals("")) {
      fileName = ftpFileName;
    }
    // TODO: 21/01/16 read with ftpmanager and store to tmp file
    File file = new File(System.getProperty("java.io.tmpdir"), ftpFileName);
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      // download ftp içindeki klasörü temp dosyası içine cekmekte
      // kullanılıyor
      if (!download(fileOutputStream, fileName, filePath)) { // eğer hata
        // olusursa
        // download
        // sırasında
        return null;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
    // ftp download da hata olusursa
    return file;
  }

  private static void createPathFolder(String path, FTPClient ftp) throws IOException {
    String[] folderName = path.split("/");
    if (path.indexOf("/") == 0)
      folderName[0] = "/" + folderName[0];
    for (int i = 0; i < folderName.length; i++) {
      ftp.makeDirectory(folderName[i]);
      ftp.changeWorkingDirectory(folderName[i]);
    }
  }

  private static FTPClient connectFtp() throws IOException {
    return connectFtp(ftpConfiguration);
  }

  private static FTPClient connectFtp(FtpConfiguration ftpConfiguration) throws IOException {
    FTPClient ftp = new FTPClient();
    FTPClientConfig config = new FTPClientConfig();
    ftp.configure(config);
    int reply;
    ftp.connect(ftpConfiguration.getUrl());
    ftp.login(ftpConfiguration.getUser(), ftpConfiguration.getPassword());

    System.out.println("Connected to " + ".");
    System.out.println(ftp.getReplyString());

    reply = ftp.getReplyCode();

    if (!FTPReply.isPositiveCompletion(reply)) {
      ftp.disconnect();
      System.out.println("FTP server refused connection.");
      return null;
    }
    return ftp;
  }

  public static boolean download(OutputStream os, String filename, String path) {
    FTPClient ftp = null;

    boolean error = false;

    try {
      ftp = connectFtp();
      if (ftp == null)
        return error;

      ftp.changeWorkingDirectory(path);
      // transfer files

      InputStream inputStream = ftp.retrieveFileStream(filename);
      byte[] bytesArray = new byte[4096];
      int bytesRead = -1;
      if (inputStream != null) {
        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
          os.write(bytesArray, 0, bytesRead);
        }
      } else {
        ftp.disconnect();
        System.out.println("FTP cannot read the File.");
        return false;
      }

      boolean success = ftp.completePendingCommand();
      if (success) {
        System.out.println(filename + " file has been downloaded successfully.");
      }
      ftp.logout();

      inputStream.close();
    } catch (IOException e) {
      error = true;
      e.printStackTrace();
    } finally {
      if (ftp != null && ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
          // do nothing
        }
      }
    }

    return !error;
  }

  public static File downloadInFolderZipAndDelete(String path, String zipName) {
    FTPClient ftp = null;

    File zipFile = null;

    try {
      ftp = connectFtp();
      if (ftp == null)
        return null;

      ftp.setFileType(FTP.BINARY_FILE_TYPE);

      List<File> fileList = new ArrayList<File>();
      FTPFile[] ftpFiles = ftp.listFiles(path);
      // transfer files
      if (ftpFiles.length > 0) {
        ftp.changeWorkingDirectory(path);

        for (FTPFile file : ftpFiles) {

          String currentFileName = file.getName();
          if (currentFileName.equals(".") || currentFileName.equals("..")) {
            continue;
          }

          File tempFile = new File(System.getProperty("java.io.tmpdir"), currentFileName);
          FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
          download(fileOutputStream, currentFileName, path);
          fileList.add(tempFile);
        }

        zipFile = ZipUtil.createZipMultipleFiles(fileList, zipName + ".zip");

        for (FTPFile file : ftpFiles) {

          String currentFileName = file.getName();
          if (currentFileName.equals(".") || currentFileName.equals("..")) {
            continue;
          }

          delete(path, currentFileName);
        }

      }

      if (ftp.isConnected())
        ftp.logout();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      if (ftp != null && ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
          // do nothing
        }
      }
    }

    return zipFile;
  }

  public static FTPFile[] getFtpFolderList(String path) throws IOException {
    FTPClient ftp = null;
    FTPFile[] ftpFiles;

    try {
      ftp = connectFtp();
      if (ftp == null)
        return null;

      ftpFiles = ftp.listFiles(path);
      ftp.logout();
    } catch (IOException e) {
      throw e;
    } finally {
      if (ftp != null && ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
          // do nothing
        }
      }
    }

    return ftpFiles;
  }

  public static boolean delete(String path, String filename) {
    return delete(path, filename, false);
  }

  public static boolean delete(String path, String filename, boolean isDirectory) {
    FTPClient ftp = null;
    boolean error = false;

    try {
      ftp = connectFtp();
      if (ftp == null)
        return error;
      ftp.setFileType(FTP.BINARY_FILE_TYPE);
      // transfer files
      ftp.changeWorkingDirectory(path);
      if (!isDirectory) {
        ftp.deleteFile(filename);
      } else {
        ftp.removeDirectory(filename);
      }

      ftp.logout();
    } catch (IOException e) {
      error = true;
      e.printStackTrace();
    } finally {
      if (ftp != null && ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
          // do nothing
        }
      }
    }

    return !error;
  }

  public static boolean sendToConfiguration(File file, String path, String filename,
                                            FtpConfiguration ftpConfiguration) throws IOException {
    FTPClient ftp = null;
    FileInputStream fis = null;

    boolean error = false;

    try {
      ftp = connectFtp(ftpConfiguration);
      if (ftp == null)
        return error;

      ftp.setFileType(FTP.BINARY_FILE_TYPE);

      // transfer files

      fis = new FileInputStream(file);
      //
      // Store file to server
      //
      if (!ftp.changeWorkingDirectory(path)) {
        createPathFolder(path, ftp);
      }
      ftp.storeFile(filename, fis);

      ftp.logout();
    } catch (IOException e) {
      error = true;
      throw e;
    } finally {
      if (ftp != null && ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
          // do nothing
        }
      }
    }

    return !error;
  }

  /**
   * Ftp bağlantısını test eder.
   */
  public static boolean testConnection(String ftpUrl, String ftpUserName, String ftpPassword) {
    boolean result = false;
    FTPClient ftp = new FTPClient();

    try {
      ftp.connect(ftpUrl);
      result = ftp.login(ftpUserName, ftpPassword);
      if (ftp.isConnected()) {
        // result = true;
        ftp.logout();
        ftp.disconnect();
      }
    } catch (IOException e) {
      result = false;
      e.printStackTrace();
    }

    return result;
  }

  public static boolean moveFileInFTP(String fromPath, String toPath, String fileName) {
    FTPClient ftp = null;
    boolean result = false;
    String fileNewName = fileName;
    String ftpPath = "";
    try {
      ftp = connectFtp();
      if (ftp == null)
        return result;

      ftpPath = ftp.printWorkingDirectory();

      if (!checkFtpFileExist(fromPath, fileName, ftp)) { // tasınacak
        // dosya warmı
        // onun kontrolu
        return result;
      }

      if (!ftp.changeWorkingDirectory(toPath)) { // atılacak dosya dizini
        // var mı
        createPathFolder(toPath, ftp); // atılacak dosya dizinini
        // olusturur
      }
      ftp.changeWorkingDirectory(ftpPath); // ana dizine donus dosya
      // tasımak icin ana dizinde
      // olmak gerekiyor

      boolean whileTrue = true;
      int i = 1;
      while (whileTrue) {
        if (!checkFtpFileExist(toPath, fileNewName, ftp)) { // atılacak
          // dosya
          // kontrolu
          // yapılıyor
          whileTrue = false;
        } else {
          fileNewName = fileName.replace(".zip", "(" + (i++) + ").zip");
        }
      }
      result = ftp.rename(fromPath + "/" + fileName, toPath + "/" + fileNewName);

    } catch (IOException e) {
      e.printStackTrace();
      result = false;
    } finally {
      if (ftp != null && ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
          // do nothing
        }
      }
    }

    return result;
  }

  // Ftp içinde dosya olup olmadığını kontrol eder

  public static boolean checkFtpFileExist(String path, String filename, FTPClient ftp) throws IOException {
    final String fileName = filename;

    boolean result = false;
    try {
      if (ftp == null)
        return result;

      FTPFileFilter ff = new FTPFileFilter() {
        @Override
        public boolean accept(FTPFile file) {
          if (fileName.equals(file.getName()))
            return true;
          return false;
        }
      };

      // ftp.changeWorkingDirectory(path);
      FTPFile[] ftpFiles = ftp.listFiles(path, ff);
      if (ftpFiles.length > 0) {
        result = true;
      }
    } catch (IOException e) {
      result = false;
      throw e;
    }

    return result;
  }

}
