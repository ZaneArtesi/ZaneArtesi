
package major_project.model.HTTPrequests.InputAPI.POJOs;

import java.util.HashMap;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RootParse {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("parsed")
    @Expose
    private List<Object> parsed = null;
    @SerializedName("hints")
    @Expose
    private List<Hint> hints = null;
    @SerializedName("_links")
    @Expose
    private Links links;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Object> getParsed() {
        return parsed;
    }

    public void setParsed(List<Object> parsed) {
        this.parsed = parsed;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }


    public String measureToString() {
        String toReturn = "";
//        for (Hint h : hints) {
//            toReturn += h.getMeasures();
//            toReturn += "===";
//        }


        for (Measure m : hints.get(0).getMeasures()) {
            toReturn += m.getLabel();
            toReturn += "&&&";
            toReturn += m.getUri();
            toReturn += "&&&";
            toReturn += m.getWeight();
            toReturn += "===";
        }

        toReturn += "======";
        toReturn += hints.get(0).getFood().getNutrients().getEnercKcal() + "&&&" +
        hints.get(0).getFood().getNutrients().getProcnt() + "&&&" +
        hints.get(0).getFood().getNutrients().getFat() + "&&&" +
        hints.get(0).getFood().getNutrients().getChocdf() + "&&&" +
        hints.get(0).getFood().getNutrients().getFibtg();
        return toReturn;
    }

    @Override
    public String toString() {

        HashMap<String, String> hmap = new HashMap<>();

        String toReturn = "";
        for (Hint h : hints) {
            toReturn += h.getFood().getLabel();
            toReturn += "&&&";
            toReturn += h.getFood().getFoodId();
            toReturn += "&&&";
            toReturn += h.getMeasures();
            toReturn += "===";
        }


        return toReturn;
    }

}
