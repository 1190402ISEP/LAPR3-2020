package lapr.project.controller;



import lapr.project.data.CourierDB;
import lapr.project.model.*;
import lapr.project.ui.Logger;

import java.util.List;

/**
 * The type Manage courier controller.
 */
public class ManageCourierController {

    private final CourierDB cdb;


    /**
     * Instantiates a new Manage courier controller.
     */
    public ManageCourierController() {
        cdb = new CourierDB();
    }

    /**
     * Instantiates a new Manage courier controller.
     *
     * @param copy the copy
     */
    public ManageCourierController(CourierDB copy){
        cdb = copy;
    }

    /**
     * New courier courier.
     *
     * @param name  the name
     * @param email the email
     * @param nif   the nif
     * @param niss  the niss
     * @return the courier
     */
    public Courier newCourier(String name, String email, int nif, String niss){
        return cdb.newCourier(name,email,nif,niss);
    }


    /**
     * Add courier courier.
     *
     * @param name       the name
     * @param email      the email
     * @param nif        the nif
     * @param niss       the niss
     * @param pharmacyId the pharmacy id
     * @param vehicleId  the vehicle id
     * @return the courier
     */
    public Courier addCourier(String name,String email, int nif, String niss, int pharmacyId, int vehicleId){
        Courier c=newCourier(name,email,nif,niss);
        cdb.addCourierHandler(c,pharmacyId,vehicleId);
        Logger.log(String.format("New Courier:%n%s%n%n", c.toString()));
        return c;
    }


    /**
     * Gets courier list.
     *
     * @param pharmacyId the pharmacy id
     * @return the courier list
     */
    public List<Courier> getCourierList(int pharmacyId) {
        return cdb.getCourierList(pharmacyId);
    }



    /**
     * Update courier boolean.
     *
     * @param name       the name
     * @param email      the email
     * @param nif        the nif
     * @param niss       the niss
     * @param state      the state
     * @param pharmacyId the pharmacy id
     * @param vehicleId  the vehicle id
     * @return the boolean
     */

    public boolean updateCourier(String name,String email, int nif, String niss, int state, int pharmacyId, int vehicleId){

        return cdb.updateCourier(name,email,nif,niss,state,pharmacyId,vehicleId);
    }




}
