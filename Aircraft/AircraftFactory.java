package aircraft;


public abstract class AircraftFactory {
    
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coords = new Coordinates(longitude, latitude, height);

        switch (type.toLowerCase()) {
            case "helicopter":
                return new Helicopter(name, coords);
            case "baloon":
                return new Baloon(name, coords);
            case "jetplane":
                return new JetPlane(name, coords);
            default:
                System.out.println("Error: Invalid Aircraft Type.");
                System.exit(1);
                return null;
        }
    }

}
