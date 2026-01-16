import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cereal> cereals = new ArrayList<>();

   
        try (Scanner scanner = new Scanner(new File("Cereal.csv"))) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Skip header row

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

              
                Cereal c = new Cereal(
                    data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]), Integer.parseInt(data[5]), Double.parseDouble(data[6]),
                    Double.parseDouble(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]),
                    Integer.parseInt(data[10]), Integer.parseInt(data[11]), Double.parseDouble(data[12]),
                    Double.parseDouble(data[13]), Double.parseDouble(data[14])
                );
                cereals.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


        
        // Calculate Mean Density
        double totalDensity = 0;
        for (Cereal c : cereals) totalDensity += c.getNutritionDensity();
        double mean = totalDensity / cereals.size();

        // Find Max Density
        Cereal topCereal = cereals.get(0);
        for (Cereal c : cereals) {
            if (c.getNutritionDensity() > topCereal.getNutritionDensity()) {
                topCereal = c;
            }
        }

        // Calculate Standard Deviation
        double sumSquares = 0;
        for (Cereal c : cereals) {
            sumSquares += Math.pow(c.getNutritionDensity() - mean, 2);
        }
        double stdDev = Math.sqrt(sumSquares / cereals.size());

        double zScore = (topCereal.getNutritionDensity() - mean) / stdDev;
      
        System.out.println("Average Nutrition Density: " + String.format("%.2f", mean));
        System.out.println("Most Densely Nutritious Cereal: " + topCereal.getName());
        System.out.println("Max Density Value: " + String.format("%.2f", topCereal.getNutritionDensity()));
        System.out.println("\nANSWER: The most densely nutritious cereal is " + topCereal.getName() + ", which is " +
                           String.format("%.2f", zScore) + " standard deviations above the average.");
    }
}
