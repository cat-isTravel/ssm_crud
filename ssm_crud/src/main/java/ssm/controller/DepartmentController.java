package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.bean.Department;
import ssm.bean.Msg;
import ssm.service.DepartmentService;

import java.util.List;

@Controller
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/deptNameInfo")
    public Msg deptNameInfo(){
        Msg msg = new Msg();
        List<Department> departmentInfo = departmentService.getDepartmentInfo();
        return msg.add("departmentInfo",departmentInfo);
    }
}
