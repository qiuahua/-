package com.itheima.case2.service;

import com.itheima.case2.pojo.vo.AddUser;
import com.itheima.case2.pojo.vo.PageResult;
import com.itheima.case2.pojo.vo.QueryPageBean;
import com.itheima.case2.pojo.vo.UpdateUser;

public interface UserService {

    //分页查询用户
    public PageResult findAll(QueryPageBean pb);

    //更新
    void update(UpdateUser updateUser);

    void delete(Integer uid);

    void add(AddUser addUser);
}
