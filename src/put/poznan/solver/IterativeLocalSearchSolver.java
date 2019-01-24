package put.poznan.solver;

import put.poznan.model.City;
import put.poznan.model.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IterativeLocalSearchSolver implements Solver {

    private final long timeMillis;

    public IterativeLocalSearchSolver(long timeMillis) {
        this.timeMillis = timeMillis;
    }


    @Override
    public Path run(Path path) {
        Path best = path.copy();
        long timeStart = System.currentTimeMillis();
        do {
            Path result = best.copy();
            List<List<City>> subpaths = new ArrayList<>();
            int start = 0;
            for (int i = 0; i < 4; i++) {
                int end = ThreadLocalRandom.current().nextInt(start, path.size() / 4 * (i + 1));
                if (i == 3) {
                    end = path.size();
                }
                subpaths.add(result.subpath(start, end));
                start = end;

            }
            Collections.shuffle(subpaths);
            ArrayList<City> merge = new ArrayList<>();
            for (List<City> subpath : subpaths) {
                merge.addAll(subpath);
            }
            Path newPath = new LocalSearchSolver().run(new Path(merge));
            if (newPath.length() < best.length()) {
                best = newPath;
            }
        } while (System.currentTimeMillis() - timeStart < timeMillis);
        return best;
    }


}
