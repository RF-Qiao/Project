package com.feng.controller;

import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.JSONResult;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delete",urlPatterns = "/delete")
public class DeleteController extends HttpServlet {
    EmpServiceImpl empService= new EmpServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        deleteEmployee(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {

    }

    //删除用户
    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp
    ) throws IOException {
        String id = req.getParameter("id");
        int i = empService.deleteEmployee(Integer.parseInt(id));
        resp.getWriter().write(new Gson().toJson(new JSONResult(200, i == 0 ? "删除失败" : "删除成功", null)));
    }

}
