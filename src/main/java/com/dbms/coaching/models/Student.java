package com.dbms.coaching.models;

import java.sql.Date;

public class Student {
    private int studentId;
    private String gender;
    private Date dateOfBirth;
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String schoolAttending;
    private double percentage10th;
    private double percentage12th;
    private User user;
    private Guardian guardian;

    public Student() {
        user = new User();
        guardian = new Guardian();
    }

    public Student(int studentId, String gender, Date dateOfBirth, String houseNumber, String street, String city, String state, int pinCode, String schoolAttending, double percentage10th, double percentage12th, User user, Guardian guardian) {
        this.studentId = studentId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.schoolAttending = schoolAttending;
        this.percentage10th = percentage10th;
        this.percentage12th = percentage12th;
        this.user = user;
        this.guardian = guardian;
    }

    /**
     * @return int return the studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return Date return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return String return the houseNumber
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * @param houseNumber the houseNumber to set
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * @return String return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return String return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return int return the pinCode
     */
    public int getPinCode() {
        return pinCode;
    }

    /**
     * @param pinCode the pinCode to set
     */
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * @return String return the schoolAttending
     */
    public String getSchoolAttending() {
        return schoolAttending;
    }

    /**
     * @param schoolAttending the schoolAttending to set
     */
    public void setSchoolAttending(String schoolAttending) {
        this.schoolAttending = schoolAttending;
    }

    /**
     * @return double return the percentage10th
     */
    public double getPercentage10th() {
        return percentage10th;
    }

    /**
     * @param percentage10th the percentage10th to set
     */
    public void setPercentage10th(double percentage10th) {
        this.percentage10th = percentage10th;
    }

    /**
     * @return double return the percentage12th
     */
    public double getPercentage12th() {
        return percentage12th;
    }

    /**
     * @param percentage12th the percentage12th to set
     */
    public void setPercentage12th(double percentage12th) {
        this.percentage12th = percentage12th;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Guardian return the guardian
     */
    public Guardian getGuardian() {
        return guardian;
    }

    /**
     * @param guardian the guardian to set
     */
    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    @Override
    public String toString() {
        return "{" +
            " studentId='" + getStudentId() + "'" +
            ", gender='" + getGender() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", houseNumber='" + getHouseNumber() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", pinCode='" + getPinCode() + "'" +
            ", schoolAttending='" + getSchoolAttending() + "'" +
            ", percentage10th='" + getPercentage10th() + "'" +
            ", percentage12th='" + getPercentage12th() + "'" +
            ", user='" + getUser() + "'" +
            ", guardian='" + getGuardian() + "'" +
            "}";
    }

}
