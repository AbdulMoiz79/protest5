package managedBean;

import backingBean.*;
import db.DbConnectionCourseCategory;
import db.DbConnectionCourses;
import db.DbConnectionViewRoadmap;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class ViewRoadmapBean {
    private CourseCategory courseCategory;
    private ViewRoadmap viewRoadmap;
    private Courses courses;
    private Roadmap roadmapEdited;
    private Degree degreeEdited;
    private ViewrmCourseCategory viewrmCourseCategory;
    private DbConnectionViewRoadmap dbConnection;
    private DbConnectionCourseCategory dbConnectionCC;
    private DbConnectionCourses dbConnectionCourses;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap3 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap4 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;

    public ViewRoadmapBean() {
        viewRoadmap=new ViewRoadmap();
        viewrmCourseCategory=new ViewrmCourseCategory();
        courseCategory = new CourseCategory();
        courses=new Courses();
        roadmapEdited=new Roadmap();
        dbConnection=new DbConnectionViewRoadmap();
        dbConnectionCC = new DbConnectionCourseCategory();
        dbConnectionCourses=new DbConnectionCourses();
        degreeEdited=new Degree();
    }
    public List<Integer> getSemesters(){
        List<Integer> semesters=new ArrayList<Integer>();
        roadmapEdited=(Roadmap) sessionMap2.get("updatedroadmap");
        int tsem=roadmapEdited.getDsemesters();
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

    public String openRoadMapCourseCategory(int s)
    {

        ViewRoadmap viewroadmapEdited = new ViewRoadmap();
        viewroadmapEdited.setSemester(s);
        sessionMap2.put("updatedviewroadmap", viewroadmapEdited);
        return "viewrmcoursecategory.xhtml?faces-redirect=true";

    }
    public String selectcourses(int s,String ccname,int cnum,String cc)
    {
        Courses courses1=new Courses();
        courses1.setCoursecode(cc);
        System.out.println("CC name"+ccname);
        sessionMap4.put("oldccode",courses1);
        roadmapEdited=(Roadmap) sessionMap2.get("updatedroadmap");
        ResultSet resultSet=dbConnectionCC.getidRecord(roadmapEdited.getId(),ccname);
        try {
            resultSet.next();
            CourseCategory courseCategory=new CourseCategory();
            courseCategory.setRmid(roadmapEdited.getId());
            courseCategory.setCategoryname(ccname);
            courseCategory.setId(resultSet.getInt("CCategoryID"));
            sessionMap.put("updatednewcc",courseCategory);
            ViewRoadmap viewRoadmap=new ViewRoadmap();
            viewRoadmap.setSemester(s);
            sessionMap3.put("updatedvrm",viewRoadmap);
        }catch (SQLException e){
            e.printStackTrace();
        }


        return "uniselectcourses.xhtml?faces-redirect=true";
    }
    public void deletecourse(String ccode)
    {
        dbConnectionCourses.unselectcourse(ccode);
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public CourseCategory getCourseCategoryobj() {
        return courseCategory;
    }

    public void setCourseCategoryobj(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

}


