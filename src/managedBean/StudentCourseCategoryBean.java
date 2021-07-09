package managedBean;

import backingBean.Degree;
import backingBean.Roadmap;
import backingBean.StudentCourseCategory;
import db.DbConnectionCourseCategory;
import db.DbConnectionCourses;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class StudentCourseCategoryBean {
    private StudentCourseCategory studentcourseCategory;

    private Roadmap roadmapEdited;
    private Degree degreeEdited;
    private DbConnectionCourseCategory dbConnection;
    private DbConnectionCourses dbConnectionCourses;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;
    private List<StudentCourseCategory> courseCategories =new ArrayList<>();

    public StudentCourseCategoryBean() {
        studentcourseCategory = new StudentCourseCategory();
        roadmapEdited=new Roadmap();
        degreeEdited=new Degree();
        dbConnection = new DbConnectionCourseCategory();
        dbConnectionCourses=new DbConnectionCourses();
    }

    public List<StudentCourseCategory> getStudentcourseCategory() {

        List<StudentCourseCategory> courseCategories = new ArrayList<StudentCourseCategory>();
        roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        int did=roadmapEdited.getId();
        int counttcrhr;
        int counttcrhrselected;
        ResultSet resultSet = dbConnection.getRecords(did);
        try {
            while (resultSet.next()) {
                StudentCourseCategory courseCategory = new StudentCourseCategory();
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



    public String opencoursecategory(int id){


        StudentCourseCategory CourseCategoryEdited = new StudentCourseCategory();

        try{

            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            CourseCategoryEdited = new StudentCourseCategory();

            CourseCategoryEdited.setId(resultSet.getInt("CCategoryID"));
            CourseCategoryEdited.setCategoryname(resultSet.getString("CategoryName"));
            CourseCategoryEdited.setTotalCourses(resultSet.getInt("TotalCourses"));



        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedCourseCategory2", CourseCategoryEdited);


        return "studentcourses.xhtml?faces-redirect=true";
    }








    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public StudentCourseCategory getStudentCourseCategoryobj() {
        return studentcourseCategory;
    }

    public void setStudentCourseCategoryobj(StudentCourseCategory courseCategory) {
        this.studentcourseCategory = courseCategory;
    }

}


