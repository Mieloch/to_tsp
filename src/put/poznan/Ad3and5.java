package put.poznan;

import put.poznan.model.*;
import put.poznan.solver.GeneticSolver;
import put.poznan.solver.IterativeLocalSearchSolver;
import put.poznan.solver.MultiLocalSearchSolver;

import java.io.FileNotFoundException;
import java.util.List;

public class Ad3and5 {

    public static void main(String[] args) throws FileNotFoundException {
        List<City> cities100 = InstanceLoader.fromFile("data/kroA100.tsp");
        Benchmark benchmark = new Benchmark(cities100, 10);
        BenchmarkResult mlls_nn_100 = benchmark.run(PathsFactory::random, new MultiLocalSearchSolver(100), "mlls_random_100");
        mlls_nn_100.printResults();
//        benchmark.run(PathsFactory::random, new IterativeLocalSearchSolver(mlls_nn_100.avgTime()), "ils_random_100").printResults();
        benchmark.run(PathsFactory::random, new GeneticSolver(Math.round(cities100.size() * 0.2), mlls_nn_100.avgTime()), "gen_random_100").printResults();

        List<City> cities150 = InstanceLoader.fromFile("data/kroA150.tsp");
        benchmark = new Benchmark(cities150, 10);
        BenchmarkResult mlls_nn_150 = benchmark.run(PathsFactory::random, new MultiLocalSearchSolver(100), "mlls_random_150");
        mlls_nn_150.printResults();
//        benchmark.run(PathsFactory::random, new IterativeLocalSearchSolver(mlls_nn_150.avgTime()), "ils_random_150").printResults();
        benchmark.run(PathsFactory::random, new GeneticSolver(Math.round(cities150.size() * 0.2), mlls_nn_150.avgTime()), "gen_random_150").printResults();

    }


}
