package com.dbms.coaching.models;

import java.sql.Date;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int batchId;
    private int courseId;
    private int transactionId;
    private int testScore;
    private int percentageScholarship;
    private Date joinDate;
    private Date endDate;

    /**
     * @return int return the enrollmentId
     */
    public int getEnrollmentId() {
        return enrollmentId;
    }

    /**
     * @param enrollmentId the enrollmentId to set
     */
    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
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
     * @return int return the batchId
     */
    public int getBatchId() {
        return batchId;
    }

    /**
     * @param batchId the batchId to set
     */
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    /**
     * @return int return the courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * @return int return the transactionId
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return int return the testScore
     */
    public int getTestScore() {
        return testScore;
    }

    /**
     * @param testScore the testScore to set
     */
    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    /**
     * @return int return the percentageScholarship
     */
    public int getPercentageScholarship() {
        return percentageScholarship;
    }

    /**
     * @param percentageScholarship the percentageScholarship to set
     */
    public void setPercentageScholarship(int percentageScholarship) {
        this.percentageScholarship = percentageScholarship;
    }

    /**
     * @return Date return the joinDate
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate the joinDate to set
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * @return Date return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}