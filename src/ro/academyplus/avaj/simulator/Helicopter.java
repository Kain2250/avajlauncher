package ro.academyplus.avaj.simulator;
import static ro.academyplus.avaj.simulator.Simulator.simulatorLogger;

public class Helicopter extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        weatherTower = null;
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        String[] messages = {
                this + ": This is hot.",
                this + ": Damn you rain! You messed up my baloon.",
                this + ": Fucking fog.",
                this + ": My rotor is going to freeze!"
        };
        switch (weather) {
            case "SUN" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 10,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 2);
                simulatorLogger.log(messages[0]);
                break;
            }
            case "RAIN" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 5,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight());
                simulatorLogger.log(messages[1]);
                break;
            }
            case "FOG" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 1,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight());
                simulatorLogger.log(messages[2]);
                break;
            }
            case "SNOW" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 12);
                simulatorLogger.log(messages[3]);
                break;
            }
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
        return "Helicopter#" + this.name + "(" + this.id + ")";
    }
}
