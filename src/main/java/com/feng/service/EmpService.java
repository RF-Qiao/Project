package com.feng.service;

import com.feng.pojo.Employee;

import java.util.List;
public interface EmpService {
    /**
     * 添加客户
     * @param employee
     * @return
     */
    int addEmployee(Employee employee);
    /**
     * 删除客户
     * @param id
     * @return
     */
    int deleteEmployee(int id);
    /**
     * 修改客户信息
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);
    /**
     * 查询所有客户
     * @return
     */
    List<Employee> findAllEmployee();

    /**
     * 创建用户
     * @param username
     * @param password
     * @return
     */
    Integer createEmployee(String username, String password);

    /**
     * 检测并登录信息
     * @param username
     * @param password
     * @return
     */
    Employee login(String username,String password);

    /**
     * 检测用户名是否存在
     * @param username
     * @return
     */
    Employee userIsExist(String username);
}
