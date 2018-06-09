import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDao {

    private static final String DEFAULT_INPUT_FILE_PATH_NAME = "INPUT.TXT";
    private static final String DEFAULT_OUTPUT_FILE_PATH_NAME = "OUTPUT.TXT";


    static String readOneLineFromFile(String filePathName) throws IOException, NullPointerException {
        Path path = Paths.get(filePathName);
        BufferedReader reader = null;
        String line;
        try {
            reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));

            line = reader.readLine();
            if (line == null) {
                throw new NullPointerException("no data");
            }
        } finally {
            if(reader!=null) {
                reader.close();
            }
        }
        return line;
    }

    static String readOneLineFromFile() throws IOException, NullPointerException {
        return readOneLineFromFile(DEFAULT_INPUT_FILE_PATH_NAME);
    }

    static void writeStringToFile(String dataToWrite, String filePathName) throws IOException {
        Files.write(Paths.get(filePathName), dataToWrite.getBytes());
    }

    static void writeStringToFile(String dataToWrite) throws IOException {
        writeStringToFile(dataToWrite, DEFAULT_OUTPUT_FILE_PATH_NAME);
    }
}
