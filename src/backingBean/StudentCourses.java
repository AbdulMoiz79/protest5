package backingBean;

public class StudentCourses {
    private int id;
    private String coursecode;
    private String coursename;
    private int credithours;
    private boolean selectunselect;
    private int selectsem;
    private int coursecategoryid;

    public StudentCourses() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getCoursecategoryid() {
        return coursecategoryid;
    }

    public void setCoursecategoryid(int coursecategoryid) {
        this.coursecategoryid = coursecategoryid;
    }

    public int getCredithours() {
        return credithours;
    }

    public void setCredithours(int credithours) {
        this.credithours = credithours;
    }

    public boolean isSelectunselect() {
        return selectunselect;
    }

    public void setSelectunselect(boolean selectunselect) {
        this.selectunselect = selectunselect;
    }

    public int getSelectsem() {
        return selectsem;
    }

    public void setSelectsem(int selectsem) {
        this.selectsem = selectsem;
    }
}
