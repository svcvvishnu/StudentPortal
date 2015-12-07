import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Niroop
 */
public class ExecutionServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        
        JSONObject result = new JSONObject();
        String action = req.getParameter(ApplicationConstants.ACTION);
        
        System.out.println(ApplicationConstants.ACTION +" :" + action);
        
        switch(action){
            case ApplicationConstants.GET_STAFF:
                result = getAllStaff();
                break;
            case ApplicationConstants.GET_STUDENTS:
                result = getAllStudents();
                break;
            
            case ApplicationConstants.GET_MY_STUDENTS:
                result = getMyStudents(req.getParameter("fid"));
                break;
            
            case ApplicationConstants.GET_FACULTY:
                result = getAllFaculty();
                break;

            case ApplicationConstants.GET_COURSES:
                result = getAllCourses();
                break;
                
            case ApplicationConstants.GET_MY_COURSES:
                result = getMyCourses(req.getParameter("fid"));
                break;
                
            case ApplicationConstants.GET_MY_EN_COURSES:
                result = getMyEnCourses(req.getParameter("sid"));
                break;
                
            case ApplicationConstants.GET_ENROLLED_STUDENTS:
                result = getEnrolledStudents(req.getParameter("cid"));
                break;
                
            case ApplicationConstants.AUTHENTICATE:
                try {
                        result = authenticateLogin(new JSONObject(req.getParameter(ApplicationConstants.USER)));
                    } catch (JSONException ex) {
                        Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                break;
              
        }
         System.out.println("Result:"+ result);
        PrintWriter writer = response.getWriter();
        writer.write(result.toString());
        writer.close();
    
    }
  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {  
        
        JSONObject result = new JSONObject();
        String action = req.getParameter(ApplicationConstants.ACTION);
        
        System.out.println(ApplicationConstants.ACTION +" :" + action);
        try{
        switch (action){
            case ApplicationConstants.ENROLL:
                System.out.println(ApplicationConstants.ACTION +" :" + action);
                result = enroll(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.ADD_STAFF:
                System.out.println(ApplicationConstants.ACTION +" :" + action);
                result = addStaff(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.ADD_STUDENT:
                result = addStudent(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.ADD_FACULTY:
                result = addFaculty(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.ADD_COURSE:
                result = addCourse(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.UPDATE_STAFF:
                result = updateStaff(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.UPDATE_STUDENT:
                result = updateStudent(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.UPDATE_STUDENT_GRADES:
                result = updateStudentGrades(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.UPDATE_FACULTY:
                result = updateFaculty(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.UPDATE_COURSE:
                result = updateCourse(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.DELETE_STAFF:
                result = deleteStaff(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.DELETE_STUDENT:
                result = deleteStudent(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.DELETE_FACULTY:
                result = deleteFaculty(req.getParameter(ApplicationConstants.OBJECT));
                break;
            case ApplicationConstants.DELETE_COURSE:
                result = deleteCourse(req.getParameter(ApplicationConstants.OBJECT));
                break;
         }
        }catch(JSONException ex){
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex); 
        }

        System.out.println("Result:"+ result);
        PrintWriter writer = response.getWriter();
        writer.write(result.toString());
        writer.close();
    }

    private JSONObject addStaff(String staff) throws JSONException {
        JSONObject result = new JSONObject();
        System.out.println(staff);
        JSONObject StaffObject = new JSONObject(staff);
        Staff newStaff = new Staff();
        newStaff.setStaffID(StaffObject.getInt("sid"));
        newStaff.setStaffName(StaffObject.getString("sname"));
        newStaff.setDepartmentName(StaffObject.getString("dname"));
        newStaff.setDepartmentID(Department.getDepartmentbyName(newStaff.getDepartmentName()).getDepartmentID());
        result.put("success", Staff.save(newStaff));
        return result;
    }

    private JSONObject updateStaff(String staff) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject StaffObject = new JSONObject(staff);
        Staff newStaff = new Staff();
        newStaff.setStaffID(StaffObject.getInt("sid"));
        newStaff.setStaffName(StaffObject.getString("sname"));
        newStaff.setDepartmentName(StaffObject.getString("dname"));
        newStaff.setDepartmentID(Department.getDepartmentbyName(newStaff.getDepartmentName()).getDepartmentID());
        result.put("success", Staff.update(newStaff));
        return result;
    }

    private JSONObject deleteStaff(String staff) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject StaffObject = new JSONObject(staff);
        Staff newStaff = new Staff();
        newStaff.setStaffID(StaffObject.getInt("sid"));
        result.put("success", Staff.delete(newStaff));
        return result;
    }
    
    private JSONObject addStudent(String student) throws JSONException {
        System.out.println(student);
        JSONObject result = new JSONObject();
        JSONObject StudentObject = new JSONObject(student);
        Student newStudent = new Student();
        newStudent.setStudentID(StudentObject.getInt("sid"));
        newStudent.setStudentName(StudentObject.getString("sname"));
        newStudent.setMajor(StudentObject.getString("major"));
        newStudent.setStudentLevel(StudentObject.getString("sLevel"));
        newStudent.setAge(StudentObject.getInt("age"));
        result.put("success", Student.save(newStudent));
        return result;
    }

    private JSONObject addFaculty(String faculty) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject FacultyObject = new JSONObject(faculty);
        Faculty newFaculty = new Faculty();
        newFaculty.setFacultyID(FacultyObject.getInt("fid"));
        newFaculty.setFacultyName(FacultyObject.getString("fname"));
        newFaculty.setDepartmentName(FacultyObject.getString("dname"));
        newFaculty.setDepartmentID(Department.getDepartmentbyName(newFaculty.getDepartmentName()).getDepartmentID());
        result.put("success", Faculty.save(newFaculty));
        return result;
    }

    private JSONObject addCourse(String course) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject CourseObject = new JSONObject(course);
        Course newCourse = new Course();
        newCourse.setCourseId(CourseObject.getString("cid"));
        newCourse.setCourseName(CourseObject.getString("cname"));
        newCourse.setFacultyName(CourseObject.getString("fname"));
        newCourse.setFacultyId(Faculty.getFacultybyName(newCourse.getFacultyName()).getFacultyID());
        newCourse.setMeetsAt(CourseObject.getString("meetsAt"));
        newCourse.setRoom(CourseObject.getString("room"));
        newCourse.setCourseLimit(CourseObject.getInt("courseLimit"));
        result.put("success", Course.save(newCourse));
        return result;
    }

    private JSONObject updateStudent(String student) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject StudentObject = new JSONObject(student);
        Student newStudent = new Student();
        newStudent.setStudentID(StudentObject.getInt("sid"));
        newStudent.setStudentName(StudentObject.getString("sname"));
        newStudent.setMajor(StudentObject.getString("major"));
        newStudent.setStudentLevel(StudentObject.getString("sLevel"));
        newStudent.setAge(StudentObject.getInt("age"));
        result.put("success", Student.update(newStudent));
        return result;
    }

    private JSONObject updateFaculty(String faculty) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject FacultyObject = new JSONObject(faculty);
        Faculty newFaculty = new Faculty();
        newFaculty.setFacultyID(FacultyObject.getInt("fid"));
        newFaculty.setFacultyName(FacultyObject.getString("fname"));
        newFaculty.setDepartmentName(FacultyObject.getString("dname"));
        newFaculty.setDepartmentID(Department.getDepartmentbyName(newFaculty.getDepartmentName()).getDepartmentID());
        result.put("success", Faculty.update(newFaculty));
        return result;
    }

    private JSONObject updateCourse(String course) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject CourseObject = new JSONObject(course);
        Course newCourse = new Course();
        newCourse.setCourseId(CourseObject.getString("cid"));
        newCourse.setCourseName(CourseObject.getString("cname"));
        newCourse.setFacultyName(CourseObject.getString("fname"));
        newCourse.setFacultyId(Faculty.getFacultybyName(newCourse.getFacultyName()).getFacultyID());
        newCourse.setMeetsAt(CourseObject.getString("meetsAt"));
        newCourse.setRoom(CourseObject.getString("room"));
        newCourse.setCourseLimit(CourseObject.getInt("courseLimit"));
        result.put("success", Course.update(newCourse));
        return result;
    }

    private JSONObject deleteStudent(String student) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject StudentObject = new JSONObject(student);
        Student newStudent = new Student();
        newStudent.setStudentID(StudentObject.getInt("sid"));
        result.put("success", Student.delete(newStudent));
        return result;
    }

    private JSONObject deleteFaculty(String faculty) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject FacultyObject = new JSONObject(faculty);
        Faculty newFaculty = new Faculty();
        newFaculty.setFacultyID(FacultyObject.getInt("fid"));
        result.put("success", Faculty.delete(newFaculty));
        return result;
    }

    private JSONObject deleteCourse(String course) throws JSONException {
        
        JSONObject result = new JSONObject();
        JSONObject CourseObject = new JSONObject(course);
        Course newCourse = new Course();
        newCourse.setCourseId(CourseObject.getString("cid"));
        result.put("success", Course.delete(newCourse));
        return result;
    }

    private JSONObject getAllStaff() {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "select s.sid,s.sname,d.dname,s.did from staff s, department d where s.did = d.did";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("sid",resultSet.getString(1));
                user.put("sname",resultSet.getString(2));
                user.put("dname",resultSet.getString(3));
                user.put("did",resultSet.getString(4));
                userArray.put(user);
            }
            
            result.put("allStaff",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }
    
    private JSONObject getAllStudents() {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "SELECT  sid,SNAME , major, s_level,age FROM STUDENT";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("sid",resultSet.getString(1));
                user.put("sname",resultSet.getString(2));
                user.put("major",resultSet.getString(3));
                user.put("sLevel",resultSet.getString(4));
                user.put("age",resultSet.getString(5));
                userArray.put(user);
            }
            
            result.put("allStudents",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }
    
    private JSONObject getAllFaculty() {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "SELECT  f.FID , f.FNAME , f.DID, d.dname FROM FACULTY f , department d where f.did = d.did";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("fid",resultSet.getString(1));
                user.put("fname",resultSet.getString(2));
                user.put("did",resultSet.getString(3));
                user.put("dname",resultSet.getString(4));
                userArray.put(user);
            }
            
            result.put("allFaculty",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }
    
    private JSONObject getAllCourses() {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "select c.cid,c.cname,c.meets_at,c.room,f.fname, c.course_limit,f.fid, c.enrolled_count"
                    + " from courses c , faculty f where c.f_id = f.fid;";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("cid",resultSet.getString(1));
                user.put("cname",resultSet.getString(2));
                user.put("meetsAt",resultSet.getString(3));
                user.put("room",resultSet.getString(4));
                user.put("fname",resultSet.getString(5));
                user.put("courseLimit",resultSet.getInt(6));
                user.put("fid",resultSet.getInt(7));
                user.put("enrollCount",resultSet.getInt(8));
                userArray.put(user);
            }
            
            result.put("allCourses",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }

    private JSONObject authenticateLogin(JSONObject user) throws JSONException {
        JSONObject result = new JSONObject();
        System.out.println("user:" + user);
        result.put("isValid", false);
        switch(user.getString(ApplicationConstants.USER_TYPE)){
            case "Staff":
                if(Staff.getStaff(user.getInt("userId")) != null){
                   result.put("isValid", true);
                    System.out.println("Not null");
                }else{
                    System.out.println("Is NUll");
                }
                break;
            case "Student":
                if(Student.getStudent(user.getInt("userId")) != null){
                  result.put("isValid", true);  
                }
                break;
            case "Faculty":
                if(Faculty.getFaculty(user.getInt("userId")) != null){
                  result.put("isValid", true);  
                }
                break;
            default:
         }
        System.out.println("result:" + result);
        return result;
    }

    private JSONObject enroll(String stduentCourse) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject newObject = new JSONObject(stduentCourse);
        EnrollCourse enrollCourse = new EnrollCourse();
        enrollCourse.setStudentID(newObject.getInt("sid"));
        enrollCourse.setCourseID(newObject.getString("cid"));
        boolean status = EnrollCourse.save(enrollCourse);
        if(status){
            status = Course.incrementEnrolCount(enrollCourse.getCourseID());
        }
        result.put("success", status);
        return result;
    }

    private JSONObject getEnrolledStudents(String courseID) {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "select s.sid,s.SNAME , s.major, s.s_level, s.age from enrolled e, student s where e.sid = s.sid"
                    + " and e.cid = '"+courseID +"'";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("sid",resultSet.getString(1));
                user.put("sname",resultSet.getString(2));
                user.put("major",resultSet.getString(3));
                user.put("sLevel",resultSet.getString(4));
                user.put("age",resultSet.getString(5));
                userArray.put(user);
            }
            
            result.put("enrolledStudents",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }

    private JSONObject getMyCourses(String facultyId) {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "select c.cid,c.cname,c.meets_at,c.room,f.fname, c.course_limit,f.fid, c.enrolled_count"
                    + " from courses c , faculty f where c.f_id = f.fid and f.fid = " + facultyId;
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("cid",resultSet.getString(1));
                user.put("cname",resultSet.getString(2));
                user.put("meetsAt",resultSet.getString(3));
                user.put("room",resultSet.getString(4));
                user.put("fname",resultSet.getString(5));
                user.put("courseLimit",resultSet.getInt(6));
                user.put("fid",resultSet.getInt(7));
                user.put("enrollCount",resultSet.getInt(8));
                userArray.put(user);
            }
            
            result.put("allMyCourses",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }
    private JSONObject getMyEnCourses(String studentId) {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "select c.cid,c.cname,c.meets_at,c.room,f.fname, c.course_limit,f.fid, c.enrolled_count"
                    + " from courses c , faculty f , enrolled e  where c.f_id = f.fid  and "
                    + " e.cid = c.cid and e.sid = " + studentId;
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("cid",resultSet.getString(1));
                user.put("cname",resultSet.getString(2));
                user.put("meetsAt",resultSet.getString(3));
                user.put("room",resultSet.getString(4));
                user.put("fname",resultSet.getString(5));
                user.put("courseLimit",resultSet.getInt(6));
                user.put("fid",resultSet.getInt(7));
                user.put("enrollCount",resultSet.getInt(8));
                userArray.put(user);
            }
            
            result.put("allMyCourses",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }

    private JSONObject updateStudentGrades(String stGrade) throws JSONException {
        JSONObject result = new JSONObject();
        JSONObject StudentGradeObject = new JSONObject(stGrade);
        EnrollCourse enCourse = new EnrollCourse();
        enCourse.setStudentID(Student.getStudentbyName(StudentGradeObject.getString("sname")).getStudentID());
        enCourse.setCourseID(Course.getCoursebyName(StudentGradeObject.getString("cname")).getCourseId());
        try{
        enCourse.setExam1(StudentGradeObject.getInt("exam1"));
        }catch(JSONException ex){
            
        } try{
        enCourse.setExam2(StudentGradeObject.getInt("exam2"));
        }catch(JSONException ex){
            
        } try{
        enCourse.setFinalExam(StudentGradeObject.getInt("final"));
        }catch(JSONException ex){
            
        } 
        result.put("success", EnrollCourse.update(enCourse));
        return result;
    }

    private JSONObject getMyStudents(String facultyId) {
        JSONObject result = new JSONObject();
        try {
            String selectQuery = "select s.sname, c.cname, e.exam1,e.exam2,e.final from student s ,"
                    + " courses c , enrolled e where e.sid = s.sid and e.cid = c.cid and c.f_id = " + facultyId;
        ResultSet resultSet = PostgresSQLConnection.executeQuery(selectQuery);
        JSONArray userArray = new JSONArray();
        assert resultSet != null;
            while(resultSet.next()){
                JSONObject user = new JSONObject();
                user.put("sname",resultSet.getString(1));
                user.put("cname",resultSet.getString(2));
                user.put("exam1",resultSet.getString(3));
                user.put("exam2",resultSet.getString(4));
                user.put("final",resultSet.getString(5));
                userArray.put(user);
            }
            
            result.put("allMyStudents",userArray);
            
        } catch (JSONException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(ExecutionServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(result);
        return result;
    }
}
