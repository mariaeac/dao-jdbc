package application;

import model.dao.DAOFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {


        SellerDao seller = DAOFactory.createSellerDAO();

        System.out.println("== TEST 1: Seller find by id === ");
        Seller sel = seller.selectByID(3);
        System.out.println(sel);
        System.out.println("== TEST 2: Seller find by id === ");
        Department dep = new Department(2, null);
        List<Seller> list = seller.findByDepartment(dep);

        for (Seller s : list) {
            System.out.println(s);
        }

        System.out.println("== TEST 3: Select All === ");
        List<Seller> sellers = seller.selectAll();
        for (Seller s : sellers) {
            System.out.println(s);
        }
    }
}
