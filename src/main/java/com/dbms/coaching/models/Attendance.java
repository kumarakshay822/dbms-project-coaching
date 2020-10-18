package com.dbms.coaching.models;

import java.sql.Date;

public class Attendance {
    private Date date;
    private int employeeId;
    private boolean isPresent;
    private String remarks;

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @return boolean return the isPresent
     */
    public boolean isIsPresent() {
        return isPresent;
    }

    /**
     * @param isPresent the isPresent to set
     */
    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    /**
     * @return String return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
