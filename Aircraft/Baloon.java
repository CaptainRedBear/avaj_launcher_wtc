package aircraft;

import simulator.WeatherTower;


public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);
        String toFile = "";
        String toFileUnreg = "";
        String tmp = "Baloon#" + this.name + "(" + this.id + "): ";

        switch (weatherType) {
            case "RAIN":
                coordinates = new Coordinates(
                    coordinates.getLongitude(), 
                    coordinates.getLatitude(), 
                    coordinates.getHeight() - 5);
                toFile = tmp + "The rain put out the baloon fire!\n";
                break;
            case "SUN":
                coordinates = new Coordinates(
                    coordinates.getLongitude() + 2, 
                    coordinates.getLatitude(), 
                    coordinates.getHeight() + 4);
                toFile = tmp + "Baloons and sun require wine and cheese!\n";
                break;
            case "FOG":
                coordinates = new Coordinates(
                    coordinates.getLongitude(), 
                    coordinates.getLatitude(), 
                    coordinates.getHeight() - 3);
                toFile = tmp + "Fog doesn't bother us since we can't steer in the first place!\n";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                    coordinates.getLongitude(), 
                    coordinates.getLatitude(), 
                    coordinates.getHeight() - 15);
                toFile = tmp + "It's snowing! Get the hot chocolate!\n";
                break;
        }

        weatherTower.writeToFile("write", toFile);

        if (this.coordinates.getHeight() <= 0) {
            toFileUnreg = "Baloon#" + this.name + "(" + this.id + ") is landing. Coordinates are: Latitude (" + coordinates.getLatitude() + "), Longitude (" + coordinates.getLongitude() + ")\n";
            toFileUnreg += "Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered from weather tower.\n";
            weatherTower.writeToFile("write", toFileUnreg);
            weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String write = "Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.\n";
        weatherTower.writeToFile("write", write);
    }

}
