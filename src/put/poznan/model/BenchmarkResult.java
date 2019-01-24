package put.poznan.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class BenchmarkResult {
    private final ArrayList<Long> lengths;
    private final ArrayList<Long> times;
    private final String name;
    private final Path best;

    public BenchmarkResult(ArrayList<Long> lengths, ArrayList<Long> times, String name, Path best) {
        this.lengths = lengths;
        this.times = times;
        this.name = name;
        this.best = best;
    }

    public long avgTime() {
        return times.stream().reduce((aLong, aLong2) -> aLong + aLong2).get() / times.size();
    }

    public long totalTime() {
        return times.stream().reduce((aLong, aLong2) -> aLong + aLong2).get();
    }

    public long avgLen() {
        return lengths.stream().reduce((aLong, aLong2) -> aLong + aLong2).get() / lengths.size();
    }

    public void printResults() {
        System.out.println("\n####\nName :" + name);
        Long maxTime = Collections.max(times);
        Long minTime = Collections.min(times);
        System.out.println("avgTime: " + avgTime());
        System.out.println("maxTime: " + maxTime);
        System.out.println("minTime: " + minTime);
        Long maxLen = Collections.max(lengths);
        Long minLen = Collections.min(lengths);
        System.out.println("avgLen: " + avgLen());
        System.out.println("maxLen: " + maxLen);
        System.out.println("minLen: " + minLen);
        try {
            best.toCsv("data/" + name + ".csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}