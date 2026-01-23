import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cereal> cereals = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Cereal.csv"))) {
            if (scanner.hasNextLine()) scanner.nextLine(); 

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length < 15) continue;

                cereals.add(new Cereal(
                    data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]), Integer.parseInt(data[5]), Double.parseDouble(data[6]),
                    Double.parseDouble(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]),
                    Integer.parseInt(data[10]), Integer.parseInt(data[11]), Double.parseDouble(data[12]),
                    Double.parseDouble(data[13]), Double.parseDouble(data[14])
                ));
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (cereals.isEmpty()) return;

        // Calculations
        double totalDensity = 0;
        for (Cereal c : cereals) totalDensity += c.getNutritionDensity();
        double mean = totalDensity / cereals.size();

        double sumSquares = 0;
        for (Cereal c : cereals) {
            sumSquares += Math.pow(c.getNutritionDensity() - mean, 2);
        }
        double stdDev = Math.sqrt(sumSquares / cereals.size());

        // Sort by Nutrition Density
        Collections.sort(cereals, new Comparator<Cereal>() {
            @Override
            public int compare(Cereal c1, Cereal c2) {
                return Double.compare(c2.getNutritionDensity(), c1.getNutritionDensity());
            }
        });

        // Print Results
        System.out.println("FULL CEREAL RANKINGS (Nutrition per Volume)");
        System.out.println("Definition: (Protein+Fiber+Potassium+Carbs+Vitamins - Cal+Sugar+Sodium+Fat) / Cups");
        for (int i = 0; i < cereals.size(); i++) {
            System.out.println((i + 1) + ". " + cereals.get(i));
        }

        Cereal topCereal = cereals.get(0);
        double zScore = (topCereal.getNutritionDensity() - mean) / stdDev;

        System.out.println("\nSUMMARY:");
        System.out.println("Most Densely Nutritious Cereal: " + topCereal.getName());
        System.out.println("Average Score: " + String.format("%.2f", mean));
        System.out.println("Top Score: " + String.format("%.2f", topCereal.getNutritionDensity()));
        System.out.println("Standard Deviation: " + String.format("%.2f", zScore));
        System.out.println("\nANSWER: The most densely nutritious cereal is " + topCereal.getName() + 
                           ", which is " + String.format("%.2f", zScore) + 
                           " standard deviations above the average nutrition density.");
    }
}
