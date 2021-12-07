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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(name = "Add",urlPatterns = "/add")
public class AddController extends HttpServlet {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        if (authorization==null){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("没有登录，无法增加用户")));
            return;
        }
        String substring = authorization.substring(7);
        Integer verify = TokenUtils.verify(substring);
        String format1 =(format.format(new Date()));
        String format2 = format.format(TokenUtils.datadecode(substring));
        int i = format1.compareTo(format2);
        if (i>1){
            resp.getWriter().write(new Gson().toJson( JSONResult.errorMsg("token验证过期，请重新认证")));
            return;
        }
        if (verify==0){
           resp.getWriter().write(new Gson().toJson( JSONResult.errorMsg("权限不足")));
           return;
        }
        //增加用户信息
        String postData = GetDataUtils.getPostData(req);
        Employee employee1 =  new Gson().fromJson(postData, Employee.class);
        Employee employee2 = empService.userIsExist(employee1.getUsername());
        if (employee2!=null){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("用户名已经存在,无法增加用户")));
            return;
        }
        empService.addEmployee(employee1);
        resp.getWriter().write(new Gson().toJson(new JSONResult(200, "增加成功", null)));
    }
}