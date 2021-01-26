package App.Football.Security;

public class UserInfo {
    private int Id;
    private String Username;
    private String Password;
    private int State;
    private int Role;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }
}
