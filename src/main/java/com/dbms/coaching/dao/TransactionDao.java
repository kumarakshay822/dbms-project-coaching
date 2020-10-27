package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Transaction;

public interface TransactionDao {
    public Transaction save(Transaction transaction);

    public Transaction get(int transactionId);

    public List<Transaction> getAll();
}
