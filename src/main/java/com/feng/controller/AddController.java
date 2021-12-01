package com.feng.controller;

import com.feng.pojo.Employee;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.GetDataUtils;
import com.feng.util.JSONResult;
import com.feng.util.TokenUtils;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "Add",urlPatterns = "/add")
public class AddController extends HttpServlet {
    EmpServiceImpl empService = new EmpServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        addEmployee(req, resp);
    }

    //增加用户
    private void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //验证是否为管理员 如果verify==1 可以增加
        String authorization = req.getHeader("Authorization");
        Integer verify = TokenUtils.verify(authorization);
        if (verify==0){
           resp.getWriter().write(new Gson().toJson(new JSONResult(500,"权限不足",null)));
           return;
        }
        //增加用户信息
        String postData = GetDataUtils.getPostData(req);
        Employee employee1 =  new Gson().fromJson(postData, Employee.class);
        Employee employee = new Employee();
        employee.setUsername(employee1.getUsername());
        employee.setPassword(employee1.getPassword());
        employee.setGender((employee1.getGender()));
        employee.setMobile(employee1.getMobile());
        employee.setBirthday(new Date());
        employee.setCreated_time(new Date());
        empService.addEmployee(employee);
        resp.getWriter().write(new Gson().toJson(new JSONResult(200, "增加成功", null)));
    }


}