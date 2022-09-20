import exception.InvalidFileContent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class AsyncCountIncrementer {

    public static final int THREADS_NUMBER = 2;
    public static final String NUMBER_REGEX = "^-?\\d+$";

    private final String filePath;

    public AsyncCountIncrementer(String filePath) {
        this.filePath = filePath;
    }

    public void asyncIncrementValueToFile(int finishValue) throws IOException {
        String path = Paths.get(filePath).toString();
        validate(path);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        Semaphore semaphore = new Semaphore(1, true);

        for (int i = Main.START_VALUE; i < finishValue; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    int oldValue = readCurrentValueFromFile(path);
                    int newValue = ++oldValue;
                    setValueInFile(path, newValue);
                    System.out.println(oldValue + " " + newValue + " " + Thread.currentThread().getName());
                } catch (IOException | InterruptedException e) {
                    System.err.println(e.getMessage());
                    executorService.shutdownNow();
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
        }

        if (!executorService.isShutdown())
            executorService.shutdown();

    }

    private void validate(String path) {
        try {
            if (!Files.exists(Paths.get(filePath)))
                throw new InvalidFileContent("File " + filePath + " not exists");

            String contentFile = FileUtils.readFileToString(new File(path));
            if (!contentFile.matches(NUMBER_REGEX))
                throw new InvalidFileContent("Invalid number format");
        } catch (IOException e) {
            throw new InvalidFileContent(e);
        }
    }

    private void setValueInFile(String path, int value) throws IOException {
        File file = new File(path);
        FileUtils.overwriteContentFile(file, String.valueOf(value));
    }

    private int readCurrentValueFromFile(String path) throws IOException {
        File file = new File(path);
        String content = FileUtils.readFileToString(file);
        return Integer.parseInt(content);
    }

}
