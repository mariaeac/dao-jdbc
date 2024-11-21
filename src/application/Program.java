package application;

import model.dao.DAOFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd/MM/yyyy");

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

        /** System.out.println("== TEST 4: Insert Seller: === ");
        String name = sc.nextLine();
        String email = sc.nextLine();
        Date birthDate = sdf.parse(sc.nextLine());
        Double baseSalary = sc.nextDouble();
        Integer departmentId = sc.nextInt();
        Department newSellerDep = map.get(departmentId);

            Seller newSeller = new Seller(baseSalary, birthDate, name, email, dep);
            seller.insert(newSeller);
            System.out.println(newSeller.getId()); **/

        System.out.println("== TEST 5: Update seller === ");
        sel = seller.selectByID(1);
        sel.setName("Mario Costa");
        seller.update(sel);
        System.out.println("Update completed!");

        System.out.println("== TEST 6: Delete seller === ");
        seller.deleteByID(1);


    }
}
