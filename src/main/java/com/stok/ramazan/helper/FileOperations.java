package com.stok.ramazan.helper;

import com.stok.ramazan.settings.OSDetector;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileOperations {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileOperations.class);

    private static final String file_DIR = "/C:/FileServer/fileMRP";
    private static final String file_DIR_LINUX = "/var/FileServer/haliYikama/Files/tempFile";

    public static byte[] encodeFileToBase64Byte(String filePath) throws IOException {
        byte[] bytes = convertFileToByte(filePath);
        return encodeFileToBase64Byte(bytes);
    }

    public static String convertBytesToFile(byte[] bytes, String locationName, String fileName) throws IOException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy HH:mm:ss.SS");
        String strDate = sdf.format(cal.getTime());
        String path = "";
        if (OSDetector.isWindows()) {
            path += file_DIR;
        } else if (OSDetector.isUnix() || OSDetector.isSolaris()) {
            path += "/home/" + Helper.getInstance().getSystemUserNameList().get(0) + file_DIR_LINUX;
        } else if (OSDetector.isMac()) {
            path += "/Users/" + Helper.getInstance().getSystemUserNameList().get(0) + file_DIR_LINUX;
        }
        path = path + "/" + fileName;
        if (locationName.contains("/")) {
            path = path.replace(".", "_") + "/" + strDate.replace(" ", "").replace("_", "").replace(":", "")

                    + locationName;

        } else {
            path = path.replace(".", "_") + "/" + strDate.replace(" ", "").replace("_", "").replace(":", "") + "/" + locationName;
        }
        String fileLocation = path.replace(".", "_").replace(",", "").replace(" ", "");

        BufferedImage image = null;


        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        image = ImageIO.read(bis);
        bis.close();

// write the image to a file
        File outputfile = new File(fileLocation.replace(".", "_") + "/" + fileName + ".png");
        File parentDir = outputfile.getParentFile();

        parentDir.setReadable(true, false);
        parentDir.setWritable(true, false);
        parentDir.setExecutable(true, false);

        if (parentDir != null && !parentDir.exists()) {
            try {
                Files.createDirectories(Paths.get(parentDir.toURI()));
            } catch (Exception ex) {
                LOGGER.error("******************* " + ex.getMessage() + " ******************************");
                ex.printStackTrace();
                LOGGER.error("******************* " + ex.getMessage() + " ******************************");
                throw new IOException("error creating directories");
            }
        }
        ImageIO.write(image, "png", outputfile);


        return fileLocation.replace(".", "_") + "/" + fileName + ".png";
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

    public byte[] convertImagetoByteArray(String filePath) {

        BufferedImage image = null;
        File f = null;

        // read image file
        try {
            f = new File(filePath);
            image = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return null;
    }

}
