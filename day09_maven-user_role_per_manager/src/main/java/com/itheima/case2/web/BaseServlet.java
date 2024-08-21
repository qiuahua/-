package com.itheima.case2.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

//@WebServlet("/getServlet")
public class BaseServlet extends HttpServlet {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                //1.    获取请求志愿路径
                String uri = req.getRequestURI();
                //2.    先后去uri最后一个斜杆的索引
                int lastIndexOf = uri.lastIndexOf("/");

                //3根据上面后去的最后斜杆的索引截取最后一个斜杆的下一个字符，一直到字符串末尾
                String methodName = uri.substring(lastIndexOf + 1);

                //使用反射执行上述过多if语句问题
                //1.获取当前类的Class对象
                try {
                    Class<?> clazz = this.getClass();
                    //2.获取要执行的方法
                    Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                    //3.执行方法
                    method.invoke(this,req,resp);
//        Class<User1Servlet> user1ServletClass = User1Servlet.class;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}


