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
		
		elevation += gpspoints[0].getElevation();

		for (int i = 1; i<gpspoints.length-1; i++) {
			if (gpspoints[i].getElevation() > gpspoints[i-1].getElevation()) {
				elevation += gpspoints[i].getElevation() - gpspoints[i-1].getElevation();
			}
		}
		return elevation;
	}

	public int totalTime() {

		int time = 0;
		
		for (int i = 0; i<gpspoints.length; i++) {
			time += gpspoints[i].getTime();
		}
		return time;
	}

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		for (int i = 0; i<gpspoints.length-1; i++) {
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

		double average = 0;
		
		for (int i = 0; i<speeds().length; i++) {
			average += speeds()[i];
		} 
		return average /= speeds().length;
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;
		int t = secs/3600;

		if (speedmph < 10.0) {
			met = 4.0;
		} else if (speedmph >= 10.0 && speedmph < 12.0) {
			met = 6.0;
		} else if (speedmph >= 12.0 && speedmph < 14.0) {
			met = 8.0;
		} else if (speedmph >= 14.0 && speedmph < 16.0) {
			met = 10.0;
		} else if (speedmph >= 16.0 && speedmph <= 20.0) {
			met = 12.0;
		} else if (speedmph > 20.0) {
			met = 16.0;
		}
		kcal = met * weight * t;
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		for (int i = 0; i<speeds().length; i++) {
			totalkcal += kcal(weight,gpspoints[i].getTime(),speeds()[i]);
		}
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		String seperator = "==============================================";
		System.out.print(seperator + "\n" + 
		"Total time		:   " + totalTime() + "\n" +
		"Total distance :      " + (totalDistance()/1000) + " km" + "\n" +
		"Total elevation:     " + totalElevation() + " m" + "\n" +
		"Max speed		:      " + (maxSpeed()*3.6) + " km/t" + "\n" +
		"Average speed	:      " + (averageSpeed()*3.6) + " km/t" + "\n" +
		"Energy			:     " + kcal(WEIGHT,gpspoints[0].getTime(),speeds()[0]) + " kcal" + "\n" +
		seperator);
	}
}
