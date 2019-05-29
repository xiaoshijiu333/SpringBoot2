package com.fei.springboothigh.services;

import com.fei.springboothigh.config.log.Loggable;
import com.fei.springboothigh.entity.Employee;
import com.fei.springboothigh.mapper.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/5/27
 * @Description: 服务测试类
 */
@Service
public class HelloServices implements Loggable {

    /**
     * 缓存名称，namespace
     */
    private static final String HELLO_SERVICES = "helloservices";

    @Autowired
    private HelloMapper helloMapper;

    /**
     * 根据id获取employee
     * @Cacheable 在方法执行之前判断，缓存中是否有值，有则不执行方法了
     */
    @Cacheable(value = HELLO_SERVICES)
    public Employee getEmp(Integer empId) {
        getLog().error("查询来了。。");
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo(Employee.EMPLOYEE_ID, empId);
        List<Employee> employees = helloMapper.selectByExample(example);
        // 利用JDK8新特性简化判空操作（不可用，这里集合不是null，是长度为0）
        // return Optional.ofNullable(employees).map(list -> list.get(0)).orElse(null);
        if (CollectionUtils.isEmpty(employees)) {
            return null;
        } else {
            return employees.get(0);
        }
    }

    /**
     * 更新employee
     * @CachePut 方法先执行，再将方法的返回结果放到缓存中（方法没出异常） key：#employee.employeeId 取参数对象属性值
     */
    @CachePut(value = HELLO_SERVICES, key = "#employee.employeeId")
    public Employee update(Employee employee) {
        getLog().error("更新来了。。");
        helloMapper.updateByPrimaryKeySelective(employee);
        return employee;
    }

    /**
     * 模拟删除，清空所有缓存
     * @CacheEvict 默认在方法执行之后，清除指定缓存
     * @param id
     * @return Integer
     */
    @CacheEvict(value = HELLO_SERVICES, key = "#id")
    public Integer delete(Integer id) {
        getLog().error("删除来了。。");
        return id;
    }
}
