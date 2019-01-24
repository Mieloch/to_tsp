package put.poznan.model;

import java.util.*;

public class PathsFactory {


    public static Path random(List<City> cities) {
        ArrayList<City> list = new ArrayList<>(cities);
        Collections.shuffle(list);
        return new Path(list);
    }


    public static Path nearestNeighbour(List<City> cities) {
        LinkedList<City> restOfCities = new LinkedList<>(cities);
        List<City> citiesInPath = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Collections.shuffle(restOfCities);
            citiesInPath.add(restOfCities.pollFirst());
            while (citiesInPath.size() % cities.size() / 2 != 0) {
                City lastAdded = citiesInPath.get(citiesInPath.size() - 1);
                City nearest = null;
                long min = Long.MAX_VALUE;
                for (City other : restOfCities) {
                    long distance = lastAdded.distanceTo(other);
                    if (nearest == null) {
                        nearest = other;
                        min = distance;
                    } else if (distance < min) {
                        nearest = other;
                        min = distance;
                    }
                }
                restOfCities.remove(nearest);
                citiesInPath.add(nearest);
            }
        }
        return new Path(citiesInPath);
    }

    public static Path nearestNeighbourCycle(List<City> cities) {
        LinkedList<City> restOfCities = new LinkedList<>(cities);
        List<City> citiesInPath = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Collections.shuffle(restOfCities);
            City start = restOfCities.pollFirst();
            citiesInPath.add(start);
            while (citiesInPath.size() % cities.size() / 2 != 0) {
                City lastAdded = citiesInPath.get(citiesInPath.size() - 1);
                City nearest = null;
                long min = Long.MAX_VALUE;
                for (City other : restOfCities) {
                    long distance = lastAdded.distanceTo(other) + start.distanceTo(other);
                    if (nearest == null) {
                        nearest = other;
                        min = distance;
                    } else if (distance < min) {
                        nearest = other;
                        min = distance;
                    }
                }
                restOfCities.remove(nearest);
                citiesInPath.add(nearest);
            }
        }
        return new Path(citiesInPath);
    }
}
