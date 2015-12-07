
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
public class Faculty {
    
    public int FACULTY_ID;
    public String FACULTY_NAME;
    public int DEPARTMENT_ID;
    public String DEPARTMENT_NAME;
    
    public void setFacultyID(int facultyId){
        FACULTY_ID = facultyId;
    }
    
    public void setFacultyName(String facultyName){
        FACULTY_NAME = facultyName;
    }
    
    public void setDepartmentID(int departmentId){
        DEPARTMENT_ID = departmentId ;
    }
    
    public void setDepartmentName(String departmentName){
        DEPARTMENT_NAME = departmentName ;
    }
    
    public int getFacultyID(){
        return FACULTY_ID ;
    }
    
    public String getFacultyName(){
        return FACULTY_NAME ;
    }
    
    public int getDepartmentId(){
        return DEPARTMENT_ID ;
    }
    
    
     public String getDepartmentName(){
        return DEPARTMENT_NAME ;
    }
    
    public static Faculty getFaculty(int facultyId){
        String staffQuery = "select f.fid,f.fname,d.dname,f.did from faculty f, department d where"
                + " f.did = d.did and f.fid = " + facultyId ;
        ResultSet resultSet = PostgresSQLConnection.executeQuery(staffQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Faculty faculty = new Faculty();
                 faculty.setFacultyID(resultSet.getInt(1));
                 faculty.setFacultyName(resultSet.getString(2));
                 faculty.setDepartmentName(resultSet.getString(3));
                 faculty.setDepartmentID(resultSet.getInt(4));
                 return faculty;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Faculty getFacultybyName( String facNmae){
        String depQuery = "SELECT fid, fname from faculty where fname =  '" + facNmae +"'";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(depQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Faculty faculty = new Faculty();
                 faculty.setFacultyName(resultSet.getString(2));
                 faculty.setFacultyID(resultSet.getInt(1));
                 return faculty;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
       public static boolean save(Faculty faculty){
        
        String insertQuery = "INSERT INTO faculty(fid,fname,did)"
                + " values("+faculty.getFacultyID()+",'"+faculty.getFacultyName()+"',"+faculty.getDepartmentId()+")";
        return PostgresSQLConnection.executeUpdate(insertQuery);
    }
    
    public static boolean update(Faculty faculty){
        
        String updateQuery = "UPDATE faculty set fname = '"+faculty.getFacultyName()+"', "
                + "did = "+faculty.getDepartmentId()+" where fid = "+faculty.getFacultyID();
        
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
    public static boolean delete(Faculty faculty){
        String updateQuery = "DELETE FROM enrolled where cid in (select cid from courses where f_id = "+faculty.getFacultyID()+")";
        boolean status = PostgresSQLConnection.executeUpdate(updateQuery);
        updateQuery = " DELETE from courses where f_id = "+faculty.getFacultyID();
        status = status && PostgresSQLConnection.executeUpdate(updateQuery);
        updateQuery = " DELETE from faculty where fid = "+faculty.getFacultyID();
        status = status && PostgresSQLConnection.executeUpdate(updateQuery);
        
        return status;
    }
    
}
