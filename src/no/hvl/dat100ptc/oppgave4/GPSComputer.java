package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i<gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i],gpspoints[i+1]);
		}
		return distance;
	}

	public double totalElevation() {
	    double elevation = 0;
	    for (int i = 1; i < gpspoints.length; i++) {
	        if (gpspoints[i].getElevation() > gpspoints[i-1].getElevation()) {
	            elevation += gpspoints[i].getElevation() - gpspoints[i-1].getElevation();
	        }
	    }
	    return elevation;
	}


	public int totalTime() {

		int time = 0;
		
		for (int i = 1; i<gpspoints.length; i++) {
			time += gpspoints[i].getTime()-gpspoints[i-1].getTime();
		}
		return time;
	}

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		for (int i = 0; i<speeds.length; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]); 
		}
		
		return speeds;
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;

		maxspeed = GPSUtils.findMax(speeds());
		
		return maxspeed;
	}

	public double averageSpeed() {
	   
	    double totalSpeed = 0;
	    
	    return totalSpeed = totalDistance() / totalTime();
	}

	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
	    double kcal;
	    double met = 0;
	    double speedmph = speed * MS;
	    double t = secs / 3600.0; 

	    if (speedmph < 10.0) {
	        met = 4.0;
	    } else if (speedmph < 12.0) {
	        met = 6.0;
	    } else if (speedmph < 14.0) {
	        met = 8.0;
	    } else if (speedmph < 16.0) {
	        met = 10.0;
	    } else if (speedmph <= 20.0) {
	        met = 12.0;
	    } else {
	        met = 16.0;
	    }

	    kcal = met * weight * t;
	    return kcal;
	}


	public double totalKcal(double weight) {
	    double totalkcal = 0;
	    double[] speedsArray = speeds();

	    for (int i = 0; i < speedsArray.length; i++) {
	        int secs = gpspoints[i+1].getTime() - gpspoints[i].getTime();
	        totalkcal += kcal(weight, secs, speedsArray[i]);
	    }

	    return totalkcal;
	}

	
	public String formatTime(int secs) {
	    int hours = secs / 3600;
	    int minutes = (secs % 3600) / 60;
	    int seconds = secs % 60;

	    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

	    String separator = "==============================================";
	    System.out.println(separator);
	    System.out.println("Total time     :   " + formatTime(totalTime()));
	    System.out.println("Total distance :      " + String.format("%.2f", totalDistance()/1000) + " km");
	    System.out.println("Total elevation:     " + String.format("%.2f", totalElevation()) + " m");
	    System.out.println("Max speed      :      " + String.format("%.2f", maxSpeed() * 3.6) + " km/t");
	    System.out.println("Average speed  :      " + String.format("%.2f", averageSpeed() * 3.6) + " km/t");
	    System.out.println("Energy         :     " + String.format("%.2f", totalKcal(WEIGHT)) + " kcal");
	    System.out.println(separator);
	}

}
