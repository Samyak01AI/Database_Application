package com.example.databaseapplication;

public class UserData {
    int id;
    String name,email;
    int age;
    int imgid;

    public UserData(String name, String email, String age, int imgid) {
        this.name = name;
        this.email = email;
        this.age = Integer.parseInt(age);
        this.imgid = imgid;
    }
    public UserData(){
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getImgid() {
        return imgid;
    }
    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
