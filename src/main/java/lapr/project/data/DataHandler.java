package lapr.project.data;



import java.sql.*;

/**
 * Exemplo de classe cujas instâncias manipulam dados de BD Oracle.
 */
public class DataHandler {
    /**
     * O URL da BD.
     */
    private String jdbcUrl;

    /**
     * O nome de utilizador da BD.
     */
    private  String username;

    /**
     * A password de utilizador da BD.
     */
    private  String password;

    /**
     * A ligação à BD.
     */
    private Connection connection;

    /**
     * A invocação de "stored procedures".
     */
    private CallableStatement callStmt;

    /**
     * Conjunto de resultados retornados por "stored procedures".
     */
    private ResultSet rSet;

    /**
     * Use connection properties set on file application.properties
     */
    public DataHandler() {
        setConnection();
    }

    /**
     * Sets connection.
     */
    public void setConnection() {
        this.jdbcUrl = System.getProperty("database.url");
        this.username = System.getProperty("database.username");
        this.password = System.getProperty("database.password");
    }

    /**
     * Estabelece a ligação à BD.
     */
    protected void openConnection() {
        try {
            connection = DriverManager.getConnection(
                    jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fecha os objetos "ResultSet", "CallableStatement" e "Connection", e
     * retorna uma mensagem de erro se alguma dessas operações não for bem
     * sucedida. Caso contrário retorna uma "string" vazia.
     */
    protected void closeAll() {

        StringBuilder message = new StringBuilder();

        if (rSet != null) {
            try {
                rSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            rSet = null;
        }

        if (callStmt != null) {
            try {
                callStmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            callStmt = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            connection = null;
        }
    }


    /**
     * Gets connection.
     *
     * @return the connection
     */
    protected Connection getConnection() {
        if (connection == null)
            openConnection();
        return connection;
    }


}
