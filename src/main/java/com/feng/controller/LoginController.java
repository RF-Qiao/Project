package com.feng.controller;

import com.feng.pojo.Employee;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.GetDataUtils;
import com.feng.util.JSONResult;
import com.feng.util.TokenUtils;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login",urlPatterns = "/login")
public class LoginController extends HttpServlet {
    EmpServiceImpl empService= new EmpServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req,resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String postData = GetDataUtils.getPostData(req);
        Employee employee = new Gson().fromJson(postData, Employee.class);

        String username = employee.getUsername();
        String password = employee.getPassword();
        if (StringUtils.isBlank(username)){
            resp.getWriter().write(new Gson().toJson(new JSONResult("用户名为空")));
        }
        if (StringUtils.isBlank(password)) {
            resp.getWriter().write(new Gson().toJson(new JSONResult("密码为空")));
        }
        Employee login = empService.login(username, password);
        String token = TokenUtils.token(login.getId(), login.getRole());
        resp.getWriter().write(new Gson().toJson(new JSONResult(200, "登录成功", token)));
    }
}

