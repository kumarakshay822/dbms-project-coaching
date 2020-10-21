package com.dbms.coaching.models;

public class Course {
    private String courseId;
    private String name;
    private int fee;
    private String description;


    public Course() {
    }

    public Course(String courseId, String name, int fee, String description) {
        this.courseId = courseId;
        this.name = name;
        this.fee = fee;
        this.description = description;
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
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
            ", name='" + getName() + "'" +
            ", fee='" + getFee() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}
