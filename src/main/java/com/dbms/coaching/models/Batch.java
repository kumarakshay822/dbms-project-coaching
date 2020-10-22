package com.dbms.coaching.models;

import java.sql.Time;

public class Batch {
    private String batchId;
    private Course course;
    private String batchName;
    private int roomNumber;
    private Time startTime;
    private Time endTime;

    public Batch() {
        course = new Course();
    }

    public Batch(String batchId, Course course, String batchName, int roomNumber, Time startTime, Time endTime) {
        this.batchId = batchId;
        this.course = course;
        this.batchName = batchName;
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return String return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId the batchId to set
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * @return Course return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * @return String return the batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * @param batchName the batchName to set
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * @return int return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return Time return the startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * @return Time return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "{" +
            " batchId='" + getBatchId() + "'" +
            ", course='" + getCourse() + "'" +
            ", batchName='" + getBatchName() + "'" +
            ", roomNumber='" + getRoomNumber() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }

}
