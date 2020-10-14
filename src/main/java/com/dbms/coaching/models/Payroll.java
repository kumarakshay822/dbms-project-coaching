package com.dbms.coaching.models;

import java.sql.Date;

public class Payroll {
    private String paymentRefNo;
    private String month;
    private int year;
    private double salaryCredited;
    private Date dateCredited;
    private String paymentMode;
    private int EmployeeId;

    /**
     * @return String return the paymentRefNo
     */
    public String getPaymentRefNo() {
        return paymentRefNo;
    }

    /**
     * @param paymentRefNo the paymentRefNo to set
     */
    public void setPaymentRefNo(String paymentRefNo) {
        this.paymentRefNo = paymentRefNo;
    }

    /**
     * @return String return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return int return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return double return the salaryCredited
     */
    public double getSalaryCredited() {
        return salaryCredited;
    }

    /**
     * @param salaryCredited the salaryCredited to set
     */
    public void setSalaryCredited(double salaryCredited) {
        this.salaryCredited = salaryCredited;
    }

    /**
     * @return Date return the dateCredited
     */
    public Date getDateCredited() {
        return dateCredited;
    }

    /**
     * @param dateCredited the dateCredited to set
     */
    public void setDateCredited(Date dateCredited) {
        this.dateCredited = dateCredited;
    }

    /**
     * @return String return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return int return the EmployeeId
     */
    public int getEmployeeId() {
        return EmployeeId;
    }

    /**
     * @param EmployeeId the EmployeeId to set
     */
    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

}