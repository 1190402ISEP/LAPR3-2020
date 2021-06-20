package lapr.project.data;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Delete data db.
 */
public class DeleteDataDB extends DataHandler {

    /**
     * Delete data.
     *
     * @param queries the queries
     * @throws SQLException the sql exception
     */
    public void deleteData( List<String[]> queries) throws SQLException {
        for (String[] s : queries) {
            getConnection().createStatement().executeQuery(s[0]);

        }

        closeAll();

    }
}
