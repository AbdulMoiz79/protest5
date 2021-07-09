package backingBean;

public class Degree {
    private int id;
    private String degreename;
    private String headname;
    private double duration;
    private int deptid;
    private int tsemesters;

    public int getTsemesters() {
        return tsemesters;
    }

    public void setTsemesters(int tsemesters) {
        this.tsemesters = tsemesters;
    }


    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Degree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String email) {
        this.degreename = email;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String password) {
        this.headname = password;
    }

    public int getDeptid() { return deptid; }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

}
