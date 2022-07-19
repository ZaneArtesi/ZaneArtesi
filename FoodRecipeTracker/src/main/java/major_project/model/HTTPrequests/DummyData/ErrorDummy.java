package major_project.model.HTTPrequests.DummyData;

public class ErrorDummy {

    public static String getData() {
        return
                "  {\n" +
                "    \"errorCode\": \"404\",\n" +
                "    \"message\": \"There was an error\",\n" +
                "    \"params\": [\n" +
                "      \"param1\"\n" +
                "    ]\n" +
                "  }\n"
                ;
    }
}
