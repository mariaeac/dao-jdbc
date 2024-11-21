package model.dao.impl;

import model.dao.SellerDao;
import model.entities.Seller;

import java.util.List;

public class SellerDaoImplJDBC implements SellerDao {

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
        return null;
    }

    @Override
    public List<Seller> selectAll() {
        return List.of();
    }
}
