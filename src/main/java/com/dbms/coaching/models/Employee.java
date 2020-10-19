package com.dbms.coaching.models;

import java.sql.Date;

public class Employee {
    private int employeeId;
    private int basicSalary;
    private Date joinDate;
    private Date endDate;
    private String panNumber;
    private String accountNumber;
    private String bankName;
    private String bankBranch;
    private String ifscCode;
    private User user;

    public Employee() {
    }

    public Employee(int employeeId, int basicSalary, Date joinDate, Date endDate, String panNumber, String accountNumber, String bankName, String bankBranch, String ifscCode, User user) {
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.joinDate = joinDate;
        this.endDate = endDate;
        this.panNumber = panNumber;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.ifscCode = ifscCode;
        this.user = user;
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
     * @return int return the basicSalary
     */
    public int getBasicSalary() {
        return basicSalary;
    }

    /**
     * @param basicSalary the basicSalary to set
     */
    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
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

    /**
     * @return String return the panNumber
     */
    public String getPanNumber() {
        return panNumber;
    }

    /**
     * @param panNumber the panNumber to set
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    /**
     * @return String return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return String return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return String return the bankBranch
     */
    public String getBankBranch() {
        return bankBranch;
    }

    /**
     * @param bankBranch the bankBranch to set
     */
    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    /**
     * @return String return the ifscCode
     */
    public String getIfscCode() {
        return ifscCode;
    }

    /**
     * @param ifscCode the ifscCode to set
     */
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param userId the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
            " employeeId='" + getEmployeeId() + "'" +
            ", basicSalary='" + getBasicSalary() + "'" +
            ", joinDate='" + getJoinDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", panNumber='" + getPanNumber() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", bankBranch='" + getBankBranch() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

}
