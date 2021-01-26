package App.Football.Models;

public class Team {
    private int Id;
    private String FullName;
    private String NickName;
    private String Initials;
    private String Logotype;
    private String Email;
    private Community community;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getInitials() {
        return Initials;
    }

    public void setInitials(String initials) {
        Initials = initials;
    }

    public String getLogotype() {
        return Logotype;
    }

    public void setLogotype(String logotype) {
        Logotype = logotype;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
