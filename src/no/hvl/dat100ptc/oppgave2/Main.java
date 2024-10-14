package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

    public static void main(String[] args) {
        // 1. Opprett to GPSPoint-objekter
        GPSPoint point1 = new GPSPoint(31946, 60.385390, 5.217217, 61.9);  // Eksempelverdier
        GPSPoint point2 = new GPSPoint(32000, 60.389780, 5.222880, 70.5);  // Eksempelverdier

        // 2. Opprett et GPSData-objekt med plass til to GPSPoint-objekter
        GPSData gpsData = new GPSData(2);

        // 3. Sett inn de to GPSPoint-objektene i GPSData-tabellen
        gpsData.insertGPS(point1);
        gpsData.insertGPS(point2);

        // 4. Skriv ut informasjon om GPSPoint-objektene ved å bruke print-metoden
        gpsData.print();
    }
}

