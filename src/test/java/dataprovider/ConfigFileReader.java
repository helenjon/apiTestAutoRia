package dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= "configs/Configuration.properties";


    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getApiKey(){
        String APIKEY = properties.getProperty("apiKey");
        if(APIKEY!= null) return APIKEY;
        else throw new RuntimeException("apikey not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getBaseUrl() {
        String BASEURL = properties.getProperty("baseUrl");
        if(BASEURL != null) return BASEURL;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }
    public String getCheckSearchCriteriaCategoryId() {
        String CategoryId = properties.getProperty("checkSearchCriteria_category_id");
        if(CategoryId != null) return CategoryId;
        else throw new RuntimeException("CategoryId not specified in the Configuration.properties file.");
    }
    public String getCheckSearchCriteriaMarkaId() {
        String BASEURL = properties.getProperty("checkSearchCriteria_marka_id[0]");
        if(BASEURL != null) return BASEURL;
        else throw new RuntimeException("SearchCriteria_marka_id[0] not specified in the Configuration.properties file.");
    }

}