package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

    public static void main(String[] args) {
        GPSPoint point1 = new GPSPoint(0, 1.0, 2.0, 3.0);
        GPSPoint point2 = new GPSPoint(0, 4.0, 5.0, 6.0);
        GPSPoint point3 = new GPSPoint(0, 7.0, 8.0, 9.0);

        GPSData gpsData = new GPSData(3);

        gpsData.insertGPS(point1);
        gpsData.insertGPS(point2);
        gpsData.insertGPS(point3);

        gpsData.print();
    }

}
