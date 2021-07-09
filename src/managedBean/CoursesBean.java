package managedBean;

import backingBean.*;
import db.DbConnectionCourses;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class CoursesBean {
    private Courses courses;
    private CourseCategory courseCategoryEdited;
    private DbConnectionCourses dbConnection;
    private ViewrmCourseCategory viewrmCourseCategory;
    private ViewRoadmap viewRoadmap;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap3 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;

    public CoursesBean() {
        courses = new Courses();
        viewrmCourseCategory=new ViewrmCourseCategory();
        courseCategoryEdited = new CourseCategory();
        viewRoadmap=new ViewRoadmap();
        dbConnection = new DbConnectionCourses();

    }

    public String registerUser() {


        courseCategoryEdited = (CourseCategory) sessionMap.get("updatedCourseCategory");
        int did = courseCategoryEdited.getId();



        String coursecode = courses.getCoursecode();
        String coursename = courses.getCoursename();
        int crehrs = courses.getCredithours();

        dbConnection.insertRecord(coursecode, coursename, crehrs, did);
        return "unicourses.xhtml?faces-redirect=true";
    }

    public List<Courses> getCourses() {

        List<Courses> courses = new ArrayList<Courses>();
        courseCategoryEdited = (CourseCategory) sessionMap.get("updatedCourseCategory");
        int did = courseCategoryEdited.getId();
        int coursenum = courseCategoryEdited.getTotalCourses();
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

    public List<Courses> selectCourses() {
        List<Courses> courses = new ArrayList<Courses>();
        courseCategoryEdited = (CourseCategory) sessionMap2.get("updatednewcc");
        int did = courseCategoryEdited.getId();

        ResultSet resultSet = dbConnection.getunselectedRecords(did);
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
    public String setselectedcourse(String ccode){
        Courses courses1=new Courses();
        courses1=(Courses) sessionMap3.get("oldccode");


        courseCategoryEdited = (CourseCategory) sessionMap2.get("updatednewcc");
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


    public Courses getCoursesobj() {
        return courses;
    }

    public void setCoursesobj(Courses courses) {
        this.courses = courses;
    }

}


