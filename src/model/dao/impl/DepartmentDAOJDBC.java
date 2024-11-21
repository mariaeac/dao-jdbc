package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOJDBC implements DepartmentDao {
    private Connection conn = null;

    public DepartmentDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("INSERT INTO department " + "(name) " + "VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, department.getName());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
            } else {
                throw new DBException("Error while creating a new department!");
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public void update(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("UPDATE department " + "SET name = ? " + "WHERE id = ?");
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
           int rowsAffected =  ps.executeUpdate();
           if (rowsAffected > 0) {
               rs = ps.getGeneratedKeys();
           }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    @Override
    public void deleteByID(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM department WHERE id = ?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Error while deleting a department!");
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Department department = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                department = instantiateDepartment(rs);
            }
            return department;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM department");
            rs = ps.executeQuery();
            List<Department> departments = new ArrayList<Department>();
            while (rs.next()) {
                Department department = instantiateDepartment(rs);
                departments.add(department);
            }
            return departments;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("id"));
        dep.setName(rs.getString("name"));
        return dep;
    }
}
