package DBTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseTestThree {
    public static void main(String[] args) {

        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";


        try {
            // we need to establish the connection to the database
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection is established");
            // create a statement to send sql queries
            Statement statement = conn.createStatement();

            String query = "select * from person";
            ResultSet rSet = statement.executeQuery(query);
            ResultSetMetaData rsmData = rSet.getMetaData();

            // extract data from the result set and store it java data structure
            List<Map<String,String>> listFromSet = new ArrayList<>();
            // iterate through the rows
            while (rSet.next()){
                Map<String,String> map = new LinkedHashMap<>();
                // iterate through the columns
                for(int i = 1; i<=rsmData.getColumnCount(); i++){
                    // fetching key and value from the columns
                    String key = rsmData.getColumnName(i);
                    String value = rSet.getString(key);
                    map.put(key,value);
                }
                System.out.println(map);
                listFromSet.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
