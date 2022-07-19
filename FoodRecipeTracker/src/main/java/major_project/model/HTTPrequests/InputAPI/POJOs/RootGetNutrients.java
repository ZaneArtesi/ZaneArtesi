package major_project.model.HTTPrequests.InputAPI.POJOs;

import java.util.ArrayList;

public class RootGetNutrients {
    public ArrayList<Ingredient> ingredients;

    public RootGetNutrients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
