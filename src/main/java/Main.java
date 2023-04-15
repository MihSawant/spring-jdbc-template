import config.ProjectConfig;
import entities.Employee;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.EmployeeDao;

public class Main {



    public static void main(String[] args) {
       var ctx = new AnnotationConfigApplicationContext(ProjectConfig.class);

       var employeeDao = (EmployeeDao) ctx.getBean("employeeDao");

       Employee e1 = new Employee();
       e1.setName("Mihir");
       e1.setDesignation("CTO");

       Employee e2 = new Employee();
       e2.setName("Yash");
       e2.setDesignation("Dev");

       Employee e3 = new Employee();
       e3.setName("Suresh");
       e3.setDesignation("Accountant");



       employeeDao.insertEmployee(e1);
       employeeDao.insertEmployee(e2);
       employeeDao.insertEmployee(e3);


       // update
        employeeDao.updateEmployee(2, "Sanket", "Tech");

        // delete
        employeeDao.deleteEmployee(3);

        // insert again
        employeeDao.insertEmployee(new Employee(0, "Azriel", "manager"));
       employeeDao.getEmployees().forEach(emp ->{
           System.out.println(emp);
       });







    }

}
