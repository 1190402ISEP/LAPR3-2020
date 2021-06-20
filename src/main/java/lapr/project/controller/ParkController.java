package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import lapr.project.model.ParkPlace;

import java.util.List;

/**
 * The type Park controller.
 */
public class ParkController {

    private final ParkDB parkDB;

    /**
     * Instantiates a new Park controller
     *  and a new Park DB.
     */
    public ParkController() {
        parkDB = new ParkDB();
    }

    /**
     * Instantiates a new Park controller.
     *
     * @param parkDB the park db
     */
    public ParkController( ParkDB parkDB ) {
        this.parkDB = parkDB;
    }

    /**
     * Create park boolean.
     *
     * @param park the park
     * @return the boolean
     */
    public boolean createPark ( Park park ) {
        return parkDB.createPark( park );
    }

    /**
     * Update park boolean.
     *
     * @param park the park
     * @return the boolean
     */
    public boolean updatePark ( Park park ) {
        return parkDB.updatePark( park );
    }

    /**
     * Remove park boolean.
     *
     * @param parkId the park id
     * @return the boolean
     */
    public boolean removePark ( int parkId ) {
        return parkDB.removePark( parkId );
    }

    /**
     * Show parks list.
     *
     * @return the list
     */
    public List<Park> showParks() {
        return parkDB.showParks();
    }

    public boolean createParkPlace ( ParkPlace place, boolean isCharging ) {
        return parkDB.createPlace( place,isCharging );
    }



}
