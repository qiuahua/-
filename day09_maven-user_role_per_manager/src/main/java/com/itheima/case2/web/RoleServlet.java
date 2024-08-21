package com.itheima.case2.web;


import com.itheima.case2.pojo.po.Role;
import com.itheima.case2.pojo.vo.Result;
import com.itheima.case2.service.RoleService;
import com.itheima.case2.utils.BaseController;
import com.itheima.case2.utils.BeansFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/role/*")
public class RoleServlet extends BaseServlet {

    //定义用户下拉框中显示角色的方法
    public void findAllRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取业务层对象

        try {
            RoleService roleService = BeansFactory.getInstance("roleService");

            //2.使用对象调用业务层方法
            List<Role> list =  roleService.findAllRoles();

            //3.将list封装到Result对象
            Result result = new Result(true, "查询结果成功", list);

            //4.使用工具类响应给前端
            BaseController.printResult(response,result);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                //3.将list封装到Result对象
                Result result = new Result(false, "查询结果不成功");

                //4.使用工具类响应给前端
                BaseController.printResult(response,result);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}


