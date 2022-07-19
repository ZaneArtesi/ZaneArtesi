package major_project.model.HTTPrequests.InputAPI.POJOs;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

public class GeneralNutrients {

    @SerializedName("label")
    public String name;
    @SerializedName("quantity")
    public double quant;
    @SerializedName("unit")
    public String u;

    public GeneralNutrients(String name, double quant, String u) {
        this.name = name;
        this.quant = quant;
        this.u = u;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuant() {
        return quant;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();

        return this.name + "(" +this.u + ")" +
        " - " + df.format(this.quant).toString() + " - " + this.u +"\n";
    }
}
