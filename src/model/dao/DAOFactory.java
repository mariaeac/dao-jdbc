package model.dao;

import model.dao.impl.SellerDaoImplJDBC;

public class DAOFactory {

    public static SellerDao createSellerDAO() {
        return new SellerDaoImplJDBC();
    }
}
