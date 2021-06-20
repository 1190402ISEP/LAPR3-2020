package lapr.project.ui;

import lapr.project.model.utils.Utils;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Logger.
 */
public class Logger {

    private static final String LOGDIR = Utils.getPath() + "Logs/";
    private static final String LOG_EXTENSION = ".txt";
    private static final String STANDARD_FILE_NAME = "output";

    private Logger () {

    }

    /**
     * Log.
     *
     * @param data the data
     */
    public static void log(String data) {
        log(data, STANDARD_FILE_NAME);
    }

    /**
     * Log.
     *
     * @param data     the data
     * @param filename the filename
     */
    public static void log(String data, String filename) {
        String pathToLogFile = LOGDIR.concat(filename).concat(LOG_EXTENSION);
        File output = new File(pathToLogFile);

        try ( BufferedWriter writer = new BufferedWriter( new FileWriter( output, true ) ) ) {


            Calendar c = Calendar.getInstance();
            Date d = new Date();
            c.setTime(d);

            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);
            writer.write(hour + ":" + minute + ":" + second + " | " + data);
            writer.write('\n');

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
