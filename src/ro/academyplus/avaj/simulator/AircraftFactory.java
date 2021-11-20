package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.exceptions.AircraftFactoryException;

public class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws AircraftFactoryException {
        switch (type) {
            case "Helicopter":
            {
                return new Helicopter(name, new Coordinates(longitude, latitude, height));
            }
            case "Baloon":
            {
                return new Baloon(name, new Coordinates(longitude, latitude, height));
            }
            case "JetPlane":
            {
                return new JetPlane(name, new Coordinates(longitude, latitude, height));
            }
            default: throw new AircraftFactoryException("Ошибка в названии Aircraft!");
        }
    }
}
