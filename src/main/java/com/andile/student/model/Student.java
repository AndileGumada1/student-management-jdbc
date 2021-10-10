package com.andile.student.model;

import java.io.Serializable;
import java.util.Objects;

/**
* This is a Student model to be persistent in the database
**/
public class Student implements Serializable {

    private long id;
    private String name;
    private String address;
    private String mobile;
    private String email;

    public Student( String name, String address, String mobile, String email) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId() == student.getId() && Objects.equals(getName(), student.getName()) && Objects.equals(getAddress(), student.getAddress()) && Objects.equals(getMobile(), student.getMobile()) && Objects.equals(getEmail(), student.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getMobile(), getEmail());
    }
}
