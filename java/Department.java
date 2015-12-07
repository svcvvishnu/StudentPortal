
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Niroop
 */
public class Department {
    
    public int DEPARTMENT_ID;
    public String DEPARTMENT_NAME;
    
    public void setDepartmentID(int depId){
        DEPARTMENT_ID = depId;
    }
    
    public void setDepartmentName(String depName){
        DEPARTMENT_NAME = depName;
    }
    
    public int getDepartmentID(){
         return DEPARTMENT_ID ;
    }
    
    public String getDepartmentName(){
         return DEPARTMENT_NAME ;
    }
    
    public static Department getDepartmentbyName( String depName){
        String depQuery = "SELECT did, dname from department where dname =  '" + depName +"'";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(depQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Department department = new Department();
                 department.setDepartmentName(resultSet.getString(2));
                 department.setDepartmentID(resultSet.getInt(1));
                 return department;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
