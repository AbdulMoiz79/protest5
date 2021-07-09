package backingBean;

public class User {
    private int id;
    private String username;
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validateUserLogin() {
        System.out.println("Entered Username is= " + username + ", password is= " + password);
        if (username.equalsIgnoreCase("admin") && password.equals("admin")) {
            return "unidept.xhtml?faces-redirect=true";
        } else {
            return "masterlogin.xhtml?faces-redirect=false";
        }
    }
}
