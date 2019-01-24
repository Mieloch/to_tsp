package put.poznan.model;

import java.util.Objects;

public class City {

    private final int x;
    private final int y;
    private final int id;

    public City(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public long distanceTo(City other) {
        return Math.round(Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)));
    }

    @Override
    public String toString() {
        return id + "," + x + "," + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return x == city.x &&
                y == city.y &&
                id == city.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, id);
    }
}
