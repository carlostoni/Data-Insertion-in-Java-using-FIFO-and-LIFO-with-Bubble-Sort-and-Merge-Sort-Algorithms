package dao;
import java.sql.*;


public class DBConnection {

    private static final String URL_MYSQL = "jdbc:mysql://localhost/DadosInpe?useTimezone=true&serverTimezone=America/Sao_Paulo"; // caminho do banco
    private static final String DRIVER_CLASS_MYSQL = "com.mysql.cj.jdbc.Driver"; //driver para acesso 
    private static final String USER = "root"; // usuario banco
    private static final String PASS = "root"; // senha banco
    

   

    public static Connection getConnection() { // Iniciar a conexão
        System.out.println("Conectando ao Banco de Dados");
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() { // cria o banco se nao existir 
        Connection connection = getConnection();
        PreparedStatement stmt = null;

        String sql = "CREATE TABLE IF NOT EXISTS DadosInpe (" +
                "id bigint(20) NOT NULL AUTO_INCREMENT, " +
                "ano varchar(50) NOT NULL, " +
                "mes varchar(50) NOT NULL, " +
                "km varchar(50) NOT NULL, " +
                "PRIMARY KEY (id)" +
                ") ENGINE=InnoDB";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
}