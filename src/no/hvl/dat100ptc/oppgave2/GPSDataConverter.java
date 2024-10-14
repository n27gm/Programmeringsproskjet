package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		// TODO
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		int time = 0;
		double latitude = 0.0;
		double longitude = 0.0;
		double elevation = 0.0;	
				
		gpspoint = new GPSPoint(time, latitude, longitude, elevation);

		return gpspoint;
	}
	
}
