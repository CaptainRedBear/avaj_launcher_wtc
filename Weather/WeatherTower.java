package Weather;

import Tower.Tower;
import Aircraft.Coordinates;

public class WeatherTower {
    
    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    }

    public void changeWeather() {
        this.conditionsChanged();
    }

}
