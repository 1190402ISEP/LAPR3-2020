package lapr.project.model;

import lapr.project.data.EmailHandler;

import java.util.*;

/**
 * The type Charger divider.
 */
public class ChargerDivider {

    private ChargerDivider() {
    }

    /**
     * The constant chargingscooters.
     */
    public static Map<List<Long>, Charging> chargingscooters = new HashMap<>();

    /**
     * Put mapnd send int.
     *
     * @param pharmacyID   the pharmacy id
     * @param scooterID    the scooter id
     * @param courierEmail the courier email
     * @param timestart    the timestart
     * @param timeend      the timeend
     * @param eh           the eh
     * @return the int
     */
    public static int putMapndSend(Integer pharmacyID, Integer scooterID, String courierEmail, Long timestart, Long timeend, EmailHandler eh) {
        Charging c = new Charging(pharmacyID, scooterID, courierEmail);
        List<Long> timepair = new ArrayList<>();
        timepair.add(timestart);
        timepair.add(timeend);
        ChargerDivider.chargingscooters.put(timepair, c);
        int howManyNewMailsSent = 0;
        for(List<Long> pair : ChargerDivider.chargingscooters.keySet()) {
            Long first = pair.get(0);
            Long second = pair.get(1);
            Charging chere = ChargerDivider.chargingscooters.get(pair);
            if(!(first < timestart && second < timestart) && !(first > timeend && second > timeend) && chere.getPharmacyID().equals(pharmacyID)) {
                if(first.equals(timestart) && second.equals(timeend)) {
                    float time = toHours(timestart, timeend);
                    int howmanych = countHowManyCharging(timestart, timeend, pharmacyID);
                    float variavle =  time * howmanych;
                    int hours = onlyhours(variavle);
                    int minutes = onlyminutes(variavle);
                        eh.sendEmail("moussamanafa@outlook.pt", "Vehicle charging", "The vehicle with the ID-" + chere.getScooterID() + " is now in a charging state and it will take " + hours + " hours and " + minutes + " minutes to charge");
                } else {
                    float time = toHours(first, second);
                    int howmanych = countHowManyCharging(first, second, pharmacyID);
                    float variavle =  time * howmanych;
                    int hours = onlyhours(variavle);
                    int minutes = onlyminutes(variavle);
                        eh.sendEmail("moussamanafa@outlook.pt", "Vehicle charging delay", "The vehicle with the ID-" + chere.getScooterID() + " has delayed its charging time and now it will take " + hours + " hours and " + minutes + " minutes to charge");
                    howManyNewMailsSent++;
                }
            }
        }
        return howManyNewMailsSent;
    }

    /**
     * Count how many charging int.
     *
     * @param timestart  the timestart
     * @param timeend    the timeend
     * @param pharmacyID the pharmacy id
     * @return the int
     */
    public static int countHowManyCharging(Long timestart, Long timeend, Integer pharmacyID) {
        int i = 0;
        Set<List<Long>> list = ChargerDivider.chargingscooters.keySet();
        for(List<Long> pair : list) {
            Long first = pair.get(0);
            Long second = pair.get(1);
            Charging chere = ChargerDivider.chargingscooters.get(pair);
            if((!(first < timestart && second < timestart) && !(first > timeend && second > timeend)) && chere.getPharmacyID().equals(pharmacyID)) {
                i++;
            }
        }
        return i;
    }

    /**
     * To hours float.
     *
     * @param timestart the timestart
     * @param timeend   the timeend
     * @return the float
     */
    public static float toHours(long timestart, long timeend) {
        float variable = (float) timeend-timestart;
        return ((((variable)/60)/60)/1000);
    }

    public static int onlyhours(float time) {
        return (int) time;
    }

    public static int onlyminutes(float time) {
        int hours = (int) time;
        float minutesb = time-hours;
        float minutesc = minutesb*60;
        return (int) minutesc;
    }
}
