package com.feng.controller;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.JSONResult;
import com.feng.util.TokenUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "loginout",urlPatterns = "/loginout")
public class LoginOutController  extends HttpServlet {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorization = req.getHeader("Authorization");
        String substring = authorization.substring(7);
        Date datadecode = TokenUtils.datadecode(substring);
        String format = LoginOutController.format.format(new Date());
        String format1 = LoginOutController.format.format(datadecode);
        format1=format;

        System.out.println("登出时间==============="+format1);
        resp.getWriter().write(new Gson().toJson(new JSONResult(200,"等出成功",null)));
    }
}
