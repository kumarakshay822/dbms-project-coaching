package com.dbms.coaching.models;

public class StudentTestDetails {
    private int studentId;
    private int testId;
    private int marksScored;
    private Boolean hasAppliedRecheck;
    private Boolean isDoneRecheck;
    private String recheckComments;

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
     * @return int return the testId
     */
    public int getTestId() {
        return testId;
    }

    /**
     * @param testId the testId to set
     */
    public void setTestId(int testId) {
        this.testId = testId;
    }

    /**
     * @return int return the marksScored
     */
    public int getMarksScored() {
        return marksScored;
    }

    /**
     * @param marksScored the marksScored to set
     */
    public void setMarksScored(int marksScored) {
        this.marksScored = marksScored;
    }

    /**
     * @return Boolean return the hasAppliedRecheck
     */
    public Boolean isHasAppliedRecheck() {
        return hasAppliedRecheck;
    }

    /**
     * @param hasAppliedRecheck the hasAppliedRecheck to set
     */
    public void setHasAppliedRecheck(Boolean hasAppliedRecheck) {
        this.hasAppliedRecheck = hasAppliedRecheck;
    }

    /**
     * @return Boolean return the isDoneRecheck
     */
    public Boolean isIsDoneRecheck() {
        return isDoneRecheck;
    }

    /**
     * @param isDoneRecheck the isDoneRecheck to set
     */
    public void setIsDoneRecheck(Boolean isDoneRecheck) {
        this.isDoneRecheck = isDoneRecheck;
    }

    /**
     * @return String return the recheckComments
     */
    public String getRecheckComments() {
        return recheckComments;
    }

    /**
     * @param recheckComments the recheckComments to set
     */
    public void setRecheckComments(String recheckComments) {
        this.recheckComments = recheckComments;
    }

}