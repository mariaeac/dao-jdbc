package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoImplJDBC implements SellerDao {

    private Connection conn = null;

    public SellerDaoImplJDBC (Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Seller seller) {

    }
    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteByID(Integer id) {

    }

    @Override
    public Seller selectByID(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department " + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.id = ? " );

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));

                Seller sel = new Seller();
                sel.setId(rs.getInt("id"));
                sel.setName(rs.getString("name"));
                sel.setEmail(rs.getString("email"));
                sel.setBaseSalary(rs.getDouble("baseSalary"));
                sel.setBirthDate(rs.getDate("birthDate"));
                sel.setDepartment(dep);
                return sel;
            }
            return null; // Vendedor nulo

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }


    }

    @Override
    public List<Seller> selectAll() {
        return List.of();
    }
}