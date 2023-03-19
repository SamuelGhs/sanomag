package ro.samuel.sanomag.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void writeJson(File file, String jsonString) {
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}