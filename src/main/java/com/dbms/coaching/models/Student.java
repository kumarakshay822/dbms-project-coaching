package com.dbms.coaching.models;

import java.sql.Date;

public class Student {
    private int studentId;
    private String gender;
    private Date dateOfBirth;
    private int houseNumber;
    private String street;
    private String city;
    private int pinCode;
    private String schoolAttending;
    private double pencentage10th;
    private double pencentage12th;
    private int userId;

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
     * @return int return the houseNumber
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * @param houseNumber the houseNumber to set
     */
    public void setHouseNumber(int houseNumber) {
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
     * @return double return the pencentage10th
     */
    public double getPencentage10th() {
        return pencentage10th;
    }

    /**
     * @param pencentage10th the pencentage10th to set
     */
    public void setPencentage10th(double pencentage10th) {
        this.pencentage10th = pencentage10th;
    }

    /**
     * @return double return the pencentage12th
     */
    public double getPencentage12th() {
        return pencentage12th;
    }

    /**
     * @param pencentage12th the pencentage12th to set
     */
    public void setPencentage12th(double pencentage12th) {
        this.pencentage12th = pencentage12th;
    }

    /**
     * @return int return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}