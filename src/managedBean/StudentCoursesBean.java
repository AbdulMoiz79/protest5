package managedBean;

import backingBean.*;
import db.DbConnectionCourses;
import db.DbConnectionStudentCourses;
import db.DbConnectionStudentEnterRoadmap;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class StudentCoursesBean {
    private StudentCourses studentCourses;
    private StudentCourseCategory studentcourseCategoryEdited;
    private DbConnectionStudentCourses dbConnection;
    private ViewrmCourseCategory viewrmCourseCategory;
    private ViewRoadmap viewRoadmap;
    private DbConnectionStudentEnterRoadmap dbConnectionStudentEnterRoadmap;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap3 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap4 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;

    public StudentCoursesBean() {
        studentCourses = new StudentCourses();
        viewrmCourseCategory=new ViewrmCourseCategory();
        studentcourseCategoryEdited = new StudentCourseCategory();
        viewRoadmap=new ViewRoadmap();
        dbConnectionStudentEnterRoadmap=new DbConnectionStudentEnterRoadmap();
        dbConnection = new DbConnectionStudentCourses();

    }


    public List<Courses> getStudentCourses() {

        List<Courses> courses = new ArrayList<Courses>();
        studentcourseCategoryEdited = (StudentCourseCategory) sessionMap.get("updatedCourseCategory2");
        int did = studentcourseCategoryEdited.getId();
        int coursenum = studentcourseCategoryEdited.getTotalCourses();
        ResultSet resultSet = dbConnection.getRecords(did);
        try {
            while (resultSet.next()) {
                Courses course = new Courses();
                course.setId(resultSet.getInt("courseID"));
                course.setCoursecode(resultSet.getString("courseCode"));
                course.setCoursename(resultSet.getString("courseName"));
                course.setCredithours(resultSet.getInt("courseCreditHour"));
                course.setSelectunselect(resultSet.getBoolean("courseSelected"));
                course.setSelectsem(resultSet.getInt("courseSelectedSem"));
                course.setCoursecategoryid(resultSet.getInt("CCategoryID"));
                courses.add(course);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public void selectCourses(int id,String ccode,String cname,int crhr) {
        System.out.println("Id="+ id);
        int s= (int) sessionMap4.get("semnum");
        System.out.println("semester="+ s);
        dbConnectionStudentEnterRoadmap.insertRecord(id,ccode,cname,s,crhr);
        dbConnection.setstudentselectcourse(ccode);

    }



    public String setselectedcourse(String ccode){
        Courses courses1=new Courses();
        courses1=(Courses) sessionMap3.get("oldccode");


        studentcourseCategoryEdited = (StudentCourseCategory) sessionMap2.get("updatednewcc");
        viewRoadmap=(ViewRoadmap) sessionMap2.get("updatedvrm");
        int sem=viewRoadmap.getSemester();
        String oldcode=courses1.getCoursecode();
        if(oldcode=="No Code"){
            dbConnection.setselectcourse(sem,ccode);
        }
        else{
            dbConnection.unselectcourse(oldcode);
            dbConnection.setselectcourse(sem,ccode);
        }

        return "uniselectcourses.xhtml?faces-redirect=true";
    }

    public String delete(int id) {
        dbConnection.deleteRecord(id);

        return "unicourses.xhtml?faces-redirect=true";
    }


    public String updatecourses(Courses updatedcourses) {


        String ccode = updatedcourses.getCoursecode();
        String cname = updatedcourses.getCoursename();
        int crhr = updatedcourses.getCredithours();

        dbConnection.updateRecord(updatedcourses.getId(), ccode, cname, crhr);
        return "unicourses.xhtml?faces-redirect=true";

    }

    public String edit(int id) {

        Courses coursesEdited = new Courses();

        try {

            ResultSet resultSet = dbConnection.getRecord(id);
            resultSet.next();
            coursesEdited = new Courses();

            coursesEdited.setId(resultSet.getInt("courseID"));
            coursesEdited.setCoursecode(resultSet.getString("courseCode"));
            coursesEdited.setCoursename(resultSet.getString("courseName"));
            coursesEdited.setCredithours(resultSet.getInt("courseCreditHour"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        sessionMap.put("updatedcourses", coursesEdited);


        return "editcourses.xhtml?faces-redirect=true";
    }




    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public StudentCourses getStudentCoursesobj() {
        return studentCourses;
    }

    public void setStudentCoursesobj(StudentCourses courses) {
        this.studentCourses = courses;
    }

}


