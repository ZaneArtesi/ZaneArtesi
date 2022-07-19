package major_project.model.HTTPrequests.InputAPI.POJOs;

import java.util.ArrayList;

public class RootNutrients {
    public String uri;
    public double calories;
    public double totalWeight;
    public ArrayList<Object> dietLabels;
    public ArrayList<String> healthLabels;
    public ArrayList<Object> cautions;
    public TotalNutrients totalNutrients;
    public TotalDaily totalDaily;
    public ArrayList<Ingredient> ingredients;

    public RootNutrients(String uri, double calories, double totalWeight,
                         ArrayList<Object> dietLabels, ArrayList<String> healthLabels,
                         ArrayList<Object> cautions, TotalNutrients totalNutrients,
                         TotalDaily totalDaily, ArrayList<Ingredient> ingredients) {
        this.uri = uri;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.cautions = cautions;
        this.totalNutrients = totalNutrients;
        this.totalDaily = totalDaily;
        this.ingredients = ingredients;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public ArrayList<Object> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(ArrayList<Object> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public ArrayList<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(ArrayList<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public ArrayList<Object> getCautions() {
        return cautions;
    }

    public void setCautions(ArrayList<Object> cautions) {
        this.cautions = cautions;
    }

    public TotalNutrients getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(TotalNutrients totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public TotalDaily getTotalDaily() {
        return totalDaily;
    }

    public void setTotalDaily(TotalDaily totalDaily) {
        this.totalDaily = totalDaily;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

//    @Override
//    public String toString() {
//        return totalNutrients.toString();
//    }


    @Override
    public String toString() {
        return "RootNutrients" + "\n" +
                "calories=" + calories + "\n" +
                "totalWeight=" + totalWeight + "\n" +
                "dietLabels=" + dietLabels + "\n" +
                "healthLabels=" + healthLabels + "\n" +
                "cautions=" + cautions + "\n" +
                "totalNutrients=" + totalNutrients + "\n" +
                "\n\n";
    }
}
