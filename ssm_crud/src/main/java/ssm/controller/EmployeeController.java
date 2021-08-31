package ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import ssm.bean.Employee;
import ssm.bean.Msg;

import ssm.service.EmployeeService;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/")
    public String getAll(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        System.out.println(employeeService);
        System.out.println(pn);
        PageHelper.startPage(pn,8);
        List<Employee> employees = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(employees);
        model.addAttribute("pageInfo",pageInfo);
        return "index";

    }

    @ResponseBody
    @RequestMapping("/emp")
    public Msg getEmps(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
//        System.out.println(employeeService);
//        System.out.println(pn);
        Msg msg = new Msg();
        PageHelper.startPage(pn,8);
        List<Employee> employees = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(employees);
        model.addAttribute("pageInfo",pageInfo);
        return msg.add("pageInfo",pageInfo);

    }

    @ResponseBody
    @RequestMapping(value = "/empOperation",method = RequestMethod.POST)
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
//        System.out.println(employee);
        Map<String,Object> map = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
//        System.out.println(fieldErrors);
        if (result.hasErrors()){

            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError);
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }

            return Msg.fail().add("check",map);
        }
        else
        {
        employeeService.addEmp(employee);
        return Msg.success();
        }
    }
    @ResponseBody
    @RequestMapping(value = "/checkName",method = RequestMethod.POST)
    public Msg checkName(@RequestParam("empName") String name){
        //用户名校验正则表达式
        String regex = "^[a-zA-Z0-9_-]{6,16}$";
        if (!name.matches(regex)){
             return Msg.fail().add("regMsg", "用户名必须为6-16位的字母或者数字");
        }
        boolean b = employeeService.checkLastName(name);
        System.out.println(name);
        if (b){
            return Msg.success().add("regMsg", "用户名可用");
        }else {
            return Msg.fail().add("regMsg", "用户名已重复");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/empOperation/{id}",method = RequestMethod.GET)
    public Employee selectById(@PathVariable("id") Integer id){
        Employee employee = employeeService.selectById(id);
        return employee;
    }

    @ResponseBody
    @RequestMapping(value = "/empOperation/{id}",method = RequestMethod.PUT)
    public Msg updateEmp(Employee employee,@PathVariable("id")Integer id){
        employee.setId(id);
        System.out.println(employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping(value ="/empOperation/{ids}",method = RequestMethod.DELETE)
    public Msg deleteById(@PathVariable("ids") String ids){
        List<Integer> idList = new ArrayList<>();
        if (ids.contains("-")){
            String[] strings = ids.split("-");
            for (String string : strings) {
                idList.add(Integer.parseInt(string));
            }
            employeeService.deleteSelectedById(idList);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteById(id);
        }
        return Msg.success();
    }
}
