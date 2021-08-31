package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.Department;
import ssm.dao.DepartmentMapper;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getDepartmentInfo(){
        List<Department> departments = departmentMapper.selectByExample(null);
        return departments;
    }


}
