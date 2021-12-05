package com.feng.service.serviceimpl;

import com.feng.util.MybatisUtils;
import com.feng.dao.EmployeeMapper;
import com.feng.pojo.Employee;
import com.feng.service.EmpService;
import java.util.List;
public class EmpServiceImpl implements EmpService {
    EmployeeMapper mapper= MybatisUtils.getSqlSession().getMapper(EmployeeMapper.class);

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
    public Integer createEmployee(String username, String password) {
        return mapper.registEmployee(username,password);
    }

    @Override
    public Employee login(String username, String password) {

       return mapper.login(username,password);
    }

    @Override
    public Employee userIsExist(String username) {
       return mapper.usernameIsExist(username);
    }

    @Override
    public Employee userAndPasswordIsExist(String username, String password) {
        return mapper.userAndPasswordIsExist(username,password);
    }


}