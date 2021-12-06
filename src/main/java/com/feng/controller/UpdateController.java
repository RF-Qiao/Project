package com.feng.controller;

import com.feng.pojo.Employee;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.GetDataUtils;
import com.feng.util.JSONResult;
import com.feng.util.TokenUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "update",urlPatterns = "/update")
public class UpdateController extends HttpServlet {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    EmpServiceImpl empService =new EmpServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateEmployee(req,resp);
    }
    //修改用户
    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        //验证是否为管理员 如果verify==1 可以增加
        String authorization = req.getHeader("Authorization");
        if (authorization==null){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("没有登录，无法修改用户")));
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
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("权限不足")));
            return;
        }
        String postData = GetDataUtils.getPostData(req);
        Employee employee1 = new Gson().fromJson(postData, Employee.class);
        int i1 = empService.updateEmployee(employee1);
        if (i1==0){
            resp.getWriter().write(new Gson().toJson( JSONResult.errorMsg("修改失败,该用户不存在")));
            return;
        }
        resp.getWriter().write(new Gson().toJson(new JSONResult(200,"修改成功",null)));
    }
}
