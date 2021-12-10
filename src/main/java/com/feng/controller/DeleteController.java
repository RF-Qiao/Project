package com.feng.controller;

import com.feng.service.serviceimpl.EmpServiceImpl;
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

@WebServlet(name = "delete",urlPatterns = "/delete")
public class DeleteController extends HttpServlet {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        if (req.getParameter("id")==null){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("id为空")));
            return;
        }
        String authorization = req.getHeader("Authorization");
        if (authorization==null){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("没有登录，无法删除用户")));
            return;
        }
        String substring = authorization.substring(7);
        Integer verify = TokenUtils.verify(substring);

        String format1 =format.format(new Date());
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
        String id = req.getParameter("id");
        int i1 = empService.deleteEmployee(Integer.parseInt(id));
        resp.getWriter().write(new Gson().toJson(new JSONResult(200, i1 == 0 ? "删除失败" : "删除成功", null)));
    }

}
