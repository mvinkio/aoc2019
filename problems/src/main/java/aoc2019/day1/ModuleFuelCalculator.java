package aoc2019.day1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class ModuleFuelCalculator {
    public static void main(String[] args) {
        ModuleFuelCalculator moduleFuelCalculator = new ModuleFuelCalculator();

        System.out.println("day1");
        int[] masses = moduleFuelCalculator.readMassesIntoArray("/home/mike/projects/aoc2019/problems/src/main/resources/day1-module-masses.txt");
        double[] fuels = moduleFuelCalculator.calculateFuels(masses);

        System.out.println(
                Arrays.stream(fuels)
                .sum()
                );

    }

    private double[] calculateFuels(int[] input) {
        double[] fuels = Arrays.stream(input)
            .mapToDouble(ele -> fuel(ele))
            .toArray();
        return fuels;
    }

    private double fuel(int ele) {
        double initialFuel = calculateFuel(ele);
        return fuelOfFuel(initialFuel);
    }

    private double fuelOfFuel(double ele) {
        double fuel = calculateFuel(ele);
        if (fuel <= 0) {
            return ele;
        } else {
            return ele + fuelOfFuel(fuel);
        }
    }

    private double calculateFuel(double ele) {
        return Math.floor(ele / 3.0 ) - 2;
    }

    private int[] readMassesIntoArray(String path) {
        int[] masses = null;

        try (Stream<String> stream = Files.lines(
                    Paths.get(path),
                    StandardCharsets.UTF_8)) {

            masses = stream
                .mapToInt(x -> Integer.parseInt(x))
                .toArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return masses;
    }


}
