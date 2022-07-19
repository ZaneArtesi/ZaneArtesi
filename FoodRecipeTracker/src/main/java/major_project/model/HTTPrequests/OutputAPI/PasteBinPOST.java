package major_project.model.HTTPrequests.OutputAPI;

import com.google.gson.Gson;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class PasteBinPOST implements PostHelper{

    @Override
    public String post(String toPaste, String apiDevKey) {

        String newURI = "https://pastebin.com/api/api_post.php";
        Gson gson = new Gson();


        HttpRequest request;
        String data = String.format("api_dev_key=%s&api_paste_code=%s&api_option=paste", apiDevKey, toPaste);
        return PasteBinSend.sendPost(data, newURI);

    }

}




