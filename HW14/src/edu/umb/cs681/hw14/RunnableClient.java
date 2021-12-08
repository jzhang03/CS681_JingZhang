//
// CS681: HW14
// Git Repositories: https://github.com/jzhang03/CS681
// Git Name: jzhang03
//

package edu.umb.cs681.hw14;

public class RunnableClient {
	public static void main(String[] args) {
        AdmissionMonitor monitor = new AdmissionMonitor();
        EntranceHandler entranceHandler = new EntranceHandler(monitor);
        ExitHandler exitHandler = new ExitHandler(monitor);
        StatsHandler statsHandler = new StatsHandler(monitor);

        Thread entranceThread = new Thread(entranceHandler);
        entranceThread.start();
        Thread exitThread = new Thread(exitHandler);
        exitThread.start();
        Thread statsThread = new Thread(statsHandler);
        statsThread.start();

        try{
            Thread.sleep(1000);
        } catch (Exception e) {
           e.printStackTrace();
        }

        entranceHandler.setDone();
        exitHandler.setDone();
        statsHandler.setDone();
        entranceThread.interrupt();
        exitThread.interrupt();
        statsThread.interrupt();
        try {
            entranceThread.join();
            exitThread.join();
            statsThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
