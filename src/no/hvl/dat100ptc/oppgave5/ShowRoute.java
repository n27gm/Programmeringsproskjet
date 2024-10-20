package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

	    setColor(0, 0, 255); 

	    for (int i = 0; i < gpspoints.length - 1; i++) {
		    
	        int x1 = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        int y1 = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);
	        int x2 = MARGIN + (int) ((gpspoints[i + 1].getLongitude() - minlon) * xstep);
	        int y2 = ybase - (int) ((gpspoints[i + 1].getLatitude() - minlat) * ystep);

 	        drawLine(x1, y1, x2, y2);
	    }
	}


	public void showStatistics() {

	    int TEXTDISTANCE = 20;  
	    int yPosition = 20;    

	    setColor(0, 0, 0); 
	    setFont("Courier", 12);  

	    drawString("Total time: " + GPSUtils.formatTime(gpscomputer.totalTime()), TEXTDISTANCE, yPosition);
	    yPosition += 20;
	    drawString("Total distance: " + String.format("%.2f", gpscomputer.totalDistance() / 1000) + " km", TEXTDISTANCE, yPosition);
	    yPosition += 20;
	    drawString("Total elevation: " + String.format("%.2f", gpscomputer.totalElevation()) + " m", TEXTDISTANCE, yPosition);
	    yPosition += 20;
	    drawString("Max speed: " + String.format("%.2f", gpscomputer.maxSpeed() * 3.6) + " km/t", TEXTDISTANCE, yPosition);
	    yPosition += 20;
	    drawString("Average speed: " + String.format("%.2f", gpscomputer.averageSpeed() * 3.6) + " km/t", TEXTDISTANCE, yPosition);
	    yPosition += 20;
	    drawString("Energy: " + String.format("%.2f", gpscomputer.totalKcal(80)) + " kcal", TEXTDISTANCE, yPosition);
	}


	public void replayRoute(int ybase) {

	    int x = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
	    int y = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep);

	    int radius = 5;
	    int circle = fillCircle(x, y, radius);
	    setColor(0, 0, 255); 

	    for (int i = 1; i < gpspoints.length; i++) {
	        int xNext = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        int yNext = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);

	        setSpeed(10);  

	        moveCircle(circle, xNext, yNext);

	        pause(100);
	    }
	}


}
