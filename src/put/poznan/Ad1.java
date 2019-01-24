package put.poznan;

import put.poznan.model.Benchmark;
import put.poznan.model.City;
import put.poznan.model.InstanceLoader;
import put.poznan.model.PathsFactory;
import put.poznan.solver.DummySolver;

import java.io.FileNotFoundException;
import java.util.List;

public class Ad1 {

    public static void main(String[] args) throws FileNotFoundException {
        List<City> cities100 = InstanceLoader.fromFile("data/kroA100.tsp");
        Benchmark benchmark = new Benchmark(cities100, 10);
        benchmark.run(PathsFactory::nearestNeighbour, new DummySolver(), "nn_100").printResults();
        benchmark.run(PathsFactory::nearestNeighbourCycle, new DummySolver(), "nnc_100").printResults();
        benchmark.run(PathsFactory::random, new DummySolver(), "random_100").printResults();

        List<City> cities150 = InstanceLoader.fromFile("data/kroA150.tsp");
        benchmark = new Benchmark(cities150, 10);
        benchmark.run(PathsFactory::nearestNeighbour, new DummySolver(), "nn_150").printResults();
        benchmark.run(PathsFactory::nearestNeighbourCycle, new DummySolver(), "nnc_150").printResults();
        benchmark.run(PathsFactory::random, new DummySolver(), "random_150").printResults();
    }


}
