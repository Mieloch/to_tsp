package put.poznan.solver;

import put.poznan.model.Path;

public class DummySolver implements Solver {
    @Override
    public Path run(Path path) {
        return path;
    }
}
