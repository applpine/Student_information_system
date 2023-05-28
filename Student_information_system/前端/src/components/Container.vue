<template>
  <div>
      <el-header> 学生管理系统 </el-header>
      <el-container>
       <el-aside width="200px" style="margin-top: 1%" >
           <el-menu :default-openeds="['1']">

            <el-menu-item-group>
              <router-link class="alink" to="/register">
                 <el-menu-item index="1-1">
                  <i class="el-icon-user-solid"></i>
                  <span slot="title">注册</span>
                 </el-menu-item>
              </router-link>

               <router-link class="alink" to="/login">
                 <el-menu-item index="1-2">
                  <i class="el-icon-s-management"></i>
                  <span slot="title">登录</span>
                 </el-menu-item>
              </router-link>

              <router-link class="alink" to="/student">
                <el-menu-item index="1-3">
                  <i class="el-icon-document" ></i>
                  <span slot="title">学生列表</span>
                </el-menu-item>
              </router-link>

              <router-link class="alink" to="">
                 <el-menu-item index="1-4" @click="loginout">
                  <i class="el-icon-setting"></i>
                  <span slot="title">登出</span>
                 </el-menu-item>
              </router-link>

            <br /><br />
            </el-menu-item-group>
           
          </el-menu>
       </el-aside>

        <el-main>
          <keep-alive>
            <router-view></router-view>
          </keep-alive>
        </el-main>

       </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  components: {
  },
  methods:{
    loginout(){
      alert("登出");
    }
  },
};
</script>

<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 50px;
}
.alink {
  text-decoration: none;
}
</style>