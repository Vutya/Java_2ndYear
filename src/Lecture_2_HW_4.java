import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecture_2_HW_4 {
//  private static String CURRENT_DIR = System.getProperty("user.dir");

    private static String TASK_FOLDER = "2_HW_4";

    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get(TASK_FOLDER));
        } catch (IOException e) {
            System.out.println("Directory is not created");
        }

        String text = "Если мы не будем играть в Бога, то кто?";
        System.out.println(text);

        writeFile(text, "text_utf8.txt", "UTF-8");
        writeFile(text, "text_win1251.txt", "cp1251");
        writeFile(text, "text_koi8r.txt", "KOI8-R");

        convertTextToBytes("text_utf8.txt", "text_utf8.bin");
        convertTextToBytes("text_win1251.txt", "text_win1251.bin");
        convertTextToBytes("text_koi8r.txt", "text_koi8r.bin");

        try {
            Path koi8rPath = Paths.get(TASK_FOLDER, "text_koi8r.txt");
            byte[] koi8rBytes = Files.readAllBytes(koi8rPath);
            for (int i = 0; i < koi8rBytes.length; i++)
                if (koi8rBytes[i] < 0)
                    koi8rBytes[i] += 128;
            Path koi7 = Paths.get(TASK_FOLDER, "text_koi7r.txt");
            Files.write(koi7, koi8rBytes);
        } catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private static void writeFile(String text, String filename, String charset) {
        try {
            Path file = Paths.get(TASK_FOLDER, filename);
            Files.write(file, Collections.singletonList(text), Charset.forName(charset));
        } catch (IOException e) {
            System.out.println("Error in writing file: " + e);
        }
    }

    private static void convertTextToBytes(String filenameIn, String filenameOut) {
        try(FileInputStream fileIn = new FileInputStream(TASK_FOLDER + "/" + filenameIn);
            PrintStream fileOut = new PrintStream(TASK_FOLDER + "/" + filenameOut)) {
            int b;
            List<String> t = new ArrayList<>();
            while ((b = fileIn.read()) != -1) {
                t.add(String.valueOf(b));
            }
            fileOut.print(String.join(" ", t));
        } catch(FileNotFoundException e) {
            System.out.println("File was not found." + e);
        } catch(IOException e) {
             System.out.println("Error converting text to bytes: " + e);
        }
    }
}