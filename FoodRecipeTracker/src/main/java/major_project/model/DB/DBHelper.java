package major_project.model.DB;

public interface DBHelper {

    /**
     * Creates the database - if it is already in the correct spot it does nothing
     */
    void cerateDB();

    /**
     * Sets up the data base by creating tables if necessary
     */
    void setupDB();

    /**
     * Adds an entry to the database that as cache
     * @param quantity the amount
     * @param measureURI the measureURI corresponding to chosen measuremnt type
     * @param foodID the foodID corresponding to chosen food
     * @param TotalNutrients all the nutrients
     */
    void addFood(String quantity, String measureURI, String foodID, String TotalNutrients);

    /**
     * Searches to see if there is a matching element in the database
     * @param quantity the amount
     * @param measureURI the measureURI corresponding to chosen measuremnt type
     * @param foodID the foodID corresponding to chosen food
     * @return String of TotalNutrients or null if not found
     */
    String searchFood(String quantity, String measureURI, String foodID);

    /**
     * Clears all the cache
     */
    void clearCache();
}