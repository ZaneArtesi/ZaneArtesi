package major_project.model.DB;

public class DatabaseOffline implements DBHelper{
    @Override
    public void cerateDB() {
        // Do nothing
    }

    @Override
    public void setupDB() {
        // do nothing
    }

    @Override
    public void addFood(String quantity, String measureURI, String foodID, String TotalNutrients) {
        // do nothing
    }

    @Override
    public String searchFood(String quantity, String measureURI, String foodID) {
        return null;
    }

    @Override
    public void clearCache() {
        // do nothing
    }
}