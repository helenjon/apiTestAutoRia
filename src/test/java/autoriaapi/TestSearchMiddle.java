package autoriaapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class TestSearchMiddle {


    JSONObject myResponse;
    HttpURLConnection myHTMLdata;
    BaseResources resources;

    public TestSearchMiddle() {
        resources = new BaseResources();
    }


    public  JSONArray getdatafromJson() throws IOException {
        myResponse = resources.getJsonObject(resources.getResponceData(resources.getsearchrequest()));
        return myResponse.getJSONObject("result").getJSONObject("search_result").getJSONArray("ids");

    }

    public  JSONObject getDataInJsonformatAnyRequest(String requeststring) throws IOException {
        myResponse = resources.getJsonObject(resources.getResponceData(requeststring));
        return myResponse;
    }



    public Integer responcecode200() throws MalformedURLException {
        Integer ResponseCode = 0;
        resources.getsearchrequest();
        myHTMLdata = resources.getHttpData(resources.searchrequest);
        try {
            ResponseCode = myHTMLdata.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseCode;
    }


    public String getdatabyAutoID(String AutoID, String parameter) throws IOException {
        String request = resources.getAutoDataIDrequert(AutoID);
        myResponse = resources.getJsonObject(resources.getResponceData(request));
        if (parameter =="categoryId"){
            return myResponse.getJSONObject("autoData").get("categoryId").toString();
        }
        if (parameter =="markId"){
            return myResponse.get("markId").toString();
        }
        if (parameter =="modelId"){
            return myResponse.get("modelId").toString();
        }
        if (parameter =="year"){
            return myResponse.getJSONObject("autoData").get("year").toString();
        }
        return "add param";
    }

}

