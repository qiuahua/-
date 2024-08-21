<template>
  <div id="div">
    <el-container>
        <!-- 头部-->
        <el-header class="el-header">
            <el-container>
                <div>
                    <el-image src="../static/export.png" class="header-img"></el-image>
                </div>
                <el-menu
                    mode="horizontal"
                    background-color="#545c64"
                    text-color="white"
                    active-text-color="#ffd04b"
                    style="margin-left: auto;">
                    <el-menu-item index="1">处理中心</el-menu-item>
                    <el-submenu index="2">
                        <template slot="title">我的工作台</template>
                        <el-menu-item index="2-1">选项1</el-menu-item>
                        <el-menu-item index="2-2">选项2</el-menu-item>
                        <el-menu-item index="2-3">选项3</el-menu-item>
                    </el-submenu>
                    <el-menu-item index="3"><a href="#/" target="_self">首页</a></el-menu-item>
                    <el-menu-item index="4"><a href="login.html" target="_self">登录</a></el-menu-item>
                </el-menu>
            </el-container>
        </el-header>

        <!-- 中间部分 -->
        <el-container style="height: 580px; border: 1px solid #eee">
            <!-- 侧边栏 -->
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
              <el-menu :default-openeds="['1']">
                <el-submenu index="1">
                  <template slot="title"><i class="el-icon-menu"></i>用户列表</template>
                  <el-menu-item-group>
                      <!--
                        TODO: 点击执行
                      -->
                      <el-menu-item index="1-1" ><i class="el-icon-help"></i> <a href="#/userlist">用户管理</a></el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title"><i class="el-icon-menu"></i>角色列表</template>
                    <el-menu-item-group>
                      <el-menu-item index="2-1"><i class="el-icon-help"></i>角色管理</el-menu-item>
                    </el-menu-item-group>
                  </el-submenu>
                  <el-submenu index="3">
                    <template slot="title"><i class="el-icon-menu"></i>权限列表</template>
                    <el-menu-item-group>
                      <el-menu-item index="3-1"><i class="el-icon-help"></i>权限管理</el-menu-item>
                    </el-menu-item-group>
                  </el-submenu>
              </el-menu>
            </el-aside>
            
            <el-container>
              <!-- 主区域部分 TODO -->
              <el-main>
                 <router-view/>
              </el-main>
            </el-container>
          </el-container>
    </el-container>
</div>

</template>

<script>
export default {
  data(){
    return {
            activeIndex:1
        }
  }
}
</script>

<style>
   .el-header{
            background-color: #545c64;
        }
        .header-img{
            width: 100px;
            margin-top:20px;
        }
</style>
