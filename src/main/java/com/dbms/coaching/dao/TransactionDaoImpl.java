package com.dbms.coaching.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.dbms.coaching.models.Transaction;
import com.dbms.coaching.utils.DateTimeUtil;
import com.dbms.coaching.utils.PreparedStatementUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO Transaction (amount, date, time, transactionMode) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] { "transactionId" });
                preparedStatementUtil.setParameters(preparedStatement, transaction.getAmount(),
                        dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"), dateTimeUtil.getCurrentDateTime("HH:mm:ss"),
                        transaction.getTransactionMode());
                return preparedStatement;
            }
        }, keyHolder);
        int transactionId = keyHolder.getKey().intValue();
        transaction.setTransactionId(transactionId);
        return transaction;
    }

    @Override
    public Transaction get(int transactionId) {
        try {
            String sql = "SELECT * FROM Transaction WHERE transactionId = ?";
            Transaction transaction = template.queryForObject(sql, new Object[] { transactionId },
                    new BeanPropertyRowMapper<>(Transaction.class));
            return transaction;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Transaction> getAll() {
        String sql = "SELECT * FROM Transaction";
        List<Transaction> transactions = template.query(sql, new BeanPropertyRowMapper<>(Transaction.class));
        return transactions;
    }

}
