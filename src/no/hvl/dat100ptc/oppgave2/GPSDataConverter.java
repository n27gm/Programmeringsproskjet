package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		String s = timestr.substring(TIME_STARTINDEX);
		hr = Integer.parseInt(s.substring(0,2));
		min = Integer.parseInt(s.substring(3,5));
		sec = Integer.parseInt(s.substring(6,8));
		return secs = hr*3600 + min*60 + sec;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);	
				
		gpspoint = new GPSPoint(time, latitude, longitude, elevation);

		return gpspoint;
	}
	
}
