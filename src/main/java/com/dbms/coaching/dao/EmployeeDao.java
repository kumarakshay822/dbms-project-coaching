package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Employee;

public interface EmployeeDao {
    public Employee save(Employee employee);

    public List<Employee> getAll();

    public List<Employee> getAllTeachers();

    public List<Employee> getAllStaffs();

    public Employee get(int employeeId);

    public Integer getEmployeeIdByUserId(int userId);

    public String getRole(int employeeId);

    public void update(Employee employee);

    public void updateOwnProfile(Employee employee);

    public void delete(int employeeId);
}
