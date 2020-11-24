package com.dbms.coaching.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.dbms.coaching.models.Attendance;

public interface AttendanceDao {
    public void save(Attendance attendance);

    public Attendance get(Date date, int employeeId);

    public List<Map<String, Object>> getAllEmployeeWisePresent();

    public List<Map<String, Object>> getAllEmployeeWiseAbsent();

    public List<Attendance> getAllByDate(Date date);

    public List<Attendance> getAllByDateForTeacher(Date date);

    public List<Attendance> getAllByDateForStaff(Date date);

    public List<Attendance> getAllByEmployeeId(int employeeId);

    public void update(Attendance attendance);

    public void delete(Date date, int employeeID);
}
