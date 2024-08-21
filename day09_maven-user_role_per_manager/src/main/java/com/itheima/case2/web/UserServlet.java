package com.itheima.case2.web;


import com.itheima.case2.pojo.vo.*;
import com.itheima.case2.service.UserService;
import com.itheima.case2.utils.BaseController;
import com.itheima.case2.utils.BeansFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    public void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //1.获取请求参数封装到实体类
            AddUser addUser = BaseController.parseJSON2Object(req, AddUser.class);
            //获取业务层对象
            UserService userService = BeansFactory.getInstance("userService");
            //调用业务层方法
            userService.add(addUser);

            //创建Result放回
            Result result = new Result(true,"添加成功");

            //响应给前端
            BaseController.printResult(resp,result);

        } catch (Exception e) {

            //响应给前端
            try {
                //创建Result放回
                Result result = new Result(true,"添加成功");
                BaseController.printResult(resp,result);
            } catch (IOException ex) {

                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //获取删除的id
            String parameter = req.getParameter("uid");

            Integer uid = (Integer) Integer.parseInt(parameter);
            //获取业务层对象
            UserService userService = BeansFactory.getInstance("userService");

            //调用业务层方法
            userService.delete(uid);

            //创建result
            Result result = new Result(true,"删除成功");

            //响应给前端
            BaseController.printResult(resp,result);
        } catch (Exception e) {

            //响应给前端
            try {
                //创建result
                Result result = new Result(false,"删除失败");
                BaseController.printResult(resp,result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) {

        try{
            //1.获取请求参数封装到实体类
            UpdateUser updateUser = BaseController.parseJSON2Object(req, UpdateUser.class);
            //2.后去业务层对象
            UserService userService = BeansFactory.getInstance("userService");
            //3.调用业务层更新方法
            userService.update(updateUser);
            //4.创建
            Result result =  new Result(true,"更新成功");
            //响应给前端
            BaseController.printResult(resp,result);

        }catch (Exception e){
            e.printStackTrace();

            //响应给前端
            try {
                //4.创建
                Result result =  new Result(false,"更新失败");
                BaseController.printResult(resp,result);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void findAll(HttpServletRequest req, HttpServletResponse resp) {

        //1.将前端提交的请求参数即当页和每页条数封装到实体类QueryPageBean对象中
        try {
            QueryPageBean pb = BaseController.parseJSON2Object(req, QueryPageBean.class);
            //2.创建业务层对象
            /*
                new的方法耦合度太高了
                解决方法：降低偶和度
                使用：放射+面向接口编程，+读取配置文件，+工厂设置模式等方式取代new
             */
            //实现左边解偶和
//            //使用反射和读取配置文件创建对象
//            ResourceBundle beans = ResourceBundle.getBundle("beans"); //参数beans表示要关联配置文件的名字，不能书写后缀
////
//            String classNamStr = beans.getString("userService");
//
//            //使用放射创建对象
//            Class<?> aClass = Class.forName(classNamStr);
//            UserService userService =(UserService) aClass.newInstance();
//
            //从SpringIOC工厂获取UserServiceImpl类对象

            UserService userService = BeansFactory.getInstance("userService");

            //使用对象调用方法
            PageResult pageResult =  userService.findAll(pb);

            //创建Result对象
            Result result = new Result(true, "查询用户成功", pageResult);

            //将Result对象转为json给前端
            BaseController.printResult(resp,result);


        } catch (Exception e) {
            e.printStackTrace();
            try {
                //创建Result对象
                Result result = new Result(false, "查询用户失败");

                //将Result对象转为json给前端
                BaseController.printResult(resp,result);
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }

    }




}


