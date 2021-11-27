package com.feng.dao;

import com.feng.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

    public interface EmployeeMapper {
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
        int deleteEmployee(Integer id);
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
         * 注册用户
         * @param employee
         * @return
         */
        Employee registEmployee(Employee employee);

        /**
         * 用户登录
         * @param username
         * @param password
         * @return
         */
        int login(@Param("username") String username,
                  @Param("password") String password);
    }