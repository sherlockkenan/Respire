package com.example.respireapp.Entity;

/**
 * Created by piglet on 2016/6/30.
 */


/**
 * An entity User composed by three fields (id, email, name). The Entity
 * annotation indicates that this class is a JPA entity. The Table annotation
 * specifies the name for the table in the db.
 *
 * @author respire
 */

public class User {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // An autogenerated id (unique for each user in the db)

    private String userid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private int sexid;
    private String role;
    private String city1;
    private String city2;
    private String city3;
    private String city4;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSexid() {
        return sexid;
    }

    public void setSexid(int sexid) {
        this.sexid = sexid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setCity1(String city1){this.city1=city1;}
    public String getCity1(){
        return city1;
    }
    public void setCity2(String city2){this.city2=city2;}
    public String getCity2(){
        return city2;
    }
    public void setCity3(String city3){this.city3=city3;}
    public String getCity3(){
        return city3;
    }
    public void setCity4(String city4){this.city4=city4;}
    public String getCity4(){
        return city4;
    }

}

