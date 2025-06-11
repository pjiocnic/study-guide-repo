package com.example.model;

public class MyData {
    public String name;
    public int age;

    public boolean isSeniorCitizen;
    public String category;
    public String taxBracket;
    public boolean isEligibleForLoan;

    @Override
    public String toString() {
        return "MyData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isSeniorCitizen=" + isSeniorCitizen +
                ", category='" + category + '\'' +
                ", taxBracket='" + taxBracket + '\'' +
                ", isEligibleForLoan=" + isEligibleForLoan +
                '}';
    }
}
