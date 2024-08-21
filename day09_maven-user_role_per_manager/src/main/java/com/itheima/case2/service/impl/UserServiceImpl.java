package com.itheima.case2.service.impl;

import com.itheima.case2.dao.UserMapper;
import com.itheima.case2.pojo.po.User;
import com.itheima.case2.pojo.vo.AddUser;
import com.itheima.case2.pojo.vo.PageResult;
import com.itheima.case2.pojo.vo.QueryPageBean;
import com.itheima.case2.pojo.vo.UpdateUser;
import com.itheima.case2.service.UserService;
import com.itheima.case2.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Comparator;
import java.util.List;

public class UserServiceImpl implements UserService {
    //分页查询用户
    @Override
    public PageResult findAll(QueryPageBean pb) {
        //2.后去mybatis会话对象
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        //使用接口代理对象调用接口分页查询用户方法

        //获取开始索引
        Integer offset = pb.getOffset();
        //获取每页条数
        Integer pageSize = pb.getPageSize();

        List<User>list =  mapper.findAll(offset,pageSize);

        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(Integer.parseInt(o1.getId()), Integer.parseInt(o2.getId()));
            }
        });
        //5.使用接口代理对象调用接口查询总记录数对象
        long total = mapper.findCount();
        //6.创建存储查询结果的实现实体类对象
        PageResult pageResult = new PageResult(total,list);

        //关闭会话
        session.close();

        //将结果放回给文本
        return pageResult;

    }

    //更新用户
    @Override
    public void update(UpdateUser updateUser) {

        //获取mybatis会话对象
        SqlSession session = SqlSessionUtil.getSession();
        //获取接口代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //使用接口代理对象调用接口中更新用户表t_user的数据
        mapper.update(updateUser);

        //使用接口代理对象调用接口中根据用户id到中间表t_user_role中删除用户数据
        mapper.deleteFromUserAndRoleTableByUID(updateUser.getId());

        //5.使用接口类代理对象调用接口中向中间表t_user_role中插入数据的方法
        mapper.addUIDAndRIDToUserAndRoleTable(updateUser.getId(),updateUser.getRoleIds());

        //释放资源
        session.close();


    }

    @Override
    public void delete(Integer uid) {
        //获取mybatis会话对象
        SqlSession session = SqlSessionUtil.getSession();
        //获取接口代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);

        //使用接口代理对象调用接口中根据用户id到中间表t_user_role中删除用户数据
        mapper.deleteFromUserAndRoleTableByUID(uid);

        //使用接口代理对象调用接口中更新用户表t_user的数据
        mapper.delete(uid);

    }

    @Override
    public void add(AddUser addUser) {
        //获取mybatis会话对象
        SqlSession session = SqlSessionUtil.getSession();
        //获取接口代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //使用接口代理对象调用接口中更新用户表t_user的数据
        mapper.add(addUser);

        //查询根据username查询用户的id
        int i = mapper.selectIdByUsername(addUser.getUsername());

        //5.使用接口类代理对象调用接口中向中间表t_user_role中插入数据的方法
        mapper.addUIDAndRIDToUserAndRoleTable(i,addUser.getRoleIds());

        //释放接口
        session.close();
    }
}
