package com.feng.controller;
import com.feng.pojo.Employee;
import com.feng.service.serviceimpl.EmpServiceImpl;
import com.feng.util.JSONResult;
import com.feng.util.TokenUtils;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Query", urlPatterns = "/query")
public class QueryController extends HttpServlet {
    EmpServiceImpl empService = new EmpServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        findAll(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String authorization = req.getHeader("Authorization");
        if (authorization==null){
            resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("没有登录，请先登录在查看信息!")));
            return;
        }
        String substring = authorization.substring(7);
        Integer verify = TokenUtils.verify(substring);
        if (verify == 1){
            List<Employee> allEmployee = empService.findAllEmployee();
            resp.getWriter().write(new Gson().toJson(new JSONResult(200,"查询成功",allEmployee)));
            return;
        }
        resp.getWriter().write(new Gson().toJson(JSONResult.errorMsg("权限不足")));
    }
}
