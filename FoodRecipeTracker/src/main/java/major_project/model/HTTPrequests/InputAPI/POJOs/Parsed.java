package major_project.model.HTTPrequests.InputAPI.POJOs;

public class Parsed {
    public double quantity;
    public String measure;
    public String food;
    public String foodId;
    public double weight;
    public double retainedWeight;
    public String measureURI;
    public String status;

    public Parsed(double quantity, String measure, String food, String foodId, double weight,
                  double retainedWeight, String measureURI, String status) {
        this.quantity = quantity;
        this.measure = measure;
        this.food = food;
        this.foodId = foodId;
        this.weight = weight;
        this.retainedWeight = retainedWeight;
        this.measureURI = measureURI;
        this.status = status;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getRetainedWeight() {
        return retainedWeight;
    }

    public void setRetainedWeight(double retainedWeight) {
        this.retainedWeight = retainedWeight;
    }

    public String getMeasureURI() {
        return measureURI;
    }

    public void setMeasureURI(String measureURI) {
        this.measureURI = measureURI;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
