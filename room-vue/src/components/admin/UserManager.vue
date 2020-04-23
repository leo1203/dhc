<!--用户管理-->
<template>
    <div class="userManager">
        <el-button @click="clickAddButton()" type="primary" style="float: right;margin-bottom: 10px;">
            添加用户<i class="el-icon-plus el-icon--right"></i></el-button>
        <el-table
                :data="tableData"
                height="400"
                border
                stripe
                :row-class-name="rowClassName"
                style="width: 100%;">
            <el-table-column
                    label="	用户名"
                    align="center"
                    prop="user.userName">
            </el-table-column>
            <el-table-column
                    label="	手机号"
                    align="center"
                    prop="user.mobile">
            </el-table-column>
            <el-table-column
                    label="	角色"
                    align="center"
                    prop="role.roleName">
            </el-table-column>
            <el-table-column
                    label="	操作"
                    align="center">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row.user.userId)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="添加用户"
                   :visible.sync="dialogFormVisible"
                   :show-close="false"
                   :close-on-click-modal="false">
            <el-form
                    :model="userInfo"
                    status-icon
                    :rules="rules"
                    ref="userInfoForm"
                    size="small"
                    :label-width="formLabelWidth">
                <el-form-item label="手机号" prop="mobile">
                    <el-input
                            v-model="userInfo.mobile"
                            placeholder="请输入用户手机号"
                            autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item
                        label="用户名"
                        prop="userName">
                    <el-input
                            v-model="userInfo.userName"
                            placeholder="请输入用户名称"
                            autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="用户角色" prop="roleId">
                    <el-select v-model="userInfo.roleId" style="width: 100%" placeholder="请选择">
                        <el-option v-for="item in roles" :label="item.roleName" :value="item.roleId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" placeholder="请输入密码" v-model="userInfo.password"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="passwordA">
                    <el-input type="password" placeholder="请再次输入密码" v-model="userInfo.passwordA"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="float:right;" type="primary"
                               @click="confirmAddUser('userInfoForm')">确 定
                    </el-button>
                    <el-button style="float:right;margin-right:15px;" @click="cancelDialog()">取 消
                    </el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "UserManager",
        data() {

            let validateCheckPass = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.userInfo.password) {
                    callback(new Error("两次密码输入不一致"));
                } else {
                    callback();
                }
            };


            return {
                tableData: [],
                dialogFormVisible: false,
                userInfo: {
                    userName: "",
                    createUser: "",
                    mobile: "",
                    roleId: 1,
                    password: "",
                    passwordA: ""
                },
                formLabelWidth: '120px',
                roles: [],
                rules: {
                    mobile: [
                        {required: true, message: '请输入手机号', trigger: 'blur'}
                    ],
                    userName: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    roleId: [
                        {required: true, message: '请选择用户角色', trigger: 'change'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'change'}
                    ],
                    passwordA: [
                        {required: true, validator: validateCheckPass, trigger: 'change'}
                    ]
                }
            }
        },
        methods: {
            rowClassName(row, rowIndex) {

            },
            handleDelete(index, userId) {

                this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = new URLSearchParams();
                    params.append("userId", userId);
                    this.axios.post("/user/deleteUser", params)
                        .then(response => {
                            return response.data;
                        })
                        .then(data => {
                            if (data.code == 0) {
                                this.tableData.splice(index, 1);
                                this.$message.success("删除成功");
                            } else {
                                this.$message.error(data.msg);
                            }
                        })
                        .catch(error => {
                            console.log(error);
                            this.$notify.error({
                                title: "错误",
                                message: error.message
                            })
                        });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            clickAddButton() {
                this.dialogFormVisible = true;
            },
            cancelDialog() {
                this.dialogFormVisible = false;
            },
            retrieveUsers() {
                this.axios.get("/user/listUsers")
                    .then(response => {
                        return response.data;
                    })
                    .then(data => {
                        console.log("--------");
                        console.log(data);
                        if (data.code == 0) {
                            this.tableData = data.data;
                        } else {
                            this.$notify.error({
                                title: "错误",
                                message: data.msg
                            });
                        }
                    })
                    .catch(error => {
                        this.$notify.error({
                            title: "错误",
                            message: error.message
                        });
                    });
            },
            retrieveRoles() {
                this.axios.get("/role/listRoles")
                    .then(response => {
                        return response.data;
                    })
                    .then(data => {
                        console.log(data);
                        if (data.code == 0) {
                            this.roles = data.data;
                        } else {
                            this.$notify.error({
                                title: "错误",
                                message: data.msg
                            });
                        }
                    })
                    .catch(error => {
                        this.$notify.error({
                            title: "错误",
                            message: error.message
                        });
                    });
            },
            confirmAddUser(ruleForm) {
                this.$refs[ruleForm].validate(valid => {
                    if (valid) {
                        let params = new URLSearchParams();
                        params.append("userName", this.userInfo.userName);
                        params.append("mobile", this.userInfo.mobile);
                        params.append("roleId", this.userInfo.roleId);
                        params.append("password", this.userInfo.password);
                        this.axios.post("/userLogin/registry", params)
                            .then(response => {
                                return response.data;
                            })
                            .then(data => {
                                console.log(data);
                                if (data.code == 0) {
                                    this.tableData.unshift(data.data);
                                    this.dialogFormVisible = false;
                                } else {
                                    this.$notify.error({
                                        title: "错误",
                                        message: data.msg
                                    });
                                }
                            })
                            .catch(error => {
                                this.$notify.error({
                                    title: "错误",
                                    message: error.message
                                });
                            });
                    } else {
                        return false;
                    }
                });
            }
        },
        mounted: function () {
            this.retrieveUsers();
            this.retrieveRoles();
        }
    }
</script>

<style scoped>
    .el-table {
        font-size: 11px;
    }

    .el-input {
        font-size: 11px;
    }

    .el-dialog {
        display: flex;
        text-align: center;
    }
</style>
