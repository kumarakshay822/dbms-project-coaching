package com.dbms.coaching.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.dbms.coaching.models.Attendance;

public interface AttendanceDao {
    public void save(Attendance attendance);

    public Attendance get(Date date, int employeeId);

    public int getTotalDays();

    public List<Map<String, Object>> getAllEmployeeWise();

    public List<Attendance> getAllByDate(Date date);

    public List<Attendance> getAllByEmployeeId(int employeeId);

    public void update(Attendance attendance);

    public void delete(Date date, int employeeID);
}
