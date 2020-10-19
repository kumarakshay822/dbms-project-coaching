package com.dbms.coaching.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dbms.coaching.models.Staff;
import com.dbms.coaching.models.User;

@Repository
public class StaffDaoImpl implements StaffDao {
    @Autowired
    JdbcTemplate template;

    // @Override
    // public void save(Staff staff, User user) {
    // String sql = "INSERT INTO Staff (staffId, gender, dateOfBirth, houseNumber,
    // street, city, state, pincode, schoolAttending, percentage10th, "
    // + "percentage12th, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // template.update(sql, staff.getStaffId(), staff.getGender(),
    // staff.getDateOfBirth(),
    // staff.getHouseNumber(), staff.getStreet(), staff.getCity(), staff.getState(),
    // staff.getPinCode(), staff.getSchoolAttending(), staff.getPercentage10th(),
    // staff.getPercentage12th(), user.getUserId());
    // }

    @Override
    public List<Staff> getAll() {
        String sql = "SELECT * FROM Staff NATURAL JOIN User";
        List<Staff> staffs = template.query(sql, new StaffRowMapper());
        return staffs;
    }

    @Override
    public Staff get(int staffId) {
        try {
            String sql = "SELECT * FROM Staff NATURAL JOIN User WHERE staffId = ?";
            return (Staff) template.queryForObject(sql, new Object[] { staffId }, new StaffRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Staff update(int staffId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Staff delete(int staffId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Staff staff, User user) {
        // TODO Auto-generated method stub

    }
}
