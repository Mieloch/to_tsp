package put.poznan.solver;

import put.poznan.model.City;
import put.poznan.model.Path;
import put.poznan.model.PathsFactory;

import java.util.List;

public class MultiLocalSearchSolver implements Solver {

    private final int nTimes;
    private final LocalSearchSolver localSearchSolver;

    public MultiLocalSearchSolver(int nTimes) {
        this.nTimes = nTimes;
        this.localSearchSolver = new LocalSearchSolver();
    }

    @Override
    public Path run(Path path) {
        Path best = null;
        List<City> cities = path.getCities();
        for (int i = 0; i < nTimes; i++) {
            Path run = localSearchSolver.run(PathsFactory.random(cities));
            if (best == null) {
                best = run;
            } else if (best.length() > run.length()) {
                best = run;
            }
        }
        return best;
    }
}
