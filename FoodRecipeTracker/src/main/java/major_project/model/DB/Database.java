package major_project.model.DB;

import java.io.File;
import java.sql.*;

public class Database implements DBHelper{
    private static final String dbName = "./src/main/java/major_project/model/DB/data.db";
    private static final String dbURL = "jdbc:sqlite:" + dbName;

    @Override
    public void cerateDB() {
        File dbFile = new File(dbName);
        if (dbFile.exists()) {
            System.out.println("Database already created");
            return;
        }
        try (Connection ignored = DriverManager.getConnection(dbURL)) {
            // If we get here that means no exception raised from getConnection - meaning it worked
            System.out.println("A new database has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setupDB() {

        String createFoodTable =
                """
                CREATE TABLE IF NOT EXISTS added_food (
                FoodID text NOT NULL,
                MeasureURI text NOT NULL,
                Quantity text NOT NULL,
                TotalNutrients text NOT NULL,    
                PRIMARY KEY (FoodID, MeasureURI, Quantity)
                );
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement statement = conn.createStatement()) {
            statement.execute(createFoodTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addFood(String quantity, String measureURI, String foodID, String TotalNutrients) {
        String addFood =
                """
                INSERT INTO added_food(FoodID, MeasureURI, Quantity, TotalNutrients) values (?,?,?,?)        
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
            PreparedStatement preparedStatement = conn.prepareStatement(addFood)) {
            preparedStatement.setString(1, foodID);
            preparedStatement.setString(2, measureURI);
            preparedStatement.setString(3, quantity);
            preparedStatement.setString(4, TotalNutrients);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                return;
            }
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public String searchFood(String quantity, String measureURI, String foodID) {

        String query =
                """
                SELECT TotalNutrients
                FROM added_food
                WHERE Quantity = ?
                AND MeasureURI = ?
                and FoodID = ?        
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, quantity);
            preparedStatement.setString(2, measureURI);
            preparedStatement.setString(3, foodID);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                // if there is no result - then there is no cached item
                return null;

            } else {
                // the entry does exist so return the id
                return rs.getString("TotalNutrients");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void clearCache() {

        String clear = "DELETE FROM added_food";

        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement pstmt = conn.prepareStatement(clear)) {

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
