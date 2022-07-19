package major_project.model.HTTPrequests.InputAPI;

import java.util.ArrayList;
import java.util.HashMap;

public interface GetHelper {

    HashMap<String, String> parser(ArrayList<String> params, boolean popOut);
}
