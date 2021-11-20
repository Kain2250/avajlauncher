package ro.academyplus.avaj.simulator;
import static ro.academyplus.avaj.simulator.Simulator.simulatorLogger;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        weatherTower = null;
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        String[] messages = {
                this + ": It's good weather to jump with parachute.",
                this + ": It's raining. Better watch out for lightings.",
                this + ": Fucking fog.",
                this + ": OMG! Winter is coming!"
        };
        switch (weather) {
            case "SUN" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 10,
                        this.coordinates.getHeight() - 2);
                simulatorLogger.log(messages[0]);
                break;
            }
            case "RAIN" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 5,
                        this.coordinates.getHeight());
                simulatorLogger.log(messages[1]);
                break;
            }
            case "FOG" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 1,
                        this.coordinates.getHeight());
                simulatorLogger.log(messages[2]);
                break;
            }
            case "SNOW" : {
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 7);
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
        return "JetPlane#" + this.name + "(" + this.id + ")";
    }
}
