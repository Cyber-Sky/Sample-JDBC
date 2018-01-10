import snaq.db.DBPoolDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private DatabaseConnection(){

    }

    private static Connection connection = null;
    private static Statement statement = null;
    private static DBPoolDataSource ds = null;


    public static Statement getStatement(){
        if(statement==null){
            synchronized(DatabaseConnection.class) {
                if(statement==null){
                    try{

                        //Class.forName("org.postgresql.Driver");
                        ds = new DBPoolDataSource();
                        ds.setName("pool-ds");
                        ds.setDescription("Pooling DataSource");
                        ds.setDriverClassName("org.postgresql.Driver");
                        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
                        ds.setUser("postgres");
                        ds.setPassword("demo");
                        ds.setMinPool(3);
                        ds.setMaxPool(3);
                        ds.setMaxSize(3);
                        ds.setIdleTimeout(3600);  // Specified in seconds.
                        try (Connection con = ds.getConnection())
                        {
                            if (con != null)
                            {
                                // use the connection
                                statement = con.createStatement();
                            }
                            else
                            {
                                // do something else (timeout occurred)
                            }
                        }
                        catch (SQLException ex)
                        {
                            System.out.println(ex.getMessage());
                            // deal with exception
                        }
                        //connection = DriverManager
                               // .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "demo");
                        //statement = connection.createStatement();
                    }catch (Exception e){
                        System.out.println("Error in Connection: "+e.toString());
                    }

                }
            }
        }
        return statement;
    }

    public static void closeStatement(){
        try{
            statement.close();
            connection.close();
        }catch (Exception e){
            System.out.println("Error in Connection: "+e.toString());
        }
    }
}
