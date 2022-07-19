package major_project.model;

import major_project.model.DB.DBHelper;
import major_project.model.DB.Database;
import major_project.model.DB.DatabaseOffline;
import major_project.model.HTTPrequests.InputAPI.*;
import major_project.model.HTTPrequests.OutputAPI.POJOs.PasteBinPOSTOffline;
import major_project.model.HTTPrequests.OutputAPI.PasteBinPOST;
import major_project.model.HTTPrequests.OutputAPI.PostHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class AppEngine {

    private final ArrayList<Observers> observersList;
    private final String INPUT_API_KEY;
    private final String INPUT_API_APP_ID;
    private final String PASTEBIN_API_KEY;

    private HashMap<String, String> searchResult;
    private HashMap<String, String> nutrientResult;
    private HashMap<String, Double> parsedNutrientResult;
    private HashMap <String, Double> setMaxes;
    private HashMap<String, Double> runningTotal;
    private ArrayList<String> totalAddedFood;
    private HashMap<String, ArrayList<Integer>> graphResultMap;
    private String previousPaste;    private String prevQueryResult;
    private ArrayList<String> prevHTTPparam;

    public boolean inputDummy;
    public boolean outputDummy;
    private GetHelper getHelper;
    private GetNutrientsHelper getNutrientsHelper;
    private PostHelper postHelper;
    private DBHelper dbHelper;
    private boolean cacheHit;
    private boolean rejectCacheHit;


    public AppEngine() {
        INPUT_API_KEY = System.getenv("INPUT_API_KEY");
        INPUT_API_APP_ID = System.getenv("INPUT_API_APP_ID");
        PASTEBIN_API_KEY = System.getenv("PASTEBIN_API_KEY");
        observersList = new ArrayList<>();
        this.totalAddedFood = new ArrayList<>();
        this.runningTotal = new HashMap<>();
        this.setMaxes = new HashMap<>();
        this.parsedNutrientResult = new HashMap<>();
    }

    /**
     * Registers Observers by adding them to the observersList
     * @param observers observer to add
     */
    public void registerObserver(Observers observers) {
        this.observersList.add(observers);
    }

    /**
     * Updates All registered Observers
     */
    public void updateObservers() {
        for (Observers o : observersList) {
            o.update();
        }
    }

    /**
     * Handles both inputAPI's HTTP requests - aim to split into two separate functions next iteration
     * @param method - either parse or get nutrients
     * @param param - all the param's they wish to pass
     * @param isPopOut - if this is for a popOut window - it changes the behaviour
     * @param rejectCache - if we have a cache hit and the user decides to not use the cache / use cache
     * @return - returns 1 if successful cache hit or 0 if not
     */
    public int HTTPrequest(String method, ArrayList<String> param, boolean isPopOut, boolean rejectCache) {

        if (param.size() < 1) {
            return 0;
        }
        ArrayList<String> paramsInput = new ArrayList<>();
            if (method.equals("parse")) {
                paramsInput.add(INPUT_API_APP_ID);
                paramsInput.add(INPUT_API_KEY);
                paramsInput.add(param.get(0));

                searchResult = getHelper.parser(paramsInput, isPopOut);
                if (!isPopOut) {
                    updateObservers();
                }
            }

            if (method.equals("getNutrients")) {
                this.cacheHit = false;
                this.rejectCacheHit = false;
                param.add(INPUT_API_APP_ID);
                param.add(INPUT_API_KEY);

                String queryResult = this.dbHelper.searchFood(param.get(0), param.get(1), param.get(2));

                if ( (queryResult != null) && (!rejectCache) ) {
                    System.out.println("CACHE HIT");
                    this.cacheHit = true;
                    this.prevQueryResult = queryResult;
                    this.prevHTTPparam = param;
                    return 1;
                }

                this.nutrientResult = this.getNutrientsHelper.getNutrients(param);
                formatOuput(nutrientResult.get("response"));
                this.dbHelper.addFood(param.get(0), param.get(1), param.get(2), nutrientResult.get("response"));
                this.rejectCacheHit = true;
                updateObservers();
            }

        return 0;
    }

    /**
     * If the user decides to use the cache - we utilise the cached data as we would normal search result
     */
    public void useCache() {
        HashMap<String, String> result = new HashMap<>();
        result.put("statusCode", "200");
        result.put("response", this.prevQueryResult);
        this.nutrientResult = result;
        formatOuput(this.prevQueryResult);
        updateObservers();
    }

    /**
     * If the user rejects the cache, we call the API as we normally would with reject cache true
     */
    public void rejectCache() {
        HTTPrequest( "getNutrients", this.prevHTTPparam, true, true);
    }

    /**
     * Updates the running nutrient totals
     * @param result the previous search result - get this data and add to existing HashMap of all nutrients
     */
    public void updateRunningTotals(String result) {
        String[] split = result.split(",, ");


        for (int i = 1; i < split.length-1; i++) {
            if (split[i].contains("null")) {
                continue;
            }
            String label = split[i].split(" - ")[0].split("=")[1].strip();
            try {
                double amount = Double.parseDouble(split[i].split(" - ")[1]);

                if (runningTotal.containsKey(label)) {
                    double oldAmount = runningTotal.get(label);
                    amount += oldAmount;
                }
                runningTotal.put(label, amount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Formats the result from an API search by adding the nutrients into a HashMap as NAME, AMOUNT
     * @param result the search result
     */
    public void formatOuput(String result) {
        String[] split = result.split(",, ");
        this.parsedNutrientResult.clear();

        for (int i = 1; i < split.length-1; i++) {
            String label = split[i].split(" - ")[0].split("=")[1].strip();
            try {
                double amount = Double.parseDouble(split[i].split(" - ")[1]);

                parsedNutrientResult.put(label, amount);
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    /**
     * Will set the max nutrient based on user input
     * @param nutrient the type of nutrient they want the max for and the measurement type in brackets
     * @param amount the max amount they want to set
     * @return returns -1 if the entered value could not be paresed to double, 0 if successful
     */
    public int setSetMaxes(String nutrient, String amount) {
        try {
            double d = Double.parseDouble(amount);
            this.setMaxes.put(nutrient, d);
            return 0;
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Will generate the numbers required for the graph
     * @return returns a HashMap<String, ArrayList<Integer>>
     *     The String is the name / label of the nutrient
     *     The ArrayList will be length 2 if the set max is higher than the running total containing the percentage and
     *     the stacked amount to display it accurately
     *
     *     The ArrayList will be length 1 if the running total has exceeded the max containing only the percentage of
     *     how much of the max it is
     */
    public HashMap<String, ArrayList<Integer>> generateGraph() {
        HashMap<String, ArrayList<Integer>> resultMap = new HashMap<>();

        int hundread = 100;
        double maxAmount;
        double addedAmount;
        double percentage;
        int convertedPercentage;

        for (String s : this.getSetMaxes().keySet()) {
            hundread = 100;
            maxAmount = this.getSetMaxes().get(s);
            addedAmount = this.getRunningTotal().get(s);
            percentage = addedAmount/maxAmount;
            convertedPercentage = (int) (percentage * 100);

            ArrayList<Integer> percentagesList = new ArrayList<>();

            if (convertedPercentage < hundread) {
                hundread = 100 - convertedPercentage;
                percentagesList.add(convertedPercentage);
                percentagesList.add(hundread);
            } else {
                // we have exceeded the max so just return percent e.g. 200% over max
                percentagesList.add(convertedPercentage);
            }
            resultMap.put(s, percentagesList);
        }
        this.graphResultMap = resultMap;
        return resultMap;
    }

    /**
     * Calls the functions that interacts with the PasteBin API
     * @param type either long or short
     * @return returns the string of the response recieved from PasteBin
     */
    public String outputAPI(String type) {
        if ((compileTotalAddedFood() == null) || (runningTotal.size() == 0) || (totalAddedFood.size() == 0)) {
            return null;
        }

        StringBuilder toPaste = new StringBuilder(compileTotalAddedFood());
        String result;

        if ((type.equals("long")) && (this.runningTotal != null)) {
            toPaste = new StringBuilder("Running totals for Nutrients: ");
            for (String s : this.runningTotal.keySet()) {
                toPaste.append(s).append(": ").append(this.runningTotal.get(s)).append("   ");
            }
        }

        System.out.println("Tobe Pasted: " + toPaste);
        result = this.postHelper.post(toPaste.toString(), this.PASTEBIN_API_KEY);
        this.previousPaste = result;

        return result;
    }

    /**
     * Compiles the total added food so far so that it can be sent to PasteBin
     * @return String of the compiled food - or null if there is none
     */
    public String compileTotalAddedFood() {
        StringBuilder toPaste;

        if (this.totalAddedFood != null) {
            toPaste = new StringBuilder();
            for (String s : this.totalAddedFood) {
                toPaste.append(s);
                toPaste.append(" & ");
            }
            return toPaste.toString();
        }
        return null;
    }

    /**
     * When a user wishes to add food - it is confirmed and relevant data is updated
     * @param label the food label
     * @param measure the measure used
     * @param quantity the quantity
     */
    public void confimAddFood(String label, String measure, String quantity, String notes) {
        if (notes == null || notes.strip().equals("")) {
            notes = "no notes given";
        }
        String toAdd = label + ">>" + measure + ">>" + quantity + ">>" + notes;
        addFood(toAdd);
        updateRunningTotals(getNutrientResult().get("response"));
        updateObservers();
    }

    public HashMap<String, String> getSearchResult() {
        return this.searchResult;
    }

    public HashMap<String, String> getNutrientResult() {
        return this.nutrientResult;
    }

    public void setNutrientResult(HashMap<String, String> map) {
        this.nutrientResult = map;
    }

    public ArrayList<String> getTotalAddedFood() {
        return this.totalAddedFood;
    }

    public void addFood(String food) {
        this.totalAddedFood.add(food);
    }

    public String getInputAPIkey() {
        return this.INPUT_API_KEY;
    }

    public String getInputAPIid() {
        return this.INPUT_API_APP_ID;
    }

    public HashMap<String, Double> getSetMaxes() {
        return this.setMaxes;
    }

    public ArrayList<Observers> getObserversList() {
        return this.observersList;
    }

    public void setInputDummy(boolean runDummy) {
        this.inputDummy = runDummy;
        if (runDummy) {
            this.getHelper = new GetRequestsOffline();
            this.getNutrientsHelper = new POSTrequestsOffline();
            this.dbHelper = new DatabaseOffline();
        } else {
            this.getHelper = new GetRequests();
            this.getNutrientsHelper = new POSTrequests();
            this.dbHelper = new Database();
        }
    }


    public HashMap<String, Double> getParsedNutrientResult() {
        return this.parsedNutrientResult;
    }

    public HashMap<String, Double> getRunningTotal() {
        return this.runningTotal;
    }

    public void clearCache() {
        this.dbHelper.clearCache();
    }

    public boolean getInputDummy() {
        return this.inputDummy;
     }

    public void setTotalAddedFood(ArrayList<String> added) {
        this.totalAddedFood = added;
    }

    public void setAllMaxes(HashMap<String, Double> s) {
        this.setMaxes = s;
    }

    public void setRunningTotal(HashMap<String, Double> runningTotal) {
        this.runningTotal = runningTotal;
    }

    public HashMap<String, ArrayList<Integer>> getGraphResultMap() {
        return this.graphResultMap;
    }

    public void setGraphResultMap(HashMap<String, ArrayList<Integer>> map) {
        this.graphResultMap = map;
    }

    public String getPreviousPaste() {
        return this.previousPaste;
    }

    public void setParsedNutrientResult(HashMap<String, Double> p) {
        this.parsedNutrientResult = p;
    }

    public boolean getCacheHit() {
        return this.cacheHit;
    }

    public boolean getRejectCacheHit() {
        return this.rejectCacheHit;
    }

    public void setOutputDummy(boolean b) {
        this.outputDummy = b;
        if (outputDummy) {
            this.postHelper = new PasteBinPOSTOffline();
        } else {
            this.postHelper = new PasteBinPOST();
        }

    }

    public DBHelper getDbHelper() {
        return this.dbHelper;
    }

    public void setDbHelper(DBHelper helper) {
        this.dbHelper = helper;
    }
}
