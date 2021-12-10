package com.feng.controller;

import com.feng.pojo.Employee;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.GetDataUtils;
import com.feng.util.JSONResult;
import com.feng.util.MD5;
import com.feng.util.TokenUtils;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login",urlPatterns = "/login")
public class LoginController extends HttpServlet {
    EmpServiceImpl empService= new EmpServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        login(req,resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String postData = GetDataUtils.getPostData(req);
        Employee employee = new Gson().fromJson(postData, Employee.class);
        String username = employee.getUsername();
        String password = MD5.md5(employee.getPassword());
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("用户名或密码为空")));
            return;
        }
        Integer employee1 = empService.userAndPasswordIsExist(username, password);
        System.out.println("=================="+employee1);
        if (employee1==0){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("用户名或密码不正确")));
            return;
        }
        Employee login = empService.login(username,password);
        String token = TokenUtils.token(login.getId(),login.getRole());
        resp.getWriter().write(new Gson().toJson(new JSONResult(200, "登录成功", token)));
    }
}