package application;

import model.dao.DAOFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {


        SellerDao seller = DAOFactory.createSellerDAO();
        Seller sel = seller.selectByID(3);
        System.out.println(sel);
    }
}
