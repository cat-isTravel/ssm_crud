package ssm.Test;

import org.junit.jupiter.api.Test;
import ssm.bean.Employee;
import ssm.service.EmployeeService;

import java.util.List;

public class ServiceTest {

    EmployeeService employeeService = new EmployeeService();
    @Test
    public void empServiceTest(){
        List<Employee> employees = employeeService.getAll();
        System.out.println(employees);
    }
}
