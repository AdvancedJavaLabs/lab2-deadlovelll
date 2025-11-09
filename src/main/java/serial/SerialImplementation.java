package serial;

import java.io.File;
import java.util.Scanner;

public class SerialImplementation {
    public void run() {
        File dataFile = new File("src/main/java/data/data_100MB");
        try (Scanner reader = new Scanner(dataFile)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
        } catch (Exception ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }
}
