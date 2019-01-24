package put.poznan.model;

public class Swap {
    public int i;
    public int j;
    public long gain;

    public Swap(int i, int j, long gain) {
        this.i = i;
        this.j = j;
        this.gain = gain;
    }

    @Override
    public String toString() {
        return "Swap{" +
                "i=" + i +
                ", j=" + j +
                ", gain=" + gain +
                '}';
    }
}