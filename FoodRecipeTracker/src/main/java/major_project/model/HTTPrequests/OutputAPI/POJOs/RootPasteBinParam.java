package major_project.model.HTTPrequests.OutputAPI.POJOs;

public class RootPasteBinParam {

    public String api_user_key;
    public String api_option;
    public String api_paste_code;

    public RootPasteBinParam(String api_user_key, String api_option, String api_paste_code) {
        this.api_user_key = api_user_key;
        this.api_option = api_option;
        this.api_paste_code = api_paste_code;
    }

    public String getApi_user_key() {
        return api_user_key;
    }

    public void setApi_user_key(String api_user_key) {
        this.api_user_key = api_user_key;
    }

    public String getApi_option() {
        return api_option;
    }

    public void setApi_option(String api_option) {
        this.api_option = api_option;
    }

    public String getApi_paste_code() {
        return api_paste_code;
    }

    public void setApi_paste_code(String api_paste_code) {
        this.api_paste_code = api_paste_code;
    }
}
