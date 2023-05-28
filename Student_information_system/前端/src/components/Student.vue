<template>
  <div>
    <div class="all">
      <table class="table table-hover">
      <caption class="caption1">
        <el-input style="width: 150px;" placeholder="请输入学号" v-model="searchStudent.number"></el-input>
        <el-input style="width: 150px;" placeholder="请输入学生姓名" v-model="searchStudent.name"></el-input>
        <el-input style="width: 150px;" placeholder="请输入学生年龄" v-model="searchStudent.age"></el-input>
        <el-input style="width: 150px;" placeholder="请输入学生地址" v-model="searchStudent.place"></el-input>

            <el-button type="success" icon="el-icon-search" circle @click="search"></el-button>
            <el-button type="warning" icon="el-icon-plus" circle @click="dialogVisible = true"></el-button>
             <!-- 增加弹窗 -->
              <el-dialog title="添加信息" :visible="dialogVisible">
               <el-form :model="newStudent">
                 <el-form-item label="学号" :label-width="formLabelWidth">
                   <el-input v-model="newStudent.number" autocomplete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="姓名" :label-width="formLabelWidth">
                   <el-input v-model="newStudent.name" autocomplete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="年龄" :label-width="formLabelWidth">
                   <el-input v-model="newStudent.age" autocomplete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="地址" :label-width="formLabelWidth">
                   <el-input v-model="newStudent.place" autocomplete="off"></el-input>
                 </el-form-item>
               </el-form>
               <div slot="footer" class="dialog-footer">
                 <el-button @click="dialogVisible = false">取 消</el-button>
                 <el-button type="primary" @click="addStudent">确 定</el-button>
               </div>
             </el-dialog>
      </caption>
      <thead>
        <tr>
          <th>学号</th>
          <th>姓名</th>
          <th>年龄</th>
          <th>地址</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in students" :key="student.id" >
          <td>{{student.number}}</td>
          <td>{{student.name}}</td>
          <td>{{student.age}}</td>
          <td>{{student.place}}</td>
          <td>
            <el-button type="primary" round @click="openDialog(student)">修改</el-button>
            <!-- 修改弹窗 -->
              <el-dialog title="修改信息" :visible="isDialogOpen(student)">
               <el-form :model="student">
                 <el-form-item label="学号" :label-width="formLabelWidth">
                   <el-input v-model="student.number" autocomplete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="姓名" :label-width="formLabelWidth">
                   <el-input v-model="student.name" autocomplete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="年龄" :label-width="formLabelWidth">
                   <el-input v-model="student.age" autocomplete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="地址" :label-width="formLabelWidth">
                   <el-input v-model="student.place" autocomplete="off"></el-input>
                 </el-form-item>
               </el-form>
               <div slot="footer" class="dialog-footer">
                 <el-button @click="closeDialog(student)">取 消</el-button>
                 <el-button type="primary" @click="confirm(student)">确 定</el-button>
               </div>
             </el-dialog>

            <el-button type="danger" round @click="openDelete(student)">删除</el-button>
          </td>
        </tr>
      </tbody>
    </table>
    </div>
    <!-- 分页 -->
    <div class="all">
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return{
            students:[],
            newStudent:{
              number:"",
              name:"",
              age:"",
              place:"",
            },
            searchStudent:{
              number:"",
              name:"",
              age:"",
              place:"",
            },
            formLabelWidth: '120px',
            openDialogStudent: null,
            dialogVisible: false,
            currentPage: 1, // 初始页码
            pageSize: 5, // 每页的数据   searchList.size
            total: 0, // 总记录数
        };
    },
    methods:{
        search(){
          axios({
                url: "http://localhost:8080/search",
                method: "POST",
                data: this.searchStudent,
               }).then(res=>{
                console.log(res.data);
                this.total = res.data.length;
                console.log(this.total);
                this.students = res.data.splice(
                   (this.currentPage - 1) * this.pageSize,
                   this.pageSize
                )
            });
        },
        addStudent(){
               axios({
                url: "http://localhost:8080/add",
                method: "POST",
                data: this.newStudent,
               });
               this.dialogVisible = false;
               window.location.reload();
        },
        update(student){
               axios({
                url: "http://localhost:8080/update",
                method: "POST",
                data: student,
               })
        },
        deleteStudent(student){
             axios({
                url: "http://localhost:8080/delete",
                method: "POST",
                data: student,
               });
               window.location.reload();
        },
      //删除提示
      openDelete(student) {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
          this.deleteStudent(student);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      //修改确认
      confirm(student){
         this.openDialogStudent = null;
         this.update(student)
      },
      //打开弹窗
      openDialog(student) {
         this.openDialogStudent = student;
      },
      //判断身份
      isDialogOpen(student) {
         return this.openDialogStudent === student;
      },
      //关闭弹窗
      closeDialog(){
        this.openDialogStudent = null;
      },
          // 每页大小变更处理函数
      handleSizeChange: function (size) {
        this.pageSize = size;
        console.log("每页大小:" + this.pageSize); //每页下拉显示数据
        this.search();
      },
      // 页码变更处理函数
      handleCurrentChange: function (currentPage) {
        this.currentPage = currentPage;
        console.log("当前页码:" + this.currentPage); //点击第几页
        this.search();
      },
    },
    mounted() {
       // 初始化加载学生数据
       this.search();
    }
};
</script>

<style>
.caption1{
  text-align: center;
  caption-side: top;
}
.all{
  text-align: center;
}
</style>