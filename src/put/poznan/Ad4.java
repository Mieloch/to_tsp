package put.poznan;

import put.poznan.model.*;
import put.poznan.solver.LocalSearchSolver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ad4 {

    public static void main(String[] args) throws FileNotFoundException {
        List<City> cities100 = InstanceLoader.fromFile("data/kroA100.tsp");
        List<Path> paths = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Path random = PathsFactory.random(cities100);
            Path run = new LocalSearchSolver().run(random);
            paths.add(run);
        }
        Path best = null;
        for (Path path : paths) {
            if (best == null) {
                best = path;
            } else if (path.length() < best.length()) {
                best = path;
            }
        }
        List<Long> lengths = paths.stream().map(Path::length).collect(Collectors.toList());

        List<Double> dists = new ArrayList<>();
        for (Path path : paths) {
            dists.add(dist(best, path));
        }
        for (int i = 0; i < lengths.size(); i++) {
            System.out.println(lengths.get(i) + "," + dists.get(i));
        }

    }

    private static double dist(Path p1, Path p2) {
        int sum = 0;
        Map<City, Integer> idxByNode = new HashMap();
        List<City> p2Cities = p2.getCities();
        for (int i = 0; i < p2Cities.size(); i++) {
            idxByNode.put(p2Cities.get(i), i);
        }

        List<City> p1Cities = p1.getCities();
        for (int p1Idx = 0; p1Idx < p1Cities.size(); p1Idx++) {
            City p1City = p1Cities.get(p1Idx);
            Integer p2Idx = idxByNode.get(p1City);
            //
            City nextP1City = p1Cities.get(nextPos(p1Idx, p1.size()));
            City nextP2City = p2Cities.get(nextPos(p2Idx, p2.size()));
            if (nextP1City == nextP2City) {
                sum += 1;
            }
        }
        return 1- (sum / (double) p1Cities.size());
    }

    private static int nextPos(int pos, int max) {
        int nextPos = pos + 1;
        if (nextPos > max - 1) {
            nextPos = 0;
        }
        return nextPos;
    }

    private static int prevPos(int pos) {
        int prevPos = pos - 1;
        if (prevPos < 0) {
            prevPos = 0;
        }
        return prevPos;
    }
}
