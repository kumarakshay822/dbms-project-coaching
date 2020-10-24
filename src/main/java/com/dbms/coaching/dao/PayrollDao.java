package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Payroll;

public interface PayrollDao {
    public void save(Payroll payroll);

    public Payroll get(String paymentRefNo);

    public List<Payroll> getAll();

    public List<Payroll> getAllByEmployeeId(int employeeId);

    public List<Payroll> getAllByMonthYear(int month, int year);

    public void update(Payroll payroll);

    public void delete(String paymentRefNo);
}
