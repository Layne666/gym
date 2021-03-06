# About

健身工作室后台管理系统

正在开发中，慢慢完善此项目！😉

我的博客：https://layne666.site

图片托管于图床：https://img.layne666.site

# 说明

开发环境 Java8 + MySQL5.7  

部署环境 阿里云 CentOS 7.2 64位

如有问题请直接在 Issues 中提，或者您发现问题并有非常好的解决方案，欢迎 PR 👍

# 相关技术

## 后端
SpringBoot + SpringMVC + Mybatis 

## 前端

Amaze UI + JQuery + Vue.js 

## 渲染模板
Thymeleaf

# 现阶段样式

### 登录界面

![登录界面](https://ws4.sinaimg.cn/large/005SN4y5ly1g2ha3c6p4qj319n0mjmyl.jpg)

### 主页界面

![主页界面](https://ws4.sinaimg.cn/large/005SN4y5ly1g2nfukym6hj31g20n2gom.jpg)

### 个人中心-头像管理

![头像管理](https://ws3.sinaimg.cn/large/005SN4y5ly1g2nfvcwj2xj31fd0kdn1b.jpg)

### 用户管理

![用户管理](https://ws2.sinaimg.cn/large/005SN4y5ly1g2nfwjpsxoj31g30i6dh5.jpg)

# 功能设计

## 登录模块

1. 进行登录验证
2. 判断登录用户是教练还是管理员，然后进行赋予权限

## 后台模块

### 主页

1. 实时展示当前月的课时记录
2. 实时展示当前月的用户加入情况
3. 实时展示当前月的收入额
...

### 会员管理

1. 显示所有的会员用户（分页）
2. 可根据多条件进行模糊查询
3. 添加会员（管理员）
4. （批量）删除会员（管理员）
5. 编辑会员（管理员）
6. 选择对应的会员进行上课

### 教练管理（管理员）

1. 显示所有的教练
2. 可多条件模糊查询
3. 增
4. 删
5. 改

### 个人中心

#### 管理员

1. 修改登录密码

#### 教练

1. 更改自己的信息

### 分类管理

1. 增加课程分类
2. 删
3. 改
4. 查

### 记录管理

1. 多条件查询课时记录（计算当前条件下的课时总金额，以及记录数）
2. 删除（管理员）
3. 教练登录时，显示的是自己的课时记录
4. 记录导出



