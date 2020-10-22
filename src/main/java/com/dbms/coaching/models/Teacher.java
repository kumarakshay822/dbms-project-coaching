package com.dbms.coaching.models;

import java.sql.Date;

public class Teacher {
    private int teacherId;
    private String gender;
    private Date dateOfBirth;
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String bachelorsDegree;
    private String mastersDegree;
    private String doctoralDegree;
    private Employee employee;
    private Subject subject;

    public Teacher() {
        employee = new Employee();
        subject = new Subject();
    }

    public Teacher(int teacherId, String gender, Date dateOfBirth, String houseNumber, String street, String city, String state, int pinCode, String bachelorsDegree, String mastersDegree, String doctoralDegree, Employee employee, Subject subject) {
        this.teacherId = teacherId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.bachelorsDegree = bachelorsDegree;
        this.mastersDegree = mastersDegree;
        this.doctoralDegree = doctoralDegree;
        this.employee = employee;
        this.subject = subject;
    }

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
     * @return Employee return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employeeId the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return Subject return the subjectId
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "{" +
            " teacherId='" + getTeacherId() + "'" +
            ", gender='" + getGender() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", houseNumber='" + getHouseNumber() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", pinCode='" + getPinCode() + "'" +
            ", bachelorsDegree='" + getBachelorsDegree() + "'" +
            ", mastersDegree='" + getMastersDegree() + "'" +
            ", doctoralDegree='" + getDoctoralDegree() + "'" +
            ", employee='" + getEmployee() + "'" +
            ", subject='" + getSubject() + "'" +
            "}";
    }

}
