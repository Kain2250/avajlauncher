package ro.academyplus.avaj.simulator;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[((coordinates.getLongitude() > 10) ? 1 : 0) + ((coordinates.getLatitude() > 20) ? 1 : 0) + ((coordinates.getHeight() > 5) ? 1 : 0)];
    }
}
