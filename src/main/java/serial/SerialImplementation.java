package serial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerialImplementation {

    public void run() throws
            IOException
    {
        String string = Files.readString(Path.of("src/main/java/data/data_100MB"));
        System.out.println(string);
    }
}
