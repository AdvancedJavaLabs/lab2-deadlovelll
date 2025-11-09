import serial.SerialImplementation;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws
            IOException
    {
        SerialImplementation serial = new SerialImplementation();
        serial.run();
    }
}
