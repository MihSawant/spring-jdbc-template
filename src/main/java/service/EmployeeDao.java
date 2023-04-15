package service;


import entities.Employee;
import repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    private AtomicInteger id;

    private static final Logger log = LoggerFactory.getLogger(EmployeeDao.class.getName());
    public EmployeeDao(){
        this.id = new AtomicInteger();

    }

    public int getIncId(){
        return id.incrementAndGet();
    }

    public void insertEmployee(Employee employee){
        employeeRepository.getJdbcTemplate().update("INSERT INTO employees(id, name, designation) VALUES(?, ?, ?)", getIncId(), employee.getName(),

                employee.getDesignation());
        log.debug("******** Record Inserted Successfully !************");
    }

    public void updateEmployee(int id, String name, String designation){
        Integer checkIfExists = employeeRepository.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM employees WHERE id = ?", new Object[]{id}, Integer.class);
        if(checkIfExists != 1){
            log.error("Cannot Update, Record Does not exist with this ID !");
        } else{

            employeeRepository.getJdbcTemplate().update("UPDATE employees SET name = ?, designation = ? WHERE  id = ?", name, designation, id);
            log.debug("******** Record Updated Successfully !************");
        }

    }

    public void deleteEmployee(int id){
        Integer checkIfExists = employeeRepository.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM employees WHERE id = ?", new Object[]{id}, Integer.class);
        if(checkIfExists != 1){
            log.error("Cannot Delete, Record Does not exist with this ID !");
        } else{
            employeeRepository.getJdbcTemplate().update("DELETE FROM employees WHERE id = ?", id);
            log.debug("******** Record Deleted Successfully !************");
        }
    }

    public List<Employee> getEmployees(){
        List<Employee> employees = employeeRepository.getJdbcTemplate().query("SELECT * FROM employees", (rs, rowNum) -> {
                    return new Employee(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3));
                }
        );
        return employees;
    }





}
