package put.poznan.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InstanceLoader {

    public static List<City> fromFile(String path) throws FileNotFoundException {
        ArrayList<City> result = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));
        for (int i = 0; i < 6; i++) {
            scanner.nextLine();
        }
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.equals("EOF")) {
                break;
            }
            String[] split = line.split(" ");
            result.add(new City(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[0])-1));
        }
        return result;
    }
}
