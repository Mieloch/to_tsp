package put.poznan.solver;

import put.poznan.model.Path;
import put.poznan.model.Swap;

public class LocalSearchSolver implements Solver {

    public Path run(Path path) {

        Path result = path.copy();
        while (true) {
            Swap bestSwap = null;
            for (int i = 0; i < result.size(); i++) {
                for (int j = i + 1; j < result.size(); j++) {

                    long gain = result.swapGain(result.getCity(i), j, result.getCity(j), i);
                    if (bestSwap == null) {
                        bestSwap = new Swap(i, j, gain);
                    } else if (bestSwap.gain < gain) {
                        bestSwap = new Swap(i, j, gain);
                    }


                }
            }
            result.swap(bestSwap.i, bestSwap.j);
            if (bestSwap.gain < 50) {
                break;
            }
        }
        return result;
    }
}
