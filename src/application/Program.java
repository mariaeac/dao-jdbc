package application;

import model.dao.DAOFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Department department = new Department(1, "Books");
        SellerDao seller = DAOFactory.createSellerDAO();
        System.out.println(department);
    }
}
