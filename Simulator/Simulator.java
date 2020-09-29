package simulator;

import aircraft.Flyable;
import aircraft.AircraftFactory;
import Exceptions.UsageException;
import Exceptions.EmptyFileException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Simulator {
    
    private static WeatherTower weatherTower;
    private static List<Flyable> aircraftList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        
        try {

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String str = reader.readLine();

            if (args.length == 0 || args.length > 1) {
                throw new UsageException((char)27 + "[33mUsage: java Simulator.Simulator [filename]" + (char)27 + "[0m");
            }

            if (str == null) {
                throw new EmptyFileException((char)27 + "[031mError: Empty file." + (char)27 + "[0m");
            }

            if (str != null) {
                weatherTower = new WeatherTower();
                int sim = Integer.parseInt(str.split(" ")[0]);

                if (sim <= 0) {
                    System.out.println((char)27 + "[33mError: Simulation counter can't be 0 or less than 0." + (char)27 + "[0m");
                    System.exit(1);
                }

                while ((str = reader.readLine()) != null) {
                    String[] params = str.split(" ");
                    
                    if (params.length == 5) {
                        String[] arg = str.split(" ");
                        Flyable flyable = AircraftFactory.newAircraft(
                            arg[0], arg[1], 
                            Integer.parseInt(arg[2]), 
                            Integer.parseInt(arg[3]), 
                            Integer.parseInt(arg[4]));
                        aircraftList.add(flyable);
                    } else {
                        System.out.println((char)27 + "[33mError: Each line of the file, except the first one, should look like this: [TYPE NAME LONGITUDE LATITUDE HEIGHT]" + (char)27 + "[0m");
                        System.exit(1);
                    }
                }

                for (Flyable flyable : aircraftList) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= sim; i++) {
                    String simToWrite = "\nSimulation: " + i + "\n";
                    weatherTower.writeToFile("write", simToWrite);
                    weatherTower.changeWeather();
                }
                reader.close();
            }
        } catch (FileNotFoundException exception) {
            System.out.println((char)27 + "[31mError: File not found " + "<" + args[0] + ">" + (char)27 + "[0m");
        } catch (IOException exception) {
            System.out.println((char)27 + "[31mError: Error while reading file " + args[0] + (char)27 + "[0m");
        } catch (UsageException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        } catch (EmptyFileException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        } catch (Exception exception) {
            System.out.println((char)27 + "[31mError: Unknown symbols in file " + "<" + exception + ">" + (char)27 + "[0m");
        }

    }
    
}
