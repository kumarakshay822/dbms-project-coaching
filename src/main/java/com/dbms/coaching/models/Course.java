package com.dbms.coaching.models;

public class Course {
    private String courseId;
    private String courseName;
    private int fee;
    private String description;

    public Course() {
    }

    public Course(String courseId, String courseName, int fee, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.fee = fee;
        this.description = description;
    }

    public Course courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * @return String return the courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * @return String return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return int return the fee
     */
    public int getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(int fee) {
        this.fee = fee;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
            " courseId='" + getCourseId() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", fee='" + getFee() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}
