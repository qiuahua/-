package com.itheima.case2.service.impl;

import com.itheima.case2.dao.RoleMapper;
import com.itheima.case2.pojo.po.Role;
import com.itheima.case2.service.RoleService;
import com.itheima.case2.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Override
    public List<Role> findAllRoles() {
        //用户下啦框查询角色的信息

        //1获取
        SqlSession session = SqlSessionUtil.getSession();
        RoleMapper mapper = session.getMapper(RoleMapper.class);

        List<Role> list =  mapper.findAllRoles();

        //释放资源
        session.close();

        //将list返回给web层
        return list;
    }
}
