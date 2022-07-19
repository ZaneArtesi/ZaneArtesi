package major_project.model.HTTPrequests.InputAPI.POJOs;

public class Ingredient {
    public int quantity;
    public String measureURI;
    public String foodId;

    public Ingredient(int quantity, String measureURI, String foodId) {
        this.quantity = quantity;
        this.measureURI = measureURI;
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasureURI() {
        return measureURI;
    }

    public void setMeasureURI(String measureURI) {
        this.measureURI = measureURI;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
}
