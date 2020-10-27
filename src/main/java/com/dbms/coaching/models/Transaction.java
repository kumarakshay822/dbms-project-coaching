package com.dbms.coaching.models;

import java.sql.Date;
import java.sql.Time;

public class Transaction {
    private int transactionId;
    private int amount;
    private Date date;
    private Time time;
    private String transactionMode;

    public Transaction() {
    }

    public Transaction(int transactionId, int amount, Date date, Time time, String transactionMode) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.transactionMode = transactionMode;
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

    @Override
    public String toString() {
        return "{" +
            " transactionId='" + getTransactionId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", transactionMode='" + getTransactionMode() + "'" +
            "}";
    }

}
