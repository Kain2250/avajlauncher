package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.exceptions.WeatherException;

public interface Flyable {
    void updateConditions() throws WeatherException;
    void registerTower(WeatherTower weatherTower);
}
