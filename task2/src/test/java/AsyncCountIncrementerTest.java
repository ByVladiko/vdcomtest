import exception.InvalidFileContent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class AsyncCountIncrementerTest {

    @Before
    public void beforeTest() throws IOException {
        cleanFiles();
    }

    @After
    public void afterTest() throws IOException {
        cleanFiles();
    }

    @Test
    public void incrementValue() throws IOException, InterruptedException {
        int expectedValue = 100;

        AsyncCountIncrementer asyncCountIncrementer = new AsyncCountIncrementer(getPath().toString());
        FileUtils.createFile(getPath().toString(), String.valueOf(Main.START_VALUE));
        asyncCountIncrementer.asyncIncrementValueToFile(expectedValue);

        TimeUnit.SECONDS.sleep(1);

        String content = FileUtils.readFileToString(getPath().toFile());
        Assert.assertEquals(expectedValue, Integer.parseInt(content));
    }

    @Test(expected = InvalidFileContent.class)
    public void fileNotExists() throws IOException {
        AsyncCountIncrementer asyncCountIncrementer = new AsyncCountIncrementer(getPath().toString());
        asyncCountIncrementer.asyncIncrementValueToFile(10);
    }

    @Test(expected = InvalidFileContent.class)
    public void invalidContentFile() throws IOException {
        AsyncCountIncrementer asyncCountIncrementer = new AsyncCountIncrementer(getPath().toString());
        FileUtils.createFile(getPath().toString(), "random value");
        asyncCountIncrementer.asyncIncrementValueToFile(10);
    }

    private Path getPath() {
        return Paths.get(Main.DEFAULT_FILENAME);
    }

    private void cleanFiles() throws IOException {
        Path path = getPath();
        if (Files.exists(path))
            Files.delete(path);
    }
}