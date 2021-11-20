package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.exceptions.AircraftFactoryException;
import ro.academyplus.avaj.simulator.exceptions.ParsingException;
import ro.academyplus.avaj.simulator.exceptions.WeatherException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulator {
    public static SimulatorLogger simulatorLogger;

    public static void main(String[] args) throws AircraftFactoryException, WeatherException, ParsingException, FileNotFoundException {
        simulatorLogger = new SimulatorLogger();
        doSimulation(args);
        simulatorLogger.close();
    }

    public static void doSimulation(String[] args) throws AircraftFactoryException, WeatherException, ParsingException {
        if (args.length != 1)
            System.out.println("Неверное количество аргументов. Пример: java -cp target ro.academyplus.avaj.simulator.Simulator scenario.txt");
        else
        {
            try {
                Scanner scanner = new Scanner(new File("scenario.txt"));
                int iterations = Integer.parseInt(scanner.nextLine());
                AircraftFactory aircraftFactory = new AircraftFactory();
                WeatherTower weatherTower = new WeatherTower();
                while (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    String[] data = line.split(" ");
                    if (data.length != 5)
                        throw new ParsingException("Ошибка парсинга файла");
                    aircraftFactory.newAircraft(
                            data[0],
                            data[1],
                            Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]),
                            Integer.parseInt(data[4])).registerTower(weatherTower);
                }
                for (int i = 0; i < iterations; i++)
                    weatherTower.changeWeather();
            }
            catch (FileNotFoundException e) {
                System.err.println("Файл не найден или недоступен для чтения");
                System.exit(-1);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Ошибка парсинга файла");
                System.exit(-1);
            }
        }
    }
}
