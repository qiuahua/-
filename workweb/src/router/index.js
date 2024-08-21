import Vue from 'vue'
import Router from 'vue-router'
import welcome from '@/views/welcome'
import userList from '@/views/userList'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: welcome
    },
    { // 定义组件的路径
      path: '/userlist',
      name: 'userList',
      component: userList
    }
  ]
})
