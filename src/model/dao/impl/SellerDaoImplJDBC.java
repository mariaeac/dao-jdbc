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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Department dep = instantiateDepartment(rs);
                Seller sel = instantiateSeller(rs, dep);

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
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
                                        +  "FROM seller INNER JOIN department "
                                        +  "ON seller.departmentId = department.Id "
                                        +  "WHERE department.Id = ?  "
                                        +  "ORDER BY name");
            st.setInt(1, department.getId());
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while(rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Seller sel = instantiateSeller(rs, dep);
                list.add(sel);
            }
            return list;

        } catch(SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller sel = new Seller();
        sel.setId(rs.getInt("id"));
        sel.setName(rs.getString("name"));
        sel.setEmail(rs.getString("email"));
        sel.setBaseSalary(rs.getDouble("baseSalary"));
        sel.setBirthDate(rs.getDate("birthDate"));
        sel.setDepartment(dep);
        return sel;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> selectAll() {
        return List.of();
    }


}
