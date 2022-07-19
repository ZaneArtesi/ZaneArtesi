package major_project.model.HTTPrequests.InputAPI.POJOs;

import java.util.ArrayList;

public class IngredientPOST {
    public ArrayList<Parsed> parsed;

    public IngredientPOST(ArrayList<Parsed> parsed) {
        this.parsed = parsed;
    }
}
