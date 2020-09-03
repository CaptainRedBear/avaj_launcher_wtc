package Aircraft;

public class Coordinates {
    
    private int latitude;
    private int longitude;
    private int height;

    Coordinates(int latitude, int longitude, int height){
        if (height > 100)
            this.height = 100;
        else if (height < 0)
            this.height = 0;
        else
            this.height = height;

        if (latitude < 0)
            this.latitude = 0;
        if (longitude < 0)
            this.longitude = 0;

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLatitude(){
        return this.latitude;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getHeight() {
        return this.height;
    }

}
