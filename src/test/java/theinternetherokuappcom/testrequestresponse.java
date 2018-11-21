package theinternetherokuappcom;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class testrequestresponse {

    //  RestAssured.get("http://the-internet.herokuapp.com/status_codes/301").statusCode();
    public static Integer test(String url) throws IOException {
        URL objurl = new URL(url);
        HttpURLConnection httpCon = (HttpURLConnection) objurl.openConnection();
        return httpCon.getResponseCode();

        //System.out.println(httpCon.getResponseCode());

    }

}
