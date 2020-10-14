package com.dbms.coaching.models;

import java.sql.Date;

public class Teacher {
    private int teacherId;
    private String gender;
    private Date dateOfBirth;
    private int houseNumber;
    private String street;
    private String city;
    private int pinCode;
    private String bachelorsDegree;
    private String mastersDegree;
    private String doctoralDegree;
    private int employeeId;
    private int subjectId;

    /**
     * @return int return the teacherId
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
     * @return String return the bachelorsDegree
     */
    public String getBachelorsDegree() {
        return bachelorsDegree;
    }

    /**
     * @param bachelorsDegree the bachelorsDegree to set
     */
    public void setBachelorsDegree(String bachelorsDegree) {
        this.bachelorsDegree = bachelorsDegree;
    }

    /**
     * @return String return the mastersDegree
     */
    public String getMastersDegree() {
        return mastersDegree;
    }

    /**
     * @param mastersDegree the mastersDegree to set
     */
    public void setMastersDegree(String mastersDegree) {
        this.mastersDegree = mastersDegree;
    }

    /**
     * @return String return the doctoralDegree
     */
    public String getDoctoralDegree() {
        return doctoralDegree;
    }

    /**
     * @param doctoralDegree the doctoralDegree to set
     */
    public void setDoctoralDegree(String doctoralDegree) {
        this.doctoralDegree = doctoralDegree;
    }

    /**
     * @return int return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return int return the subjectId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

}