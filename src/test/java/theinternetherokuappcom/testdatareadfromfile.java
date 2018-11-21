package theinternetherokuappcom;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class testdatareadfromfile {

    private List<String> username = new ArrayList<String>();
    private List<String> password= new ArrayList<String>();
    private List<String> expectedmessage = new ArrayList<String>();
    // get test para, from file
    public testdatareadfromfile(String filewithdata){
        try {
            // Create an object of filereader
            FileReader filereader = new FileReader(filewithdata);
            // create csvReader object passing
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
         // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                this.username.add(nextRecord[0]);
                this.password.add(nextRecord[1]);
                this.expectedmessage.add(nextRecord[2]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public List <String> getusername() {
        return this.username;
    }
    public List <String> getPassword() {
        return this.password;
    }
    public List <String> getExpectedmessage() {
        return this.expectedmessage;
    }

    }



