package major_project.model.HTTPrequests.InputAPI.POJOs;

import java.util.ArrayList;

public class RootError {

    public String errorCode;
    public String message;
    public ArrayList<String> params;

    public RootError(String errorCode, String message, ArrayList<String> params) {
        this.errorCode = errorCode;
        this.message = message;
        this.params = params;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
