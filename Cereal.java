public class Cereal {
    private String name;
    private String type;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private double fiber;
    private double carbohydrates;
    private int sugar;
    private int potassium;
    private int vitamins;
    private int shelf;
    private double weight;
    private double cups;
    private double rating;

    public Cereal(String name, String type, int calories, int protein, int fat, int sodium, 
                  double fiber, double carbohydrates, int sugar, int potassium, 
                  int vitamins, int shelf, double weight, double cups, double rating) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.fiber = fiber;
        this.carbohydrates = carbohydrates;
        this.sugar = sugar;
        this.potassium = potassium;
        this.vitamins = vitamins;
        this.shelf = shelf;
        this.weight = weight;
        this.cups = cups;
        this.rating = rating;
    }

    /**
     * Calculates Nutrition Density (Nutrition per Volume).
     * Justification: Nutritious means maximizing protein, fiber, potassium, carbs, and vitamins 
     * while minimizing calories, sugar, sodium, and fat.
     */
    public double getNutritionDensity() {
        double goodNutrients = protein + fiber + potassium + carbohydrates + vitamins;
        double badNutrients = calories + sugar + sodium + fat;
        return (goodNutrients - badNutrients) / cups;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("%-30s Score: %10.2f", name, getNutritionDensity());
    }
}
