package App.Football.Models;

import java.sql.Date;

public class Player {
    private int Id;
    private String Dni;
    private String Name;
    private String LastName;
    private Date DateBirth;
    private String Photo;
    private String Phone;
    private String Email;
    private double Height;
    private double Weight;
    private String Foot;
    private Position position;
    private int State;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Date getDateBirth() {
        return DateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        DateBirth = dateBirth;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public String getFoot() {
        return Foot;
    }

    public void setFoot(String foot) {
        Foot = foot;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
