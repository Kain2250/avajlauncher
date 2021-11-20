package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.exceptions.WeatherException;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() throws WeatherException {
        super.conditionsChanged();
    }
}
