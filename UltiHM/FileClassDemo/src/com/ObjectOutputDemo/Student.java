package com.ObjectOutputDemo;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String loginName;
    private String pwd;

    public Student(String name, String loginName, String pwd) {
        this.name = name;
        this.loginName = loginName;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
