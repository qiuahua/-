package com.itheima.case2.dao;


import com.itheima.case2.pojo.po.User;
import com.itheima.case2.pojo.vo.AddUser;
import com.itheima.case2.pojo.vo.UpdateUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    //分页查询用户和角色信息
    List<User> findAll(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);

//查询用户表的总记录数
    @Select("select count(*) from t_user")
    long findCount();

    //更新用户表t_user的数据
    @Update("update t_user set username=#{username},email=#{email},password=#{password} where id=#{id}")
    void update(UpdateUser updateUser);

    //根据用户id到中间表t_user_role中删除用户数据
    @Delete("delete from t_user_role where user_id=#{uid}")
    void deleteFromUserAndRoleTableByUID(@Param("uid") Integer id);

    //向中间表t_user_role中删除数据的方法
    void addUIDAndRIDToUserAndRoleTable(@Param("uid") Integer id, @Param("roleIds") List<String> roleIds);

    //根据id删除用户
    @Delete("delete from t_user where id = #{uid}")
    void delete(Integer uid);


    void add(AddUser addUser);


    @Select("select id from t_user where username=#{username}")
    int selectIdByUsername(String username);
}
