package simulator;

import java.util.Random;

import aircraft.Coordinates;


public class WeatherProvider {
    
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"SUN", "RAIN", "SNOW", "FOG"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int rand = new Random().nextInt(4);//double check if 3 or 4
        rand++;
        rand = coordinates.getHeight()*rand;
        rand = rand%4;
        return weather[rand];
    }

}
