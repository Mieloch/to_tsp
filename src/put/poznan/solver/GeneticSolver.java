package put.poznan.solver;

import put.poznan.model.City;
import put.poznan.model.Path;
import put.poznan.model.PathsFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticSolver implements Solver {

    private final long populationSize;
    private final long timeMillis;
    private List<Path> population;

    public GeneticSolver(long populationSize, long timeMillis) {
        this.populationSize = populationSize;
        this.timeMillis = timeMillis;
    }


    @Override
    public Path run(Path path) {
        this.population = new ArrayList<>();
        List<City> cities = path.getCities();
        for (int i = 0; i < populationSize; i++) {
            population.add(PathsFactory.random(cities));
        }
        Path worst = worstInPopulation();
        long startTime = System.currentTimeMillis();

        do {
            int p1 = ThreadLocalRandom.current().nextInt(0, population.size());
            int p2 = ThreadLocalRandom.current().nextInt(0, population.size());
            Path child = crossover(population.get(p1), population.get(p2));
            if (child.length() < worst.length()) {
                population.remove(worst);
                population.add(child);
                worst = worstInPopulation();
            }
        } while (System.currentTimeMillis() - startTime < timeMillis);

        return bestInPopulation();
    }

    private Path bestInPopulation() {
        Path best = null;
        for (Path path : population) {
            if (best == null) {
                best = path;
            } else if (path.length() < best.length()) {
                best = path;
            }
        }
        return best;
    }

    private Path worstInPopulation() {
        Path worst = null;
        for (Path path : population) {
            if (worst == null) {
                worst = path;
            } else if (path.length() > worst.length()) {
                worst = path;
            }
        }
        return worst;
    }

    private Path crossover(Path parent1, Path parent2) {
        int geneA = ThreadLocalRandom.current().nextInt(0, parent1.size());
        int geneB = ThreadLocalRandom.current().nextInt(0, parent1.size());
        int startGene = Math.min(geneA, geneB);
        int endGene = Math.max(geneA, geneB);
        HashSet<City> blackList = new HashSet<>(parent1.subpath(startGene, endGene));
        ArrayList<City> child = new ArrayList<>(parent1.subpath(startGene, endGene));
        for (City city : parent2.getCities()) {
            if (!blackList.contains(city)) {
                child.add(city);
            }
        }
        LocalSearchSolver localSearchSolver = new LocalSearchSolver();
        Path path = new Path(child);
        return localSearchSolver.run(path);
    }
}
