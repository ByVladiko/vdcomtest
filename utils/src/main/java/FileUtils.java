import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {

    public static File createFile(String path, String initText) throws IOException {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath))
            Files.createFile(filePath);

        Path newFilePath = Files.write(filePath, initText.getBytes());
        return newFilePath.toFile();
    }

    public static String readFileToString(File file) throws IOException {
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }

    public static File overwriteContentFile(File file, String content) throws IOException {
        return Files.writeString(file.toPath(), content, StandardOpenOption.WRITE).toFile();
    }
}
