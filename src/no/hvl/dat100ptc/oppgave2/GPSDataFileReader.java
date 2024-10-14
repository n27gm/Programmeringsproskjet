package no.hvl.dat100ptc.oppgave2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GPSDataFileReader { 

    private static final String SEP_STR = ",";
    private static final String GPSLOGS_DIR = "src/logs/";

    public static GPSData readGPSFile(String filename) {
        GPSData gpsdata = null;

        try (BufferedReader br = new BufferedReader(new FileReader(GPSLOGS_DIR + filename + ".csv"))) {
            
            int n = Integer.parseInt(br.readLine());  
            gpsdata = new GPSData(n);

            br.readLine();  

            String line;
            while ((line = br.readLine()) != null) {
                String[] gpsdatapoint = line.split(SEP_STR);

                String time = gpsdatapoint[0];
                String latitude = gpsdatapoint[1];
                String longitude = gpsdatapoint[2];
                String elevation = gpsdatapoint[3];
                
                gpsdata.insert(time, latitude, longitude, elevation);
            }

        } catch (IOException e) {
            System.err.println("Feil ved lesing av GPS-filen: " + e.getMessage());
        }

        return gpsdata;
    }
}
