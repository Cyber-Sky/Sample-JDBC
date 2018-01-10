import snaq.db.DBPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleInsert {


    public static void main(String args[]){
        try {




            //createTable();
            //insert(2,"Trial","Admin");
            selectAll();



        }catch (Exception e){
            System.out.println(e.toString());
        }finally{

        }
    }

    public static void createTable(){
        try {

            Statement statement = DatabaseConnection.getStatement();

            statement.execute("CREATE TABLE DEMO_TBL(" +
                    "USER_ID INT PRIMARY KEY NOT NULL," +
                    "USERNAME TEXT NOT NULL," +
                    "CREATED_BY TEXT NOT NULL," +
                    "CREATED_DATE DATE" +
                    ");");
        }catch (Exception e) {


        }finally {
            DatabaseConnection.closeStatement();
        }
    }

    public static void insert(int userid, String name, String created_by){

        try{

            Statement statement = DatabaseConnection.getStatement();

            statement.executeUpdate("INSERT INTO DEMO_TBL(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) "
                    + " VALUES ("+userid+",'"+name+"', '"+created_by+"', TO_TIMESTAMP('" + System.currentTimeMillis() + "'))");

        }catch (Exception e){

        }finally {
            DatabaseConnection.closeStatement();
        }

    }

    public static void selectAll(){

        try {
            Statement statement = DatabaseConnection.getStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM DEMO_TBL");
            while (rs.next()) {
                System.out.println("User Id: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Created By: " + rs.getString(3));
                System.out.println("Created Date: " + rs.getString(4));
                System.out.println();
            }
        }catch (Exception e){

        }finally {
            DatabaseConnection.closeStatement();
        }
    }

}
