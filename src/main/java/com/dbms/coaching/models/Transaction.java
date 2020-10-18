package com.dbms.coaching.models;

import java.sql.Date;
import java.sql.Time;

public class Transaction {
    private int transactionId;
    private String accountName;
    private int amount;
    private Date date;
    private Time time;
    private String transactionMode;
    private String remarks;
    private boolean isVerified;

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
     * @return String return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return int return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

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
     * @return Time return the time
     */
    public Time getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * @return String return the transactionMode
     */
    public String getTransactionMode() {
        return transactionMode;
    }

    /**
     * @param transactionMode the transactionMode to set
     */
    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
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

    /**
     * @return boolean return the isVerified
     */
    public boolean isIsVerified() {
        return isVerified;
    }

    /**
     * @param isVerified the isVerified to set
     */
    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

}
