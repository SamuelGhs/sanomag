package ro.samuel.sanomag.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ro.samuel.sanomag.util.ConfigLoader.getProperty;

public class FileUtil {
    private static String SEPARATOR = File.separator;

    public static File getDestinationFile(String supplier) {
        String outputPath = getProperty("orders.output.path");
        File outputDirectory = new File(outputPath);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
        String fileName = supplier + ".json";
        return new File(outputDirectory + SEPARATOR + fileName);
    }

    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    public static File getFile(String path) throws IOException {
        return new File(path);
    }

    public static void writeJson(File file, String jsonString) {
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveFileToError(File ordersFile) {
        String errorDirectoryPath = getProperty("input.files.error.path");
        createDirectory(errorDirectoryPath);
        File fileDestination = new File(errorDirectoryPath + SEPARATOR + ordersFile.getName());
        try {
            copyFile(ordersFile, fileDestination);
            System.out.println("File " + ordersFile + " was moved to error folder");
            ordersFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File createDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    private static void copyFile(File source, File destination) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(source));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }

    public static void moveFileToArhive(File file) {
        String arhiveDirectoryPath = getProperty("input.files.archive.path");
        createDirectory(arhiveDirectoryPath);
        File fileDestination = new File(arhiveDirectoryPath + SEPARATOR + file.getName());
        try {
            copyFile(file, fileDestination);
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}