package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.exceptions.WeatherException;
import static ro.academyplus.avaj.simulator.Simulator.simulatorLogger;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        weatherTower = null;
    }

    @Override
    public void updateConditions() throws WeatherException {
        String weather = this.weatherTower.getWeather(this.coordinates);
        String[] messages = {
                this + ": Let's enjoy the good weather and take some pics.",
                this + ": Damn you rain! You messed up my baloon.",
                this + ": Fucking fog.",
                this + ": It's snowing. We're gonna crash."
        };
        switch (weather) {
            case "SUN" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 2,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 4);
                simulatorLogger.log(messages[0]);
                break;
            }
            case "RAIN" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 5);
                simulatorLogger.log(messages[1]);
                break;
            }
            case "FOG" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 3);
                simulatorLogger.log(messages[2]);
                break;
            }
            case "SNOW" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 15);
                simulatorLogger.log(messages[3]);
                break;
            }
            default: throw new WeatherException("Ошибка в названии погоды!");
        }
        if (this.coordinates.getHeight() <= 0) {
            simulatorLogger.log(this + " landing.");
            this.weatherTower.unregister(this);
            this.weatherTower = null;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

    @Override
    public String toString() {
        return "Baloon#" + this.name + "(" + this.id + ")";
    }
}
