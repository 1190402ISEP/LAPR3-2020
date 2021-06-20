package lapr.project.data;

import lapr.project.model.Park;
import lapr.project.model.ParkPlace;
import lapr.project.ui.Logger;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Park db.
 */
public class ParkDB extends DataHandler {
    
    private final String SQL_EXCEPTION = " SQL Exception -> ";

    /**
     * Create park boolean.
     *
     * @param park the park
     * @return the boolean
     */
    public boolean createPark ( Park park ) {
        boolean isCreated = false;

        try ( CallableStatement callStmt = getConnection().prepareCall("{ call createPark(?,?,?,?) }") ) {

            callStmt.setInt( 1, park.getTotalVoltage() );
            callStmt.setInt( 2, park.getMaxCapacity() );
            callStmt.setInt( 3, park.getPharmacyID());
            callStmt.setInt( 4, park.getParkType() );
            callStmt.execute();

            isCreated = true;

        } catch ( SQLException e ) {
            Logger.log( SQL_EXCEPTION + e.getErrorCode() );
        } finally {
            closeAll();
        }

        return isCreated;
    }

    /**
     * Update park boolean.
     *
     * @param park the park
     * @return the boolean
     */
    public boolean updatePark ( Park park ) {
        boolean isUpdated = false;

        try ( CallableStatement callStmt = getConnection().prepareCall("{ call updatePark(?,?,?,?,?,?) }") ) {

            callStmt.setInt( 1, park.getID() );
            callStmt.setInt( 2, park.getPharmacyID() );
            callStmt.setInt( 3, park.getMaxCapacity() );
            callStmt.setInt( 4, park.getCurrentOcupation () );
            callStmt.setInt( 5, park.getParkType() );
            callStmt.setInt( 6, park.getTotalVoltage() );
            callStmt.execute();

            isUpdated = true;

        } catch ( SQLException e ) {
            Logger.log( SQL_EXCEPTION + e.getErrorCode() );
        } finally {
            closeAll();
        }

        return isUpdated;
    }

    /**
     * Remove park boolean.
     *
     * @param parkID the park id
     * @return the boolean
     */
    public boolean removePark ( int parkID ) {
        boolean isDeleted = false;

        try ( CallableStatement callStmt = getConnection().prepareCall("{ call deletePark(?) }") ) {

            callStmt.setInt( 1, parkID );
            callStmt.execute();

            isDeleted = true;

        } catch ( SQLException e ) {
            Logger.log( SQL_EXCEPTION + e.getErrorCode() );
        } finally {
            closeAll();
        }

        return isDeleted;
    }

    /**
     * Show parks list.
     *
     * @return the list
     */
    public List<Park> showParks ( ) {
        List<Park> parkList = new ArrayList<>();

        try ( CallableStatement callStmt = getConnection().prepareCall("{ ? = call showParks() }") ) {

            callStmt.registerOutParameter( 1, OracleTypes.CURSOR );
            callStmt.execute();

            ResultSet results = (ResultSet) callStmt.getObject( 1 );

            while ( results.next() ){
                int id = results.getInt( 1 );
                int pharmacyId = results.getInt( 2 );
                int totalVoltage = results.getInt( 3 );
                int maxCapacity = results.getInt( 4 );
                int parkTypeId = results.getInt( 5 );
                int currentOccupation = results.getInt( 6 );

                Park park = new Park( pharmacyId, totalVoltage, maxCapacity, parkTypeId,  currentOccupation );
                park.setID( id );

                parkList.add( park );
            }

        } catch ( SQLException e ) {
            Logger.log( SQL_EXCEPTION + e.getErrorCode() );
        } finally {
            closeAll();
        }

        return parkList;
    }

    public boolean createPlace ( ParkPlace place, boolean isChargingStation ) {
        boolean isCreated = false;

        try ( CallableStatement callStmt = getConnection().prepareCall("{ call createPlace(?,?,?) }") ) {

            callStmt.setInt( 1, place.getParkId() );
            callStmt.setInt( 2, place.isAvailable() ? 1 : 0 );
            callStmt.setInt( 3, isChargingStation== true  ? 1:0 );
            callStmt.execute();

            isCreated = true;

        } catch ( SQLException e ) {
            Logger.log( SQL_EXCEPTION + e.getErrorCode() );
        } finally {
            closeAll();
        }

        return isCreated;
    }

}
