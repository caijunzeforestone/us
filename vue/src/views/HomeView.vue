<template>
  <div>
    <div style="margin: 10px">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-button type="primary" @click="index" style="margin-left: 5px">搜索<i class="el-icon-search"></i></el-button>
    </div>
    <div style="margin: 10px">
      <el-button type="primary" @click="Add">添加数据<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
        confirm-button-text='好的'
        cancel-button-text='不用了'
        icon="el-icon-info"
        icon-color="red"
        @confirm="batch"
        title="这是一段内容确定删除吗？"
      >
        <el-button type="danger" slot="reference" style="margin-left: 5px">批量删除<i class="el-icon-delete"></i>
        </el-button>
      </el-popconfirm>
    </div>
    <div style="margin: 10px">
      <el-table :data="tableData" border stripe style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column fixed prop="id" label="ID" width="220"></el-table-column>
        <el-table-column fixed prop="username" label="姓名" width="220"></el-table-column>
        <el-table-column fixed prop="nickname" label="昵称" width="220"></el-table-column>
        <el-table-column fixed prop="email" label="邮箱" width="220"></el-table-column>
        <el-table-column fixed prop="phone" label="手机" width="220"></el-table-column>
        <el-table-column fixed prop="address" label="地址" width="220"></el-table-column>
        <el-table-column fixed prop="" label="操作" width="240">
          <template slot-scope="scope">
            <el-button type="success" @click="Edit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
            <el-popconfirm
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              @confirm="del(scope.row.id)"
              title="这是一段内容确定删除吗？"
            >
              <el-button type="danger" slot="reference" style="margin-left: 5px">删除<i class="el-icon-delete"></i>
              </el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
      <el-dialog title="用户管理" :visible.sync="dialogFormVisible">
        <el-form label-width="100px">
          <el-form-item label="姓名">
            <el-input v-model="form.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="住址">
            <el-input v-model="form.address" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    return {
      tableData: [],
      form: [],
      total:0,
      pageNum:1,
      pageSize:5,
      username:"",
      mu:[],
      dialogFormVisible: false
    }
  },
  created() {
    this.index()
  },
  methods: {
    index() {
      request.get("/user/index", {
        params: {
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          username:this.username
        }
      }).then(res => {
        this.tableData = res.records
        this.total = res.total
      })
    },
    del(id) {
      request.delete("/user/del/" + id).then(res => {
        if (res) {
          this.$message.success("删除成功!")
          this.index()
        } else {
          this.$message.error("删除失败!")
        }
      })
    },
    save() {
      request.post("/user/save", this.form).then(res => {
        if (res){
          this.$message.success("保存成功!")
          this.dialogFormVisible = false
          this.index()
        }else {
          this.$message.error("保存失败!")
        }
      })
    },
    batch(){
      let ids = this.mu.map(v => v.id)
      request.post("/user/batch/",ids).then(res =>{
        if (res) {
          this.$message.success("删除成功!")
          this.index()
        } else {
          this.$message.error("删除失败!")
        }
      })
    },
    Add() {
      this.dialogFormVisible = true
      this.form = {}
    },
    Edit(row) {
      this.dialogFormVisible = true
      this.form = row
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.index()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.index()
    },
    handleSelectionChange(val){
      this.mu = val
    }
  }
}
</script>
