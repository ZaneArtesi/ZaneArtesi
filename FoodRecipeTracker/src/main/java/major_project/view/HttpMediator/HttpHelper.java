package major_project.view.HttpMediator;

/**
 *  This helps to dictate what is run depending if running as dummy or not
 *  It is dependent on if input is dummy only - as output is handled elsewhere
 */
public interface HttpHelper {

    /**
     * Online / Offline - Will submit the input to the model for parsing
     * @param input
     */
    void submitSearchParse(String input);

    /**
     * Online - Retrieves quantity, measureURI and foodID and calls to view for submisison
     * Offline - Calls to view with dummy data
     */
    void submitNutrients();

    /**
     * Online - Calls selectItem in view with the inputted string
     * Offline - Calls selectItem in view with dummy data
     */
    void submitTopSearch();

}
