package backingBean;

public class Roadmap {
    private int id;
    public String roadmapversion;
    private int requiredcredithour;
    private int degreeid;
    private int dsemesters;

    public Roadmap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoadmapversion() {
        return roadmapversion;
    }

    public void setRoadmapversion(String roadmapversion) {
        this.roadmapversion = roadmapversion;
    }

    public int getRequiredcredithour() {
        return requiredcredithour;
    }

    public void setRequiredcredithour(int requiredcredithour) {
        this.requiredcredithour = requiredcredithour;
    }

    public int getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(int degreeid) {
        this.degreeid = degreeid;
    }

    public int getDsemesters() {
        return dsemesters;
    }

    public void setDsemesters(int dsemesters) {
        this.dsemesters = dsemesters;
    }
}
