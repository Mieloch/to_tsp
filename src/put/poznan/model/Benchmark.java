package put.poznan.model;

import put.poznan.solver.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Benchmark {

    private final List<City> cities;
    private final int nTimes;

    public Benchmark(List<City> cities, int nTimes) {
        this.cities = cities;
        this.nTimes = nTimes;
    }

    public BenchmarkResult run(Function<List<City>, Path> initFunction, Solver solver, String name) {
        ArrayList<Long> lengths = new ArrayList<>();
        ArrayList<Long> times = new ArrayList<>();
        Path best = null;
        //AD1
        for (int i = 0; i < nTimes; i++) {
            long start = System.currentTimeMillis();
            Path path = initFunction.apply(cities);
            path = solver.run(path);
            times.add(System.currentTimeMillis() - start);
            lengths.add(path.length());
            if (best == null) {
                best = path;
            } else if (best.length() > path.length()) {
                best = path;
            }
        }
        return new BenchmarkResult(lengths, times, name, best);
    }
}
