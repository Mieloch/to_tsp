package put.poznan.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<City> cities;
    private final int singlePathSize;

    public Path(List<City> cities) {
        this.cities = cities;
        this.singlePathSize = cities.size() / 2;
    }

    public Path copy() {
        return new Path(new ArrayList<>(cities));
    }

    public long length() {
        return distance(0, singlePathSize) + distance(singlePathSize, cities.size() - 1);
    }

    /**
     * @param start inclusive
     * @param end   exclusive
     * @return
     */
    private long distance(int start, int end) {
        long sum = 0;
        for (int i = start; i < end - 1; i++) {
            sum += cities.get(i).distanceTo(cities.get(i + 1));
        }
        sum += cities.get(end).distanceTo(cities.get(start));
        return sum;
    }


    public void swap(int position1, int position2) {
        City city = cities.get(position1);
        cities.set(position1, cities.get(position2));
        cities.set(position2, city);
    }

    public long swapGain(City city1, int nPosition1, City city2, int nPosition2) {
        ArrayList<City> cities = new ArrayList<>(this.cities);
        cities.set(nPosition1, city1);
        cities.set(nPosition2, city2);
        long afterSwap = segmentDist(cities, nPosition1) + segmentDist(cities, nPosition2);
        long beforeSwap = segmentDist(this.cities, nPosition1) + segmentDist(this.cities, nPosition2);
        return beforeSwap - afterSwap;
    }

    public void toCsv(String path) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(path));
        printWriter.write("id,x,y\n");
        for (City city : cities) {
            printWriter.write(city.toString());
            printWriter.write("\n");
        }
        printWriter.close();
    }

    private long segmentDist(List<City> cities, int nPosition) {
        int prevPos = nPosition - 1;
        int nextPos = nPosition + 1;
        if (prevPos < 0) {
            prevPos = cities.size() - 1;
        }
        if (nextPos > cities.size() - 1) {
            nextPos = 0;
        }
        City prevCity = cities.get(prevPos);
        City nextCity = cities.get(nextPos);
        return prevCity.distanceTo(cities.get(nPosition)) + cities.get(nPosition).distanceTo(nextCity);
    }


    public int size() {
        return this.cities.size();
    }

    public City getCity(int i) {
        return this.cities.get(i);
    }

    public List<City> subpath(int a, int b) {
        return new ArrayList<>(this.cities.subList(a, b));
    }

    public List<City> getCities() {
        return new ArrayList<>(cities);
    }
}
