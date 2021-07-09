package backingBean;

public class CourseCategory {
    private int id;
    public String categoryname;
    private int totalCourses;
    private int totalcrhr;
    private int totalcrhrselected;
    private int rmid;

    public CourseCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(int totalCourses) {
        this.totalCourses = totalCourses;
    }

    public int getTotalcrhr() {
        return totalcrhr;
    }

    public void setTotalcrhr(int totalcrhr) {
        this.totalcrhr = totalcrhr;
    }

    public int getTotalcrhrselected() {
        return totalcrhrselected;
    }

    public void setTotalcrhrselected(int totalcrhrselected) {
        this.totalcrhrselected = totalcrhrselected;
    }

    public int getRmid() {
        return rmid;
    }

    public void setRmid(int rmid) {
        this.rmid = rmid;
    }
}
