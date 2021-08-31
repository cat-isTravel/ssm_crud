package ssm.Test;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ssm.dao.EmployeeMapper;




public class CRUDtEST {

    @Autowired(required = false)
    EmployeeMapper employeeMapper;
    @Test
    public void crud(){
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");
//        DepartmentMapper departmentMapper = ioc.getBean(DepartmentMapper.class);
//        System.out.println(departmentMapper);
//        Department dept = new Department();
//        dept.setId(1);
//        dept.setDeptName("研发部");
//        int i = departmentMapper.updateByPrimaryKey(dept);
//        System.out.println(i);
//        EmployeeMapper employeeMapper = ioc.getBean(EmployeeMapper.class);
//        System.out.println(employeeMapper);
//        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(6);
//        System.out.println(employee);

//        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
//        SqlSession sqlSession = ioc.getBean(SqlSession.class);
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for (int i = 0; i < 500; i++) {
//            String name = UUID.randomUUID().toString();
//            mapper.insert(new Employee(null, name,"男",name+"@123.com",1));
//        }
        System.out.println(employeeMapper.selectByPrimaryKeyWithDept(6));

    }
}
