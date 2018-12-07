package autoriaapi;

import com.opencsv.CSVReader;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class baseResources {


    //
    String baseUrl = "https://developers.ria.com/auto/";
    String api_key = "xmu8sNMjwhO29eIrRpTQMOG5KbFg6domccVFwzIZ";
    // search parameters
    String category ;//= "1";
    String mark ;//= "4";  //Asia
    String model; //= "31"; // Rosta
    String year_from; // = "1991"; //Year from
    //String year_to = "1991";
    String searchrequest;
    String line;

//    https://developers.ria.com/auto/search?api_key=xmu8sNMjwhO29eIrRpTQMOG5KbFg6domccVFwzIZ&category=1&marka_id[0]=4&model_id[0]=31



    public void getrequstparamsfromfile(String filewithdata){
        try {
            // Create an object of filereader
            FileReader filereader = new FileReader(filewithdata);
            // create csvReader object passing
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                category = nextRecord[0];
                mark = nextRecord[1];
                model = nextRecord[2];
                year_from = nextRecord[3];
            }
            //System.out.print(this.category+" "+this.mark+" "+this.model+" "+this.year_from);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String getsearchrequest() {
        searchrequest = baseUrl+"search?"+"api_key=" + api_key + "&category.main.id=" + category + "&marka_id[0]=" + mark + "&model_id[0]=" + model+"&s_yers[0]="+year_from; //  + "&year[0].gte="+ year_from + "year[0].lte=" + year_to
        //System.out.println(searchrequest);
        return searchrequest.toString() ;  }

    public String getAutoDataIDrequert(String id_auto) {
        return baseUrl+"info?"+"api_key=" + api_key + "&auto_id=" + id_auto;
    }

    //define connection
    public URLConnection getURLobject(String searchrequest) throws IOException {
        URL objurl = new URL(searchrequest);
        URLConnection urlCon = objurl.openConnection();
        return urlCon; }

    //object to get responce data such as Responce code
    public HttpURLConnection getHttpData(String searchrequest) throws MalformedURLException {
        URL url1 = new URL(searchrequest);
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url1.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlConnection;
    }

    // get responce data from stream
    public String getResponceData(String searchrequest) throws IOException {
        URL objurl = new URL(searchrequest);
        URLConnection urlCon = objurl.openConnection();
        InputStream inputStream = urlCon.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        this.line = reader.readLine();
        // close stream connection
        reader.close();
        return line;
    }

//object to parse responce data
    public JSONObject getJsonObject(String responcedata){
        JSONObject myResponse = new JSONObject(responcedata);
        return myResponse;
    }

    }
