package managedBean;

import backingBean.*;
import db.DbConnectionCourseCategory;
import db.DbConnectionCourses;
import db.DbConnectionViewRoadmap;
import db.DbConnectionvrmCourseCategory;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class ViewrmCourseCategoryBean {
    private ViewRoadmap viewRoadmap;
    private ViewRoadmap viewRoadmapedited;
    private ViewrmCourseCategory viewrmCourseCategory;
    private ViewrmCourseCategory viewrmCourseCategoryedited;
    private CourseCategory courseCategory;
    private CourseCategoryBean courseCategoryBean;
    private CourseCategory courseCategoryEdited;
    private Roadmap roadmapEdited;
    private Degree degreeEdited;
    private Courses courses;
    private DbConnectionvrmCourseCategory dbConnection;
    private DbConnectionCourseCategory dbConnectionCC;
    private DbConnectionCourses dbConnectionCourses;
    private DbConnectionViewRoadmap dbConnectionViewRoadmap;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;
    private int counter=0;



    public ViewrmCourseCategoryBean() {
        dbConnectionViewRoadmap=new DbConnectionViewRoadmap();
        dbConnectionCourses=new DbConnectionCourses();
        viewRoadmapedited=new ViewRoadmap();
        viewrmCourseCategoryedited=new ViewrmCourseCategory();
        viewrmCourseCategory=new ViewrmCourseCategory();
        courseCategory = new CourseCategory();
        courseCategoryBean=new CourseCategoryBean();
        courseCategoryEdited=new CourseCategory();
        roadmapEdited=new Roadmap();
        dbConnection=new DbConnectionvrmCourseCategory();
        dbConnectionCC = new DbConnectionCourseCategory();
        viewRoadmap=new ViewRoadmap();
        degreeEdited=new Degree();
        courses=new Courses();
        roadmapEdited=new Roadmap();
    }
    public String registerUser()
    {
        return "";
    }

    public List<ViewrmCourseCategory> getViewrmCourseCategory()
    {
        List<ViewrmCourseCategory> viewrmCourseCategories=new ArrayList<ViewrmCourseCategory>();
        roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        degreeEdited=(Degree) sessionMap.get("updatedDegree");
        viewRoadmap=(ViewRoadmap) sessionMap.get("updatedviewroadmap");
        int did=roadmapEdited.getId();
        String tname=roadmapEdited.getRoadmapversion();
        ResultSet resultSet = dbConnectionCC.getRecords(did);
        try {
            while (resultSet.next()) {
                ResultSet resultSet2=dbConnection.getRecords(viewRoadmap.getSemester(),tname);
                resultSet2.next();
                ViewrmCourseCategory viewrmCourseCategory=new ViewrmCourseCategory();
                viewrmCourseCategory.setId(resultSet.getInt("CCategoryID"));
                viewrmCourseCategory.setViewrmccname(resultSet.getString("CategoryName").replace(" ","_"));
                viewrmCourseCategory.setCoursesnum(resultSet2.getInt(viewrmCourseCategory.getViewrmccname()));
                viewrmCourseCategories.add(viewrmCourseCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return viewrmCourseCategories;

    }

    public List<ViewrmCourseCategory> getallViewrmCourseCategory(int i)
    {
        List<ViewrmCourseCategory> viewrmCourseCategories=new ArrayList<ViewrmCourseCategory>();
        roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        int tsem=roadmapEdited.getDsemesters();
        degreeEdited=(Degree) sessionMap.get("updatedDegree");
        viewRoadmap=(ViewRoadmap) sessionMap.get("updatedviewroadmap");
        int did=roadmapEdited.getId();

        String tname=roadmapEdited.getRoadmapversion();

        ResultSet resultSet = dbConnectionCC.getRecords(did);
        try {
            while (resultSet.next() && i<=tsem && i>0) {


                ResultSet resultSet2=dbConnection.getRecords(i,tname);
                resultSet2.next();
                ViewrmCourseCategory viewrmCourseCategory = new ViewrmCourseCategory();
                viewrmCourseCategory.setId(resultSet.getInt("CCategoryID"));
                viewrmCourseCategory.setViewrmccname(resultSet.getString("CategoryName").replace(" ", "_"));
                viewrmCourseCategory.setCoursesnum(resultSet2.getInt(viewrmCourseCategory.getViewrmccname()));
                ResultSet resultSet3=dbConnectionCourses.getselectedRecords(viewrmCourseCategory.getId(),i);
                int j=0;
                while (j<viewrmCourseCategory.getCoursesnum()) {
                    ViewrmCourseCategory viewrmCourseCategory2 = new ViewrmCourseCategory();
                    if (resultSet3.next())
                    {
                        viewrmCourseCategory2.setViewrmccode(resultSet3.getString("courseCode"));
                        viewrmCourseCategory2.setViewrmcourse(resultSet3.getString("courseName"));
                        viewrmCourseCategory2.setViewrmccrhr(resultSet3.getInt("courseCreditHour"));
                        viewrmCourseCategory2.setViewrmcc(viewrmCourseCategory.getViewrmccname());
                        viewrmCourseCategory2.setViewrmccname(viewrmCourseCategory.getViewrmccname());
                        viewrmCourseCategory2.setCoursesnum(j + 1);
                        viewrmCourseCategories.add(viewrmCourseCategory2);
                    }
                    else {
                        viewrmCourseCategory2.setViewrmccode("No Code");
                        viewrmCourseCategory2.setViewrmcourse(viewrmCourseCategory.getViewrmccname()+"----"+(j+1));
                        viewrmCourseCategory2.setViewrmccrhr(0);
                        viewrmCourseCategory2.setViewrmcc(viewrmCourseCategory.getViewrmccname());
                        viewrmCourseCategory2.setViewrmccname(viewrmCourseCategory.getViewrmccname());
                        viewrmCourseCategory2.setCoursesnum(j + 1);
                        viewrmCourseCategories.add(viewrmCourseCategory2);
                    }
                    j++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        i++;
        return viewrmCourseCategories;

    }

    public String ccchanged(){
        roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        degreeEdited=(Degree) sessionMap.get("updatedDegree");
        viewRoadmap=(ViewRoadmap) sessionMap.get("updatedviewroadmap");
        viewrmCourseCategoryedited=(ViewrmCourseCategory) sessionMap.get("updatedvrmcc");
        int sem=viewRoadmap.getSemester();
        String col=viewrmCourseCategoryedited.getViewrmccname().replace(" ","_");
        String table=roadmapEdited.getRoadmapversion();
        int ccnum=viewrmCourseCategoryedited.getCoursesnum();
        int ccid=viewrmCourseCategoryedited.getId();
        dbConnection.updateRecord(sem,col,table,ccnum,ccid);



        return "viewrmcoursecategory.xhtml?faces-redirect=true";
    }
    public String addmoreSubjects(int ccid,String cc)
    {
        cc=cc.replace(" ","_");
        roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        degreeEdited=(Degree) sessionMap.get("updatedDegree");
        viewRoadmap=(ViewRoadmap) sessionMap.get("updatedviewroadmap");
        String tname=roadmapEdited.getRoadmapversion();
        ResultSet resultSet=dbConnection.getRecords(viewRoadmap.getSemester(),tname);
        try {
            resultSet.next();
            ViewrmCourseCategory viewrmCourseCategory=new ViewrmCourseCategory();
            viewrmCourseCategory.setViewrmccname(cc);
            viewrmCourseCategory.setId(ccid);
            viewrmCourseCategory.setCoursesnum(resultSet.getInt(viewrmCourseCategory.getViewrmccname()));

            sessionMap.put("updatedvrmcc",viewrmCourseCategory);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return "changevrmcc.xhtml?faces-redirect=true";
    }





    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public ViewrmCourseCategory getViewrmCourseCategoryobj() {
        return viewrmCourseCategory;
    }

    public void setViewrmCourseCategoryobj(ViewrmCourseCategory viewrmCourseCategory) {
        this.viewrmCourseCategory = viewrmCourseCategory;
    }
}


