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
         * @param username
         * @param password
         * @return
         */
        Integer registEmployee(@Param("username") String username,
                                  @Param("password") String password);
        /**
         * 用户登录
         * @param username
         * @param password
         * @return
         */
        Employee login(@Param("username") String username,
                       @Param("password") String password);

        /**
         * 判断用户名是否存在
         * @param username
         * @return
         */
        Employee usernameIsExist(String username);

        /**
         * 判断用户名和密码是否正确
         * @param username
         * @param password
         * @return
         */
        Employee userAndPasswordIsExist(@Param("username") String username,
                                        @Param("password") String password);
    }