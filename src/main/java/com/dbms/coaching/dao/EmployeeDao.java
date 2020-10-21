package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Employee;

public interface EmployeeDao {
    public Employee save(Employee employee);

    public List<Employee> getAll();

    public Employee get(int employeeId);

    public void update(Employee employee);

    public void delete(int employeeId);
}
