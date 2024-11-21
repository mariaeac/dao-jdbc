package application;

import model.dao.DAOFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DAOFactory.createDepartmentDAO();

    Scanner sc = new Scanner(System.in);

    System.out.println("Department");
    System.out.println("Select one option: ");
    System.out.println("1. Add Department");
    System.out.println("2. Delete Department");
    System.out.println("3. Edit Department");
    System.out.println("4. Display Department");
    System.out.println("5. Display All Departments");
    System.out.println("0. Exit");
    Department department = null;

    switch (sc.nextInt()) {
        case 1:
            System.out.println("Enter department name: ");
            String name = sc.next();
            department = new Department(name);
            departmentDao.insert(department);
            System.out.println("Department added");
            break;
        case 2:
          System.out.println("Enter department ID: ");
          int id = sc.nextInt();
          departmentDao.deleteByID(id);
          System.out.println("Department deleted");
          break;
        case 3:
            System.out.println("Enter department ID: ");
            id = sc.nextInt();
            department = departmentDao.findById(id);
            System.out.println("Enter the new department name: ");
            String newName = sc.next();
            department.setName(newName);
            departmentDao.update(department);
            break;
        case 4:
            System.out.println("Enter department ID: ");
            id = sc.nextInt();
            department = departmentDao.findById(id);
            System.out.println("Department: " + department.getName());
            break;
        case 5:
            List<Department> deps = new ArrayList<>();
            deps = departmentDao.findAll();
            for (Department d : deps) {
                System.out.println("Department: " + d.getName());
            }

    }

}
}