package managedBean;

import backingBean.*;
import db.*;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class StudentEnterRoadmapBean {
    private ViewRoadmap viewRoadmap;
    private ViewRoadmap viewRoadmapedited;
    private ViewrmCourseCategory viewrmCourseCategory;
    private ViewrmCourseCategory viewrmCourseCategoryedited;
    private StudentCourseCategory courseCategory;
    private StudentCourseCategoryBean courseCategoryBean;
    private StudentCourseCategory courseCategoryEdited;
    private Roadmap roadmapEdited;
    private Courses courses;
    private Degree degreeEdited;
    private DbConnectionvrmCourseCategory dbConnection;
    private DbConnectionCourseCategory dbConnectionCC;
    private DbConnectionStudentEnterRoadmap dbConnectionStudentEnterRoadmap;
    private DbConnectionCourses dbConnectionCourses;
    private StudentEnterRoadmap studentEnterRoadmap;
    private DbConnectionViewRoadmap dbConnectionViewRoadmap;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;
    private int counter=0;



    public StudentEnterRoadmapBean() {
        studentEnterRoadmap=new StudentEnterRoadmap();
        dbConnectionViewRoadmap=new DbConnectionViewRoadmap();
        dbConnectionCourses=new DbConnectionCourses();
        viewRoadmapedited=new ViewRoadmap();
        viewrmCourseCategoryedited=new ViewrmCourseCategory();
        viewrmCourseCategory=new ViewrmCourseCategory();
        courseCategory = new StudentCourseCategory();
        courseCategoryBean=new StudentCourseCategoryBean();
        courseCategoryEdited=new StudentCourseCategory();
        roadmapEdited=new Roadmap();
        dbConnection=new DbConnectionvrmCourseCategory();
        dbConnectionCC = new DbConnectionCourseCategory();
        viewRoadmap=new ViewRoadmap();
        degreeEdited=new Degree();
        courses=new Courses();
        roadmapEdited=new Roadmap();
        dbConnectionStudentEnterRoadmap =new DbConnectionStudentEnterRoadmap();
    }
    public String addSubjects(int sem)
    {

        sessionMap2.put("semnum",sem);
        Roadmap roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        System.out.println("Roadmap name="+roadmapEdited.getRoadmapversion());
        System.out.println("Roadmap id="+roadmapEdited.getId());
        return "Studentcoursecategory.xhtml?faces-redirect=true";
    }

    public List<StudentCourses> getstudentrmcourses(int semester)
    {
        System.out.println("Semester"+semester);
        System.out.println(semester);
        List<StudentCourses> studentCourses=new ArrayList<StudentCourses>();
        ResultSet resultSet = dbConnectionStudentEnterRoadmap.getRecords(semester);
        try {
            while (resultSet.next()) {
                StudentCourses studentCourse=new StudentCourses();
                studentCourse.setCoursecode(resultSet.getString("coursecode"));
                studentCourse.setCoursename(resultSet.getString("course"));
                studentCourse.setSelectsem(resultSet.getInt("semester"));
                studentCourse.setCredithours(resultSet.getInt("credithour"));
                studentCourses.add(studentCourse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentCourses;

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public StudentEnterRoadmap getStudentEnterRoadmapobj() {
        return studentEnterRoadmap;
    }

    public void setStudentEnterRoadmapobj(StudentEnterRoadmap studentEnterRoadmap) {
        this.studentEnterRoadmap = studentEnterRoadmap;
    }
}


