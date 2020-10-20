package com.dbms.coaching.dao;

import com.dbms.coaching.models.Guardian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GuardianDaoImpl implements GuardianDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(Guardian guardian) {
        String sql = "INSERT INTO Guardian (name, studentId, occupation, address, emailAddress, relationWithStudent) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, guardian.getName(), guardian.getStudentId(), guardian.getOccupation(),
                guardian.getAddress(), guardian.getEmailAddress(), guardian.getRelationWithStudent());
    }

    @Override
    public Guardian getByStudentId(int studentId) {
        try {
            String sql = "SELECT * FROM Guardian WHERE studentId = ?";
            Guardian guardian = template.queryForObject(sql, new Object[] { studentId },
                    new BeanPropertyRowMapper<>(Guardian.class));
            return guardian;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except studentId
     */
    @Override
    public void update(Guardian guardian) {
        String sql = "UPDATE Guardian SET name = ?, occupation = ?, address = ?, emailAddress = ?, relationWithStudent = ? WHERE studentId = ?";
        template.update(sql, guardian.getName(), guardian.getOccupation(), guardian.getAddress(),
                guardian.getEmailAddress(), guardian.getRelationWithStudent(), guardian.getStudentId());
    }

}
