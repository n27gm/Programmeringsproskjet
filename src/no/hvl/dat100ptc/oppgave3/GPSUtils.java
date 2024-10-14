package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitude = new double[gpspoints.length];
		for (int i = 0; i<gpspoints.length; i++) {
			latitude[i] = gpspoints[i].getLatitude();
		}
		
		return latitude;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitude = new double[gpspoints.length];
		for (int i = 0; i<gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		
		return longitude;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 = toRadians(gpspoint1.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude2 = toRadians(gpspoint2.getLongitude());

		double deltaphi = latitude2 - latitude1;
		double deltadelta = longitude2 - longitude1;
		
		double a = compute_a(latitude1,latitude2,deltaphi,deltadelta);
		double c = compute_c(a);
		d = R*c;
		return d;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		double a = pow((sin(deltaphi/2)),2)+cos(phi1)*cos(phi2)*pow((sin(deltadelta/2)),2);
		return a;

	}

	private static double compute_c(double a) {

		double c = 2*atan2(sqrt(a),sqrt(1-a));
		return c;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = distance(gpspoint1,gpspoint2)/secs;
		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		int hh = 0;
		int min = 0;
		
		//fjern 60 sek av sek for hver min og 3600 sek for hver time
		for (int i = 0;secs>60;i++) {
			for(int j = 0; secs>3600; j++) {
				secs -= 3600;
				hh++;
			}
			min++;
			secs -= 60;
		}
		
		String form = "  " + (hh/10) + (hh%10) + TIMESEP + (min/10) + (min%10) + TIMESEP + (secs/10) + (secs%10);
		timestr = String.format(form,secs);
		return timestr;
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		d *= 100;
		double r = round(d);
		str = "      " + r/100;

		return str;
		
	}
}
