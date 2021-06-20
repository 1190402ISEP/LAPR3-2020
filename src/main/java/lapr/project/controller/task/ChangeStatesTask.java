package lapr.project.controller.task;

import lapr.project.data.EmailHandler;
import lapr.project.data.PlanningDB;
import lapr.project.model.ChargerDivider;
import lapr.project.model.VehicleType;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The type Change states task.
 */
public class ChangeStatesTask {

    private int pharmacyID;

    private int droneID;
    private int scooterID;
    private int courierNIF;


    private final PlanningDB db;


    /**
     * Instantiates a new Change states task.
     *
     * @param scooterID  the scooter id
     * @param courierNIF the courier nif
     * @param pharmacyID the pharmacy id
     */
    public ChangeStatesTask(int scooterID, int courierNIF, int pharmacyID) {
        this.scooterID = scooterID;
        this.courierNIF = courierNIF;
        this.pharmacyID = pharmacyID;
        db = new PlanningDB();
    }

    /**
     * Instantiates a new Change states task.
     *
     * @param scooterID  the scooter id
     * @param courierNIF the courier nif
     * @param db         the db
     * @param pharmacyID the pharmacy id
     */
    public ChangeStatesTask(int scooterID, int courierNIF, PlanningDB db, int pharmacyID) {
        this.scooterID = scooterID;
        this.courierNIF = courierNIF;
        this.db = db;
        this.pharmacyID = pharmacyID;
    }

    /**
     * Instantiates a new Change states task.
     *
     * @param droneID    the drone id
     * @param db         the db
     * @param pharmacyID the pharmacy id
     */
    public ChangeStatesTask(int droneID, PlanningDB db, int pharmacyID) {

        this.db = db;
        this.pharmacyID = pharmacyID;
        this.droneID = droneID;

    }

    /**
     * Instantiates a new Change states task.
     *
     * @param db the db
     */
    public ChangeStatesTask(PlanningDB db) {
        this.db = db;
    }

    /**
     * Run boolean.
     *
     * @return the boolean
     */
    public boolean run() {
        boolean flag = false;

        db.changeCourierToAvailable(courierNIF);
        Integer chargingStationID;
        if (courierNIF == 0) {
            chargingStationID = db.getAvailableChargingStation(pharmacyID, VehicleType.DRONE);
            if (chargingStationID != 0) {
                try {
                    //db.putPlaceUnavailable
                    parkingAndChargingDrone100(droneID, pharmacyID);
                    //indisponivel
                    flag = true;
                } catch(IOException e) {
                    //e.printStackTrace();
                }
            }
        } else {
            chargingStationID = db.getAvailableChargingStation(pharmacyID, VehicleType.SCOOTER);
            if (chargingStationID != 0) {
                try {
                    //db.putPlaceUnavailable
                    parkingAndChargingScooter100(scooterID, courierNIF, pharmacyID);
                    //indisponivel
                    flag = true;
                } catch(IOException e) {
                    //e.printStackTrace();
                }
            }
        }

        return flag;

    }

    /**
     * Parking and charging scooter 100 float.
     *
     * @param scooterID  the scooter id
     * @param courierNIF the courier nif
     * @param pharmacyID the pharmacy id
     * @return the float
     * @throws IOException the io exception
     */
    public float parkingAndChargingScooter100(Integer scooterID, Integer courierNIF, Integer pharmacyID) throws IOException {
        int batteryM = db.getBatteryByScooterID(scooterID, pharmacyID);
        Integer battery = 100-batteryM;
        Integer batteryCapacity = db.getBatteryCapacityByScooterID(scooterID, pharmacyID);
        Integer energyToCharge = (battery*batteryCapacity)/100;

        return parkingAndChargingScooter(scooterID, pharmacyID, energyToCharge);
    }

    /**
     * Parking and charging drone 100 float.
     *
     * @param scooterID  the scooter id
     * @param pharmacyID the pharmacy id
     * @return the float
     * @throws IOException the io exception
     */
    public float parkingAndChargingDrone100(Integer scooterID, Integer pharmacyID) throws IOException {
        int batteryM = db.getBatteryByScooterID(scooterID, pharmacyID);
        Integer battery = 100-batteryM;
        Integer batteryCapacity = db.getBatteryCapacityByScooterID(scooterID, pharmacyID);
        Integer energyToCharge = (battery*batteryCapacity)/100;

        return parkingAndCharginDrones(scooterID, pharmacyID, energyToCharge);
    }

    /**
     * Parking and charging scooter float.
     *
     * @param scooterID      the scooter id
     * @param pharmacyID     the pharmacy id
     * @param EnergyToCharge the energy to charge
     * @return the float
     * @throws IOException the io exception
     */
    public float parkingAndChargingScooter(Integer scooterID, Integer pharmacyID, Integer EnergyToCharge) throws IOException{

        String pathFile = "CalculateTimeToFullyCharge/";
        float time = 0.0F;

        db.changeToCharging(scooterID);

        Integer BatteryCapacity = db.getBatteryCapacityByScooterID(scooterID, pharmacyID);
        float midcac = ((float) EnergyToCharge/ (float)BatteryCapacity);
        float Batteryb =  midcac*100;
        Integer Battery = (int) Batteryb-1;
        Integer Voltage = db.getVoltageByPharmacyID(pharmacyID, VehicleType.SCOOTER);



        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mmss");
        Date date = new Date();
        String dateformat = dateFormat.format(date);

        File myObj = new File(pathFile + "lock_" + dateformat + ".data");
        boolean flagCreated = false;
        if (myObj.createNewFile()) {
            PrintWriter writer = new PrintWriter(myObj);
            writer.printf("%d %d %d", Battery, BatteryCapacity, Voltage);
            writer.close();
            //creates the flag file
            File flagobj = new File(pathFile + "lock_" + dateformat + ".flag");
            flagCreated = flagobj.createNewFile();

        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(Exception e) {
            //nada
        }

        boolean flag = true;
        boolean secondflag = false;
        if (flagCreated) {
            while(flag) {
                File dir = new File(pathFile);
                File[] foundFiles = dir.listFiles((dir1, name) -> name.startsWith("estimativa_"));
                try {
                    assert foundFiles != null;
                    for (File file : foundFiles) {
                        if(file.getName().endsWith(".flag")) {
                            secondflag = true;
                        }
                        if(file.getName().endsWith(".data") && secondflag) {
                            Scanner sc = new Scanner(file);

                            String emailCourier = "";

                            time = Float.parseFloat(sc.nextLine());


                            Long timestart = date.getTime();
                            Long timeend = date.getTime() + calculateToMilli(time);

                            ChargerDivider.putMapndSend(pharmacyID, scooterID, emailCourier, timestart, timeend, new EmailHandler());
                            sc.close();
                            flag = false;
                        }
                    }
                } catch(NullPointerException e) {
                    //nada
                    flag = true;
                }
                if(!flag) {
                    for (File file : foundFiles) {
                        file.delete();
                    }
                }
            }
        }

        //db.putPlaceAvailable
        db.TrullyCharge(scooterID, Battery ,pharmacyID);
        //db.changetoReady
        return time;

    }

    /**
     * Parking and chargin drones float.
     *
     * @param droneID        the drone id
     * @param pharmacyID     the pharmacy id
     * @param EnergyToCharge the energy to charge
     * @return the float
     * @throws IOException the io exception
     */
    public float parkingAndCharginDrones(Integer droneID, Integer pharmacyID, Integer EnergyToCharge) throws IOException {

        db.changeToCharging(droneID);

        float batteryCapacity = db.getBatteryCapacityByScooterID(droneID, pharmacyID);
        float battery = (EnergyToCharge/batteryCapacity)-0.01F;
        float voltage = db.getVoltageByPharmacyID(pharmacyID, VehicleType.DRONE);

        float time = battery*batteryCapacity/voltage;
        Date date = new Date();
        Long timestart = date.getTime();
        Long timeend = calculateToMilli(time)+timestart;
        ChargerDivider.putMapndSend(pharmacyID+100, droneID, "moussamanafa@outlook.pt", timestart, timeend, new EmailHandler());

        //db.putPlaceAvailable
        float Percentual = battery*100;
        db.TrullyCharge(droneID, (int) Percentual ,pharmacyID);
        //db.changetoReady
        return time;
    }

    /**
     * Calculate to milli long.
     *
     * @param hours the hours
     * @return the long
     */
    public Long calculateToMilli(float hours) {
        float help = hours*60*60*1000;
        return (long) help;
    }
}