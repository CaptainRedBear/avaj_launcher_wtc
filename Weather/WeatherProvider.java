package Weather;

import java.util.Random;

import Aircraft.Coordinates;
import java.util.Random;

public class WeatherProvider {
    
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"SUN", "RAIN", "SNOW", "FOG"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int rand = new Random().nextInt(3);//double check if 3 or 4
        return weather[rand];
    }

}
