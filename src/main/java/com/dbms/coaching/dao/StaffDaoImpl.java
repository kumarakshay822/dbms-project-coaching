package com.dbms.coaching.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dbms.coaching.models.Staff;
import com.dbms.coaching.utils.PreparedStatementUtil;
import com.dbms.coaching.dao.rowmappers.StaffRowMapper;

@Repository
public class StaffDaoImpl implements StaffDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public Staff save(Staff staff) {
        String sql = "INSERT INTO Staff (gender, dateOfBirth, houseNumber, street, city, state, pincode, employeeId) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"staffId"});
                preparedStatementUtil.setParameters(preparedStatement, staff.getGender(), staff.getDateOfBirth(),
                        staff.getHouseNumber(), staff.getStreet(), staff.getCity(), staff.getState(),
                        staff.getPinCode(), staff.getEmployee().getEmployeeId());
                return preparedStatement;
            }
        }, keyHolder);
        int staffId = keyHolder.getKey().intValue();
        staff.setStaffId(staffId);
        return staff;
    }

    @Override
    public List<Staff> getAll() {
        String sql = "SELECT * FROM Staff NATURAL JOIN Employee NATURAL JOIN User";
        List<Staff> staffs = template.query(sql, new StaffRowMapper());
        return staffs;
    }

    @Override
    public Staff getByEmployeeId(int employeeId) {
        try {
            String sql = "SELECT * FROM Staff NATURAL JOIN Employee NATURAL JOIN User WHERE employeeId = ?";
            return (Staff) template.queryForObject(sql, new Object[] { employeeId }, new StaffRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except staffId and employeeId
     */
    @Override
    public void update(Staff staff) {
        String sql = "UPDATE Staff SET gender = ?, dateOfBirth = ?, houseNumber = ?, street = ?, city = ?, state = ?, pinCode = ? "
                + "WHERE staffId = ?";
        template.update(sql, staff.getGender(), staff.getDateOfBirth(), staff.getHouseNumber(),
                staff.getStreet(), staff.getCity(), staff.getState(), staff.getPinCode(),
                staff.getStaffId());
    }

    @Override
    public void delete(int staffId) {
        String sql = "DELETE FROM Staff WHERE staffId = ?";
        template.update(sql, staffId);
    }

}
