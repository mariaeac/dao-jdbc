package model.dao;

import db.DB;
import model.dao.impl.DepartmentDAOJDBC;
import model.dao.impl.SellerDaoImplJDBC;

public class DAOFactory {

    public static SellerDao createSellerDAO() {
        return new SellerDaoImplJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDAO() {
        return new DepartmentDAOJDBC(DB.getConnection());
    }
}
