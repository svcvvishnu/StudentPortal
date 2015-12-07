
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sangav
 */
public class TestJDBC {
    
    public static void main(String[] args){
        try {
            
                Class.forName("com.mysql.jdbc.Driver");

                System.out.println("Connecting to database...");
                Connection con = DriverManager.getConnection("jdbc:mysql://dbserv.cs.siu.edu:1521/cs", "ngade", "iumuNE8p");
                   System.out.println("Instance successfully created");
                   Statement stmt = con.createStatement();
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: unable to load driver class!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
}
