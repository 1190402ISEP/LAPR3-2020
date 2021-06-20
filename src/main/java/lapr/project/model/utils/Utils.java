package lapr.project.model.utils;

import lapr.project.model.CourierState;


import lapr.project.model.files.ReadFiles;
import lapr.project.ui.Logger;


import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.TimeZone;

/**
 * The type Utils.
 */
public class Utils {

    private static String path;


    /**
     * Start.
     *
     * @param filename the filename
     */
    public static void start(String filename) {
        try {
            path = ReadFiles.readFileScenario(filename).get(0)[0];

        } catch (FileNotFoundException e) {
            System.err.println("Path not found");
            System.exit(0);
        }
    }


    /**
     * Gets path.
     *
     * @return the path
     */
    public static String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public static void setPath(String path) {
        Utils.path = path;
    }

    /**
     * Create date date.
     *
     * @param date the date
     * @return the date
     */
    public static Date createDate(String date) {
        String[] data = date.split("-");

        int day = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int year = Integer.parseInt(data[2]);

        Calendar c = Calendar.getInstance();

        c.set(year, month, day);

        return c.getTime();
    }

    /**
     * Listar.
     *
     * @param <E>  the type parameter
     * @param item the item
     */
    public static <E> void listar(List<E> item) {
        int i = 1;
        for (E p : item) {
            Logger.log(i + "- " + p);
            i++;
        }

    }

    /**
     * Gets date underscore.
     *
     * @return the date underscore
     */
    public static String getDateUnderscore() {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());

        int day;
        int month;
        int year;
        int hour;
        int minute;
        int second;

        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH) + 1;
        year = c.get(Calendar.YEAR);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);

        return String.format("%d_%d_%d_%d_%d_%d", day, month, year, hour, minute, second);


    }


    /**
     * Gets item by option.
     *
     * @param <E>  the type parameter
     * @param list the list
     * @param op   the op
     * @return the item by option
     */
    public static <E> E getItemByOption(List<E> list, int op) {
        return list.get(op - 1);
    }


    /**
     * Gets state by id.
     *
     * @param id the id
     * @return the state by id
     */
    public static CourierState getStateByID(int id) {
        for (CourierState pos : CourierState.values()) {
            if (pos.getId() == id) {
                return pos;
            }
        }
        return null;
    }

    /**
     * List states.
     */
    public static void  listStates(){
        StringBuilder s= new StringBuilder();
        s.append("Choose the state:\n");
        for (CourierState pos:CourierState.values()) {
            s.append(pos.getId()+" "+pos);
            s.append("\n");
        }
        Logger.log(s.toString());
    }

    public static String timeMinutesFormat(float timeMinutes) {

        long toMilis = (long) (timeMinutes * 60 * 1000);

        Date date = new Date(toMilis);
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = formatter.format(date);
        return dateFormatted + " min";
    }


}




