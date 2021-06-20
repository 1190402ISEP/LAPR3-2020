package lapr.project.data;


import lapr.project.model.*;
import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * The type Planning db.
 */
public class PlanningDB extends DataHandler {


    /**
     * Gets cords by orders.
     *
     * @param pharmacyOrders the pharmacy orders
     * @return the cords by orders
     */
    public List<String> getCordsByOrders(List<Integer> pharmacyOrders) {
        CallableStatement callStmt;
        List<String> coordinates = new LinkedList<>();

        for (Integer orderID : pharmacyOrders) {
            try {
                callStmt = getConnection().prepareCall("{ ? = call getCordinatesByOrder(?) }");

                callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
                callStmt.setInt(2, orderID);

                callStmt.execute();

                String coordinateOf1Client = (String) callStmt.getObject(1);
                closeAll();
                coordinates.add(coordinateOf1Client);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return coordinates;
    }


    /*public Map<Integer, List<String>> getTotalPayloadByStock(int orderID, int pharmacyID, List<String> productnoStock) {
        CallableStatement callStmt;
        HashMap<Integer, List<String>> map = new HashMap<>();

        for (String barCode : productnoStock) {
            try {
                callStmt = getConnection().prepareCall("{ ? = call getTotalPayloadByStock(?,?,?) }");

                callStmt.registerOutParameter(1, OracleTypes.INTEGER);
                callStmt.setInt(2, orderID);
                callStmt.setInt(3, pharmacyID);
                callStmt.setString(4, barCode);

                callStmt.execute();

                int necessaryStock = (int) callStmt.getObject(1);
                closeAll();
                List<String> list = map.get(necessaryStock);
                list.add(barCode);
                map.put(necessaryStock, list);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }*/

    /**
     * Gets total payload by part.
     *
     * @param map the map
     * @return the total payload by part
     */
    public float getTotalPayloadByPart(Map<String, Integer> map, Integer pharmacyID) {
        CallableStatement callStmt;
        float TotalPayLoad = 0.0F;
        for (String barCode : map.keySet()) {
            Integer stock = map.get(barCode);
            try {
                callStmt = getConnection().prepareCall("{ ? = call getTotalPayloadByStock(?,?) }");

                callStmt.registerOutParameter(1, OracleTypes.FLOAT);
                callStmt.setString(2, barCode);
                callStmt.setInt(3, pharmacyID);

                callStmt.execute();

                Double Payload = (Double) callStmt.getObject(1);
                closeAll();
                TotalPayLoad = (float) (TotalPayLoad + (Payload*stock));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return TotalPayLoad;
    }

    /**
     * Gets coordinates by pharmacy id.
     *
     * @param pharmacyID the pharmacy id
     * @return the coordinates by pharmacy id
     */
    public String getCoordinatesByPharmacyID(Integer pharmacyID) {
        CallableStatement callStmt;
        String PharmacyCoordinates = "";
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCordinatesByPharmacyId(?) }");

            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            callStmt.setInt(2, pharmacyID);

            callStmt.execute();

            PharmacyCoordinates = (String) callStmt.getObject(1);
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PharmacyCoordinates;
    }

    /**
     * Gets available courier.
     *
     * @param pharmacyID the pharmacy id
     * @return the available courier
     */
    public int getAvailableCourier(Integer pharmacyID) {
        CallableStatement callStmt;
        int CourierNIF = 0;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAvailableCourier(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, pharmacyID);

            callStmt.execute();

            CourierNIF = (int) callStmt.getObject(1);
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CourierNIF;
    }

    /**
     * Gets available scooter.
     *
     * @param courierNIF the courier nif
     * @param pharmacyID the pharmacy id
     * @param payload    the payload
     * @return the available scooter
     */
    public int getAvailableScooter(int courierNIF, int pharmacyID, float payload) {
        CallableStatement callStmt;
        Integer ScooterID = 0;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAvailableScooter(?,?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, courierNIF);
            callStmt.setInt(3, pharmacyID);
            callStmt.setFloat(4, payload);

            callStmt.execute();

            ScooterID = (Integer) callStmt.getObject(1);
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ScooterID;
    }

    /**
     * Change states boolean.
     *
     * @param vehicleID  the vehicle id
     * @param courierNIF the courier nif
     * @param pharmacyID the pharmacy id
     * @return the boolean
     */
    public boolean changeStates(int vehicleID, Integer courierNIF, Integer pharmacyID) {
        CallableStatement callStmt;
        boolean flag = true;
        try {
            callStmt = getConnection().prepareCall("{ call changeStates(?,?,?) }");

            callStmt.setInt(1, vehicleID);

            callStmt.setInt(2, courierNIF);


            callStmt.setInt(3, pharmacyID);

            callStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * Change stock boolean.
     *
     * @param map        the map
     * @param pharmacyID the pharmacy id
     * @return the boolean
     */
    public boolean changeStock(Map<String, Integer> map, Integer pharmacyID) {
        CallableStatement callStmt;
        boolean flag = true;
        for (String barCode : map.keySet()) {
            Integer stock = map.get(barCode);
            try {
                callStmt = getConnection().prepareCall("{ call changeStockOnPharmacy(?,?,?) }");

                callStmt.setInt(1, stock);
                callStmt.setString(2, barCode);
                callStmt.setInt(3, pharmacyID);

                callStmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Change stock boolean.
     *
     * @param vehicleID the id of the Vehicle
     * @return the boolean
     */
    public boolean changeToCharging(Integer vehicleID) {
        CallableStatement callStmt;
        boolean flag = true;
            try {
                callStmt = getConnection().prepareCall("{ call changeVehicleToCharging(?) }");

                callStmt.setInt(1, vehicleID);

                callStmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        return flag;
    }

    /**
     * Take vehicle energy boolean.
     *
     * @param vehicleID   the vehicle id
     * @param totalEnergy the total energy
     * @param pharmacyID  the pharmacy id
     * @return the boolean
     */
    public boolean takeVehicleEnergy(int vehicleID, float totalEnergy, int pharmacyID) {
        CallableStatement callStmt;
        boolean flag = true;
        try {
            callStmt = getConnection().prepareCall("{ call TakeEnergyFromScooter(?,?,?) }");

            callStmt.setInt(1, vehicleID);
            callStmt.setFloat(2, totalEnergy);
            callStmt.setInt(3, pharmacyID);

            callStmt.execute();


            Logger.log("Vehicle with id " + vehicleID + " of pharmacy " + pharmacyID + " spent " + totalEnergy + " Wh");

        } catch (SQLException e) {
            System.err.println("Insufficient energy to complete the tour!");
            flag = false;

        }
        closeAll();
        return flag;
    }

    /**
     * Initiate points boolean.
     *
     * @param points the points
     * @return the boolean
     */
    public boolean initiatePoints(Map<String, MapPoint> points) {

        CallableStatement callStmt;

        try {
            for (String p : points.keySet()) {


                try {
                    callStmt = getConnection().prepareCall("{call initiateMapPoints(?,?,?) }");


                    callStmt.setString(1, p);
                    callStmt.setFloat(2, points.get(p).getAltitude());
                    callStmt.setString(3, points.get(p).getNome());


                    callStmt.execute();


                } catch (SQLException e) {
                    //nada
                }
            }

        } finally {
            closeAll();
        }

        return true;


    }

    /**
     * Change courier to available boolean.
     *
     * @param courierNIF the courier nif
     * @return the boolean
     */
    public boolean changeCourierToAvailable(int courierNIF) {
        CallableStatement callStmt;


        try {
            callStmt = getConnection().prepareCall("{call changeCourierToAvailable(?) }");


            callStmt.setInt(1, courierNIF);


            callStmt.execute();


        } catch (SQLException e) {
            return false;

        }

        return true;

    }

    /**
     * Change courier to available boolean.
     *
     * @param orderID the order id
     * @param pharmacyId the id of the pharmacy
     * @return the boolean
     */
    public boolean createPharmacyOrder(int orderID, int pharmacyId) {
        CallableStatement callStmt;


        try {
            callStmt = getConnection().prepareCall("{call createPharmacyOrder(?,?) }");


            callStmt.setInt(1, orderID);
            callStmt.setInt(2, pharmacyId);


            callStmt.execute();


        } catch (SQLException e) {
            return false;

        }

        return true;

    }


    /**
     * Gets available charging station.
     *
     * @param pharmacyID the pharmacy id
     * @param type       the type
     * @return the available charging station
     */
    public Integer getAvailableChargingStation(int pharmacyID, VehicleType type) {
        if (type.equals(VehicleType.SCOOTER)) {
            return getAvailableChargingStation(pharmacyID, 1);
        } else {
            return getAvailableChargingStation(pharmacyID, 2);
        }
    }

    private Integer getAvailableChargingStation(int pharmacyID, int tipoParque) {
        CallableStatement callStmt;

        Integer chargId;
        try {
            callStmt = getConnection().prepareCall("{? = call getAvailableChargingStation(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, pharmacyID);
            callStmt.setInt(3, tipoParque);


            callStmt.execute();

            chargId = (Integer) callStmt.getObject(1);


        } catch (SQLException e) {
            return null;

        }
        return chargId;
    }


    /**
     * Gets client email by order.
     *
     * @param orderID the order id
     * @return the client email by order
     */
    public String getClientEmailByOrder(int orderID) {
        CallableStatement callStmt;

        String email = null;
        try {
            callStmt = getConnection().prepareCall("{? = call getClientEmailByOrder(?) }");

            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            callStmt.setInt(2, orderID);

            callStmt.execute();

            email = (String) callStmt.getObject(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return email;
    }

    /**
     * Insert delivery integer.
     *
     * @param courierNIF the courier nif
     * @param vehicleID  the vehicle id
     * @param pharmacyID the pharmacy id
     * @param distance   the distance
     * @param time       the time
     * @param energyCost the energy cost
     * @return the integer
     */
    public Integer insertDelivery(Integer courierNIF, int vehicleID, Integer pharmacyID, float distance, float time, float energyCost) {

        CallableStatement callStmt;

        Integer idDelivery;
        try {
            callStmt = getConnection().prepareCall("{? = call insertDelivery(?,?,?,?,?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, courierNIF);
            callStmt.setInt(3, vehicleID);
            callStmt.setInt(4, pharmacyID);
            callStmt.setFloat(5, distance * 1000);
            callStmt.setFloat(6, time * 60);
            callStmt.setFloat(7, energyCost);


            callStmt.execute();

            idDelivery = (Integer) callStmt.getObject(1);


        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
        return idDelivery;
    }

    /**
     * Update orders delivery id boolean.
     *
     * @param pharmacyOrders the pharmacy orders
     * @param deliveryID     the delivery id
     * @return the boolean
     */
    public boolean updateOrdersDeliveryID(List<Integer> pharmacyOrders, int deliveryID) {

        CallableStatement callStmt;

        try {
            for (Integer orderID : pharmacyOrders) {


                try {
                    callStmt = getConnection().prepareCall("{call updateOrdersDeliveryID(?,?) }");


                    callStmt.setInt(1, orderID);
                    callStmt.setInt(2, deliveryID);


                    callStmt.execute();

                    Logger.log("Delivery ID " + deliveryID + " set on Order #" + orderID);


                } catch (SQLException e) {
                    //nada
                }
            }

        } finally {
            closeAll();
        }

        return true;


    }

    /**
     * Gets client cord by email.
     *
     * @param email the email
     * @return the client cord by email
     */
    public String getClientCordByEmail(String email) {

        CallableStatement callStmt;

        String cord;
        try {
            callStmt = getConnection().prepareCall("{? = call getClientCordByEmail(?)}");

            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);

            callStmt.setString(2, email);

            callStmt.execute();

            cord = (String) callStmt.getObject(1);


        } catch (SQLException e) {
            return null;

        }


        return cord;

    }

    /**
     * Gets cords of all pharmacys.
     *
     * @return the cords of all pharmacys
     */
    public Map<Integer, String> getCordsOfAllPharmacys() {

        CallableStatement callStmt;

        Map<Integer, String> lista = new HashMap<>();

        try {
            callStmt = getConnection().prepareCall("{? = call getCordsOfAllPharmacys()}");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);


            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int id = rSet.getInt(1);
                String coordenadas = rSet.getString(2);
                lista.put(id, coordenadas);
            }


        } catch (SQLException e) {
            return null;

        }
        return lista;

    }

    /**
     * Gets cords of pharmacys with available charging station.
     *
     * @param type the type
     * @return the cords of pharmacys with available charging station
     */
    public Map<Integer, String> getCordsOfPharmacysWithAvailableChargingStation(VehicleType type) {
        if (type.equals(VehicleType.SCOOTER)) {
            return getCordsOfPharmacysWithAvailableChargingStation(1);
        } else {
            return getCordsOfPharmacysWithAvailableChargingStation(2);
        }
    }

    private Map<Integer, String> getCordsOfPharmacysWithAvailableChargingStation(int tipo) {

        CallableStatement callStmt;

        Map<Integer, String> lista = new HashMap<>();

        try {
            callStmt = getConnection().prepareCall("{? = call getCordsOfPharmacysWithAvailableChargingStation(?)}");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, tipo);


            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int id = rSet.getInt(1);
                String coordenadas = rSet.getString(2);
                lista.put(id, coordenadas);
            }


        } catch (SQLException e) {
            return null;

        }
        return lista;

    }


    /**
     * Gets available drone.
     *
     * @param pharmacyID the pharmacy id
     * @param payload    the payload
     * @return the available drone
     */
    public Integer getAvailableDrone(int pharmacyID, float payload) {
        CallableStatement callStmt;
        Integer droneID = 0;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAvailableDrone(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, pharmacyID);
            callStmt.setFloat(3, payload);

            callStmt.execute();

            droneID = (Integer) callStmt.getObject(1);
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return droneID;
    }


    /**
     * Gets actual battery vehicle.
     *
     * @param scooterID  the scooter id
     * @param pharmacyID the pharmacy id
     * @return the actual battery vehicle
     */
    public float getActualBatteryVehicle(int scooterID, Integer pharmacyID) {

        CallableStatement callStmt;
        double actualBattery = 0;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getActualBatteryVehicle(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.FLOAT);
            callStmt.setInt(2, scooterID);
            callStmt.setInt(3, pharmacyID);


            callStmt.execute();

            actualBattery = (Double) callStmt.getObject(1);
            closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (float) actualBattery;
    }

    /**
     * Gets pesos por encomenda.
     *
     * @param pharmacyOrders the pharmacy orders
     * @return the pesos por encomenda
     */
    public Map<String, Float> getPesosPorEncomenda(List<Integer> pharmacyOrders) {

        CallableStatement callStmt;
        Map<String, Float> pesos = new HashMap<>();

        try {
            for (Integer orderID : pharmacyOrders) {


                try {
                    callStmt = getConnection().prepareCall("{call getPesosPorEncomenda(?,?,?) }");


                    callStmt.setInt(1, orderID);
                    callStmt.registerOutParameter(2, OracleTypes.VARCHAR);
                    callStmt.registerOutParameter(3, OracleTypes.FLOAT);

                    callStmt.execute();

                    String cord = (String) callStmt.getObject(2);
                    double peso = (Double) callStmt.getObject(3);

                    pesos.put(cord, (float) peso);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            closeAll();
        }

        return pesos;
    }

    /**
     * Update delivery boolean.
     *
     * @param gastoEnergeticoRestante the gasto energetico restante
     * @param tempoRestante           the tempo restante
     * @param distanciaRestante       the distancia restante
     * @param idDelivery              the id delivery
     * @return the boolean
     */
    public boolean updateDelivery(float gastoEnergeticoRestante, float tempoRestante, float distanciaRestante, int idDelivery) {
        CallableStatement callStmt;

        try {
            callStmt = getConnection().prepareCall("{ call updateDelivery(?,?,?,?) }");


            callStmt.setFloat(1, gastoEnergeticoRestante);
            callStmt.setFloat(2, tempoRestante * 60); //em minutos
            callStmt.setFloat(3, distanciaRestante * 1000); //em metros
            callStmt.setFloat(4, idDelivery);


            callStmt.execute();


            closeAll();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets battery by scooter id.
     *
     * @param scooterID  the scooter id
     * @param pharmacyID the pharmacy id
     * @return the battery by scooter id
     */
    public int getBatteryByScooterID(Integer scooterID, Integer pharmacyID) {
        CallableStatement callStmt;
        int battery = 0;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getBatteryInScooter(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, scooterID);
            callStmt.setInt(3, pharmacyID);

            callStmt.execute();


            battery = (Integer) callStmt.getObject(1);
            closeAll();
            return battery;

        } catch (SQLException e) {
            e.printStackTrace();
            return battery;
        }
    }

    /**
     * Gets battery capacity by scooter id.
     *
     * @param scooterID  the scooter id
     * @param pharmacyID the pharmacy id
     * @return the battery capacity by scooter id
     */
    public int getBatteryCapacityByScooterID(Integer scooterID, Integer pharmacyID) {
        CallableStatement callStmt;
        int batterycapacity = 0;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getBatteryCapacityInScooter(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, scooterID);
            callStmt.setInt(3, pharmacyID);

            callStmt.execute();


            batterycapacity = (Integer) callStmt.getObject(1);
            closeAll();
            return batterycapacity;

        } catch (SQLException e) {
            e.printStackTrace();
            return batterycapacity;
        }
    }

    /**
     * Gets voltage by pharmacy id (Works for Drone and Scooter).
     *
     * @param pharmacyID the pharmacy id
     * @return the voltage by pharmacy id
     */
    public int getVoltageByPharmacyID(Integer pharmacyID, VehicleType type) {
        CallableStatement callStmt;
        int voltage = 0;
        String call = "";
        if(type.equals(VehicleType.SCOOTER)) {
            call = "{ ? = call getVoltageInPark(?) }";
        } else {
            call = "{ ? = call getVoltageInParkDrone(?) }";
        }

        try {
            callStmt = getConnection().prepareCall(call);

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, pharmacyID);

            callStmt.execute();


            voltage = (Integer) callStmt.getObject(1);
            closeAll();
            return voltage;

        } catch (SQLException e) {
            e.printStackTrace();
            return voltage;
        }
    }

    /**
     * Gets courier email by courier nif.
     *
     * @param CourierNIF the courier nif
     * @return the courier email by courier nif
     */
    public String getCourierEmailByCourierNif(Integer CourierNIF) {
        CallableStatement callStmt;
        String email = "";

        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourierEmailByCourierNif(?) }");

            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, CourierNIF);

            callStmt.execute();


            email = (String) callStmt.getObject(1);
            closeAll();
            return email;

        } catch (SQLException e) {
            e.printStackTrace();
            return email;
        }
    }

    /**
     * Update order state to delivered boolean.
     *
     * @param deliveryID the delivery id
     * @return the boolean
     */
    public boolean updateOrderStateToDelivered(Integer deliveryID) {
        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{  call updateOrderStateToDelivered(?) }");

            callStmt.setInt(1, deliveryID);

            callStmt.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert callStmt != null;
                callStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean TrullyCharge(Integer VehicleID, Integer Percentual, Integer pharmacyID) {
        CallableStatement callStmt = null;

        try {
            callStmt = getConnection().prepareCall("{  call ChargeEnergyVehicle(?,?,?) }");

            callStmt.setInt(1, VehicleID);
            callStmt.setFloat(2, (float) Percentual);
            callStmt.setInt(3, pharmacyID);

            callStmt.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert callStmt != null;
                callStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
