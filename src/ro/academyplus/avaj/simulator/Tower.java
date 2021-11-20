package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.exceptions.WeatherException;

import java.util.ArrayList;
import java.util.List;
import static ro.academyplus.avaj.simulator.Simulator.simulatorLogger;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        simulatorLogger.log("Tower says: " + flyable.toString() + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        simulatorLogger.log("Tower says: " + flyable.toString() + " unregistered from weather tower.");
    }

    protected void conditionsChanged() throws WeatherException {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).updateConditions();
    }
}
