package ro.academyplus.avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SimulatorLogger {
    private String FILENAME = "simulation.txt";
    private PrintWriter logger;

    public SimulatorLogger() throws FileNotFoundException {
        logger = new PrintWriter(new File(FILENAME));
    }

    public void log(String message) {
        logger.println(message);
    }

    public void close() {
        logger.close();
    }
}
