package model.dao;

import model.entities.Seller;
import java.util.List;
public interface SellerDao {

    void insert(Seller seller);
    void update(Seller seller);
    void deleteByID(Integer id);
    Seller selectByID(Integer id);
    List<Seller> selectAll();
}
