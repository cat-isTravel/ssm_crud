package ssm.service;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.bean.Employee;
import ssm.bean.EmployeeExample;
import ssm.dao.EmployeeMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("employeeService")
public class EmployeeService {

    @Autowired(required = false)
    EmployeeMapper employeeMapper;

    public List<Employee> getAll(){
        System.out.println(employeeMapper);
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        return employees;
    }

    public void addEmp(Employee employee){
        employeeMapper.insertSelective(employee);


    }

    public boolean checkLastName(String name){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        EmployeeExample.Criteria criteria1 = criteria.andLastNameEqualTo(name);
        long l = employeeMapper.countByExample(employeeExample);
        return l == 0;
    }

    public Employee selectById(Integer id){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(id);
        return employee;
    }

    public void updateEmp(Employee employee){
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public void deleteById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteSelectedById(List<Integer> in) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andIdIn(in);
        employeeMapper.deleteByExample(employeeExample);
    }
}
