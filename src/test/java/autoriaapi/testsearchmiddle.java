package autoriaapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;

public class testsearchmiddle {


    //public  baseResources resources = new baseResources();

    JSONObject myResponse;
    URLConnection myConnection;
    HttpURLConnection myHTMLdata;
    baseResources resources;

    public testsearchmiddle() {
        resources = new baseResources();
    }


    public  JSONArray getdatafromJson() throws IOException {
        //String searchrequest = resources.getsearchrequest();
        // String responcedata = resources.getResponceData(searchrequest);
        //myResponse = resources.getJsonObject(responcedata);

        myResponse = resources.getJsonObject(resources.getResponceData(resources.getsearchrequest()));
        System.out.println("ids test 3" + myResponse.getJSONObject("result").getJSONObject("search_result").getJSONArray("ids"));
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
        //System.out.println(request);
        //myResponse = resources.getJsonObject(resources.getResponceData(request));
        //System.out.println(myResponse.getJSONObject("autoData").get("categoryId"));
        //System.out.println(myResponse.get("markID"));
        //System.out.println(myResponse.get("modelID"));
        //System.out.println(myResponse.getJSONObject("autoData").get("year"));
        return "add param";
    }

}

