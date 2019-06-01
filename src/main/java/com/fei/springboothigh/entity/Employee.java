package com.fei.springboothigh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/5/27
 * @Description: 员工实体类
 */
@Table(name = "employee")
@Getter
@Setter
public class Employee implements Serializable {

    private static final long serialVersionUID = 5336128105870854037L;

    /**
     * 定义常量字段
     */
    public final static String EMPLOYEE_ID = "employeeId";
    @Id
    private Integer employeeId;

    public final static String EMPLOYEE_NAME = "employeeName";
    private String employeeName;

    public final static String EMPLOYEE_SEX = "employeeSex";
    private Integer employeeSex;
}
