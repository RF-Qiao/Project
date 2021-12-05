package com.feng.controller;

import com.feng.pojo.Employee;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.GetDataUtils;
import com.feng.util.JSONResult;
import com.feng.util.MD5;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Passport", urlPatterns = "/passport")
public class PassportController extends HttpServlet {
    EmpServiceImpl empService = new EmpServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        regist(req, resp);
    }
    private void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String postData = GetDataUtils.getPostData(req);
        Employee employee = new Gson().fromJson(postData, Employee.class);
        Employee isExist = empService.userIsExist(employee.getUsername());
        if (isExist!=null){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("用户名已存在")));
            return;
        }
        String username = employee.getUsername();
        String password = MD5.md5(employee.getPassword());
        resp.getWriter().write(new Gson().toJson(new JSONResult(200, empService.createEmployee(username, password) > 0 ? "注册成功，马上登录" : "注册失败", null)));
    }
}
