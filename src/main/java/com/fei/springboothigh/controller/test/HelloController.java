package com.fei.springboothigh.controller.test;

import com.fei.springboothigh.config.log.Loggable;
import com.fei.springboothigh.entity.Employee;
import com.fei.springboothigh.services.HelloServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/5/27
 * @Description: Controller测试
 */
@RestController
@RequestMapping("/emp")
public class HelloController implements Loggable {

    @Autowired
    private HelloServices helloServices;

    @GetMapping("/getById/{id}")
    public Employee getEmp(@PathVariable("id") Integer empId) {
        return helloServices.getEmp(empId);
    }

    @GetMapping("/update/{id}")
    public Employee update(@PathVariable("id") Integer id, Employee employee) {
        employee.setEmployeeId(id);
        return helloServices.update(employee);
    }

    @GetMapping("/delete/{id}")
    public Integer delete(@PathVariable("id") Integer id){
        return helloServices.delete(id);
    }

}
