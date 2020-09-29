package simulator;

import aircraft.Flyable;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Tower {
    
    private ArrayList<Flyable> observers = new ArrayList<>();
    private ArrayList<Flyable> dereg = new ArrayList<>();

    private FileWriter writer;
    private File file;

    public void register(Flyable flyable) {
        if (observers.contains(flyable))
            return;
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        if (dereg.contains(flyable))
            return;
        dereg.add(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers)
            flyable.updateConditions();
        observers.removeAll(dereg);
    }

    public void writeToFile(String state, String text) {
        try {
            this.file = new File("simulation.txt");
            this.writer = new FileWriter(file, true);
            this.file.createNewFile();

            switch (state) {
                case "write":
                    try {
                        writer.write(text);
                        writer.flush();
                    } catch (Exception e) {
                        System.out.println((char)27 + "[31mError: Couldn't write to file." + (char)27 + "[0m");
                        }
                    break ;
            }
        } catch (Exception e) {
        }
    }
    
}
