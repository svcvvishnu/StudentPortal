
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
public class EnrollCourse {
    
    public int STUDENT_ID;
    public String COURSE_ID;
    public int EXAM1;
    public int EXAM2;
    public int FINAL_EXAM;
    
    public void setStudentID(int studentId){
        STUDENT_ID = studentId;
    }
    
    public void setCourseID(String courseID){
        COURSE_ID = courseID;
    }
    
    public void setExam1(int exam1){
        EXAM1 = exam1 ;
    }
    
    public void setExam2(int exam2){
        EXAM2 = exam2 ;
    }
    
    public void setFinalExam(int finalExam){
        FINAL_EXAM = finalExam ;
    }
    
    public int getStudentID(){
       return  STUDENT_ID ;
    }
    
    public String getCourseID(){
        return COURSE_ID ;
    }
    
    public int getExam1(){
        return EXAM1  ;
    }
    
    public int getExam2(){
        return EXAM2 ;
    }
    
    public int getFinalExam(){
       return FINAL_EXAM  ;
    }
    
    
    public static boolean save(EnrollCourse enroll){
        
        String insertQuery = "INSERT INTO enrolled(sid,cid)"
                + " values('"+enroll.getStudentID()+"','"+enroll.getCourseID()+"')";
        return PostgresSQLConnection.executeUpdate(insertQuery);
    }
    
    public static boolean update(EnrollCourse enroll){
        
        String updateQuery = "UPDATE enrolled set exam1 = "+enroll.getExam1()+", "
                + "exam2 = "+enroll.getExam2()+" , final = "+enroll.getFinalExam()+" where sid = "+enroll.getStudentID()+" and "
                + "cid = '"+enroll.getCourseID()+"'";
        
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
    public static boolean delete(Student student){
        String updateQuery = "DELETE from enrolled where sid = "+student.getStudentID();
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
    public static boolean delete(Course course){
        String updateQuery = "DELETE from enrolled where cid = "+course.getCourseId();
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
}
