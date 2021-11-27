package com.feng.service.serviceimpl;


import com.feng.Util.MybatisUtils;
import com.feng.dao.EmployeeMapper;
import com.feng.pojo.Employee;
import com.feng.service.EmpService;

import java.util.Date;
import java.util.List;
public class EmpServiceImpl implements EmpService {
    EmployeeMapper mapper= MybatisUtils.getSqlSession()
                                .getMapper(EmployeeMapper.class);

    @Override
    public int addEmployee(Employee employee) {
      return  mapper.addEmployee(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        return mapper.deleteEmployee(id);
    }

    @Override
    public int updateEmployee(Employee employee) {
       return mapper.updateEmployee(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return mapper.findAllEmployee();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(0);
        employee.setUsername(employee.getUsername());
        employee.setPassword(employee.getPassword());
        employee.setGender(0);
        employee.setMobile("123456");
        employee.setBirthday(null);
        employee.setCreated_time(new Date());
        return mapper.registEmployee(employee);
    }

    @Override
    public int login(String username, String password) {

       return mapper.login(username,password);
    }


}