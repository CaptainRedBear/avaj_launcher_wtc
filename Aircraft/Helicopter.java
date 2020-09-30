package aircraft;

import simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);
        String toFile = "";
        String toFileUnreg = "";
        String tmp = "Helicopter#" + this.name + "(" + this.id + "): ";

        switch (weatherType) {
            case "RAIN":
                coordinates = new Coordinates(
                    coordinates.getLongitude() + 5, 
                    coordinates.getLatitude(), 
                    coordinates.getHeight());
                toFile = tmp + "RAIN IS THE BEST WEATHER\n";
                break;
            case "SUN":
                coordinates = new Coordinates(
                    coordinates.getLongitude() + 10, 
                    coordinates.getLatitude(), 
                    coordinates.getHeight() + 2);
                toFile = tmp + "It's crazy sunny!\n";
                break;
            case "FOG":
                coordinates = new Coordinates(
                    coordinates.getLongitude(), 
                    coordinates.getLatitude(), 
                    coordinates.getHeight() - 3);
                toFile = tmp + "Beware the fog monster!\n";
                break;
            case "SNOW":
                coordinates = new Coordinates(
                    coordinates.getLongitude(), 
                    coordinates.getLatitude(), 
                    coordinates.getHeight() - 12);
                toFile = tmp + "Hopefully the engine doesn't stall in this snow!\n";
                break;
        }

        weatherTower.writeToFile("write", toFile);

        if (this.coordinates.getHeight() <= 0) {
            toFileUnreg = "Helicopter#" + this.name + "(" + this.id + ") is landing. Coordinates are: Latitude (" + coordinates.getLatitude() + "), Longitude (" + coordinates.getLongitude() + ")\n";
            toFileUnreg += "Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered from weather tower.\n";
            weatherTower.writeToFile("write", toFileUnreg);
            weatherTower.unregister(this);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        String write = "Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.\n";
        this.weatherTower = weatherTower;
        weatherTower.writeToFile("write", write);
    }

}
