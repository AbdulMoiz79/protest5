package managedBean;

import backingBean.*;
import db.*;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class StudentSemBean {
    private CourseCategory courseCategory;
    private ViewRoadmap viewRoadmap;
    private Courses courses;
    private Roadmap roadmapEdited;
    private StudentSem studentSem;
    private Degree degreeEdited;
    private ViewrmCourseCategory viewrmCourseCategory;
    private DbConnectionViewRoadmap dbConnection;
    private DbConnectionCourseCategory dbConnectionCC;
    private DbConnectionCourses dbConnectionCourses;
    private DbConnectionStudentCourses dbConnectionStudentCourses;
    private DbConnectionStudentEnterRoadmap dbConnectionStudentEnterRoadmap;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;

    public StudentSemBean() {
        viewRoadmap=new ViewRoadmap();
        viewrmCourseCategory=new ViewrmCourseCategory();
        courseCategory = new CourseCategory();
        courses=new Courses();
        roadmapEdited=new Roadmap();
        dbConnection=new DbConnectionViewRoadmap();
        dbConnectionCC = new DbConnectionCourseCategory();
        dbConnectionCourses=new DbConnectionCourses();
        degreeEdited=new Degree();
        studentSem=new StudentSem();
        dbConnectionStudentCourses= new DbConnectionStudentCourses();
        dbConnectionStudentEnterRoadmap=new DbConnectionStudentEnterRoadmap();
    }
    public String setSemesters()
    {

        int stusem=studentSem.getStuSemester();
        StudentSem studentSem1=new StudentSem();
        studentSem1.setStuSemester(stusem);
        sessionMap.put("stusem",studentSem1);
        System.out.println("In set Semesters");

        dbConnectionStudentEnterRoadmap.makenewstudenttable();
        dbConnectionStudentCourses.unsetstudentselectcourse();
        return "Studententerroadmap.xhtml?faces-redirect=true";

    }
    public List<Integer> getSemesters(){
        List<Integer> semesters=new ArrayList<Integer>();
        StudentSem studentSem1=(StudentSem) sessionMap.get("stusem");
        int tsem=studentSem1.getStuSemester();
        int i=0;
        try {
            while (i<tsem){
                semesters.add((i+1));
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return semesters;
    }




    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public StudentSem getStudentSemobj() {
        return studentSem;
    }

    public void setStudentSemobj(StudentSem studentSem) {
        this.studentSem = studentSem;
    }
}


