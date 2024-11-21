package model.dao;

import db.DB;
import model.dao.impl.SellerDaoImplJDBC;

public class DAOFactory {

    public static SellerDao createSellerDAO() {
        return new SellerDaoImplJDBC(DB.getConnection());
    }
}
