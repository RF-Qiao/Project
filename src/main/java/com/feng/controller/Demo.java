package com.feng.controller;

import com.feng.Util.JSONResult;
import com.feng.pojo.Employee;
import com.feng.service.EmpService;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "test", urlPatterns = "/test")
public class Demo extends HttpServlet {
    EmpService empService = new EmpServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String get = req.getParameter("get");
        if (get.equals("get")){
            findAll(req,resp);
        }else {
            deleteEmployee(req,resp);
        }
        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String post = req.getParameter("post");
        if (post.equals("post")){
            addEmployee(req,resp);
        }else {
            updateEmployee(req,resp);
        }
    }

    //查询所有客户
    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> allEmployee = empService.findAllEmployee();
        String json = new Gson().toJson(allEmployee);
        resp.getWriter().write(json);
    }
    //增加用户
    private void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        setEmployee(req,employee);
        empService.addEmployee(employee);
    }
    //修改用户
    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        setEmployee(req,employee);
        empService.updateEmployee(employee);
    }

    //删除用户
    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        empService.deleteEmployee(id);
    }

    /**
     * 获取前端数据信息
     * @param req
     * @param employee
     * @return
     */
    private  Employee setEmployee(HttpServletRequest req, Employee employee){
        employee.setId(Integer.parseInt(req.getParameter("id")));
        employee.setUsername(req.getParameter("username"));
        employee.setPassword(req.getParameter("password"));
        employee.setGender(Integer.parseInt(req.getParameter("gender")));
        employee.setMobile(req.getParameter("mobile"));
        employee.setBirthday(new Date());
        employee.setCreated_time(new Date());
        return employee;
    }
}
