import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A static utility class for handling file IO.
 */
public final class FileIo {
    private FileIo() {
    }

    public static void writeDatabaseFile(File file, String title, List<String> infoList){
        try {
            FileWriter fw = new FileWriter(file);

            fw.write(title);
            fw.write(System.lineSeparator());

            for(String info : infoList){
                fw.write(info);
                fw.write(System.lineSeparator());
            }

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> readDatabaseFile(File file) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<String>> parsedLines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            List<String> parsedLine = Arrays.asList(line.split(","));
            parsedLines.add(parsedLine);
        }
        scanner.close();

        return parsedLines;
    }
}
