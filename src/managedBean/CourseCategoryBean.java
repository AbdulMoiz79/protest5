package managedBean;

import backingBean.CourseCategory;
import backingBean.Degree;
import backingBean.Roadmap;
import db.DbConnectionCourseCategory;
import db.DbConnectionCourses;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class CourseCategoryBean {
    private CourseCategory courseCategory;

    private Roadmap roadmapEdited;
    private Degree degreeEdited;
    private DbConnectionCourseCategory dbConnection;
    private DbConnectionCourses dbConnectionCourses;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;
    private List<CourseCategory> courseCategories =new ArrayList<>();

    public CourseCategoryBean() {
        courseCategory = new CourseCategory();
        roadmapEdited=new Roadmap();
        degreeEdited=new Degree();
        dbConnection = new DbConnectionCourseCategory();
        dbConnectionCourses=new DbConnectionCourses();
    }
    public String registerUser() {

        degreeEdited=(Degree) sessionMap.get("updatedDegree");
        roadmapEdited=(Roadmap) sessionMap.get("updatedroadmap");
        int did=roadmapEdited.getId();
        String tname=roadmapEdited.getRoadmapversion();
        int degid=degreeEdited.getId();

        String categoryname = courseCategory.getCategoryname();
        int totalcourses = courseCategory.getTotalCourses();

        dbConnection.insertRecord(categoryname, totalcourses, did,"deg"+degid+"v"+tname);

        return "unicoursecategory.xhtml?faces-redirect=true";
    }

    public List<CourseCategory> getCourseCategory() {

        List<CourseCategory> courseCategories = new ArrayList<CourseCategory>();
        roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        int did=roadmapEdited.getId();
        int counttcrhr;
        int counttcrhrselected;
        ResultSet resultSet = dbConnection.getRecords(did);
        try {
            while (resultSet.next()) {
                CourseCategory courseCategory = new CourseCategory();
                courseCategory.setId(resultSet.getInt("CCategoryID"));
                courseCategory.setCategoryname(resultSet.getString("CategoryName"));
                courseCategory.setTotalCourses(resultSet.getInt("TotalCourses"));
                counttcrhr=0;

                ResultSet resultSet2=dbConnectionCourses.getTotalCredithours(courseCategory.getId());
                while (resultSet2.next()){
                    counttcrhr=counttcrhr+(resultSet2.getInt("courseCreditHour"));
                }

                counttcrhrselected=0;
                resultSet2=dbConnectionCourses.getTotalCredithoursselected(courseCategory.getId());
                while (resultSet2.next()){
                    counttcrhrselected=counttcrhrselected+(resultSet2.getInt("courseCreditHour"));
                }


                courseCategory.setTotalcrhr(counttcrhr);
                courseCategory.setTotalcrhrselected(counttcrhrselected);
                courseCategory.setRmid(resultSet.getInt("rmID"));
                courseCategories.add(courseCategory);
            }
        } catch (Exception e) {

        }
        return courseCategories;
    }

    public String delete(int id){

        degreeEdited=(Degree) sessionMap.get("updatedDegree");
        roadmapEdited=(Roadmap) sessionMap.get("updatedroadmap");
        String tname=roadmapEdited.getRoadmapversion();
        int degid=degreeEdited.getId();

        dbConnection.deleteRecord(id,"deg"+degid+"v"+tname);

        return "unicoursecategory.xhtml?faces-redirect=true";
    }

    public String opencoursecategory(int id){


        CourseCategory CourseCategoryEdited = new CourseCategory();

        try{

            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            CourseCategoryEdited = new CourseCategory();

            CourseCategoryEdited.setId(resultSet.getInt("CCategoryID"));
            CourseCategoryEdited.setCategoryname(resultSet.getString("CategoryName"));
            CourseCategoryEdited.setTotalCourses(resultSet.getInt("TotalCourses"));



        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedCourseCategory", CourseCategoryEdited);


        return "unicourses.xhtml?faces-redirect=true";
    }


    public String updateCourseCategory(CourseCategory updatedcoursecategory){

        CourseCategory oldcouse=new CourseCategory();
        degreeEdited=(Degree) sessionMap.get("updatedDegree");
        roadmapEdited=(Roadmap) sessionMap.get("updatedroadmap");
        String tname=roadmapEdited.getRoadmapversion();
        int degid=degreeEdited.getId();
        String tablename="deg"+degid+"v"+tname;
        oldcouse=(CourseCategory) sessionMap.get("oldcourseCategory");
        String oldcolname=oldcouse.getCategoryname();
        String ccategoryname = updatedcoursecategory.getCategoryname();
        int tcourses = updatedcoursecategory.getTotalCourses();

        dbConnection.updateRecord(updatedcoursecategory.getId(),ccategoryname,tcourses,oldcolname,tablename);
        return "unicoursecategory.xhtml?faces-redirect=true";

    }

    public String edit(int id){

        CourseCategory courseCategoryEdited = new CourseCategory();
        CourseCategory oldcourseCategory= new CourseCategory();

        try{

            ResultSet resultSet=dbConnection.getRecord(id);
            resultSet.next();
            courseCategoryEdited = new CourseCategory();
            oldcourseCategory=new CourseCategory();
            courseCategoryEdited.setId(resultSet.getInt("CCategoryID"));
            courseCategoryEdited.setCategoryname(resultSet.getString("CategoryName"));
            oldcourseCategory.setCategoryname(resultSet.getString("CategoryName"));
            courseCategoryEdited.setTotalCourses(resultSet.getInt("TotalCourses"));

        }catch(Exception e){
            e.printStackTrace();
        }

        sessionMap.put("updatedcourseCategory", courseCategoryEdited);
        sessionMap.put("oldcourseCategory", oldcourseCategory);

        return "editcoursecategory.xhtml?faces-redirect=true";
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


