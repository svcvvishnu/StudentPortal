
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
public class Staff {
    
    public int STAFF_ID;
    public String STAFF_NAME;
    public int DEPARTMENT_ID;
    public String DEPARTMENT_NAME;
    
    public void setStaffID(int staffId){
        STAFF_ID = staffId;
    }
    
    public void setStaffName(String staffName){
        STAFF_NAME = staffName;
    }
    
    public void setDepartmentID(int departmentId){
        DEPARTMENT_ID = departmentId ;
    }
    
    public void setDepartmentName(String departmentName){
        DEPARTMENT_NAME = departmentName ;
    }
    
    public int getStaffID(){
        return STAFF_ID ;
    }
    
    public String getStaffName(){
        return STAFF_NAME ;
    }
    
    public int getDepartmentId(){
        return DEPARTMENT_ID ;
    }
    
     public String getDepartmentName(){
        return DEPARTMENT_NAME ;
    }
    
    public static Staff getStaff(int staffId){
        String staffQuery = "select s.sid,s.sname,d.dname,s.did from staff s, department d where s.did = d.did and s.sid = " + staffId ;
        ResultSet resultSet = PostgresSQLConnection.executeQuery(staffQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Staff staff = new Staff();
                 staff.setStaffID(resultSet.getInt(1));
                 staff.setStaffName(resultSet.getString(2));
                 staff.setDepartmentName(resultSet.getString(3));
                 staff.setDepartmentID(resultSet.getInt(4));
                 return staff;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean save(Staff staff){
        
        String insertQuery = "INSERT INTO Staff(sid,sname,did)"
                + " values("+staff.getStaffID()+",'"+staff.getStaffName()+"',"+staff.getDepartmentId()+")";
        return PostgresSQLConnection.executeUpdate(insertQuery);
    }
    
    public static boolean update(Staff staff){
        
        String updateQuery = "UPDATE Staff set sname = '"+staff.getStaffName()+"', "
                + "did = "+staff.getDepartmentId()+" where sid = "+staff.getStaffID();
        
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
    public static boolean delete(Staff staff){
        
        String updateQuery = " DELETE from Staff where sid = "+staff.getStaffID();
        
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
}
