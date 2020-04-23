<!--会议室管理-->
<template>
    <div class="roomManager">
        <el-button @click="clickAddButton('roomInfoForm')" type="primary" style="float: right;margin-bottom: 10px;">
            添加会议室<i class="el-icon-plus el-icon--right"></i></el-button>
        <el-table
                :data="tableData"
                height="400"
                border
                stripe
                :row-class-name="rowClassName"
                style="width: 100%;">
            <el-table-column
                    label="编号"
                    align="center"
                    width="50"
                    prop="id">
            </el-table-column>
            <el-table-column
                    label="地域"
                    align="center"
                    width="180"
                    prop="areaName">
            </el-table-column>
            <el-table-column
                    label="地点"
                    width="180"
                    align="center"
                    prop="areaAddress">
            </el-table-column>
            <el-table-column
                    label="容纳人数"
                    align="center"
                    width="80"
                    prop="areaNumber">
            </el-table-column>
            <el-table-column
                    label="类型"
                    align="center"
                    width="120"
                    prop="useType">
            </el-table-column>
            <el-table-column
                    label="	备注"
                    align="center"
                    prop="roomMark">
            </el-table-column>
            <el-table-column
                    label="	操作"
                    align="center">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <edit-add-dialog :roomInfo="roomInfo"
                         :dialogTitle="dialogTitle"
                         :dialogFormVisible="dialogFormVisible"
                         @dismissDialog="dismissDialog()"
                         @changeRowStatus="changeRoomInfo($event)"></edit-add-dialog>
    </div>
</template>

<script>

    import DialogAddAndEdit from './DialogAddAndEdit'

    export default {
        name: "roomManager",
        components: {
            "edit-add-dialog": DialogAddAndEdit
        },
        data() {
            return {
                tableData: [],
                roomInfo: {
                    areaId: "",
                    areaName: "",
                    areaAddress: "",
                    useType: "",
                    areaNumber: "",
                    roomMark: ""
                },
                dialogTitle: "添加会议室",
                dialogFormVisible: false
            }
        },
        methods: {
            rowClassName({row, rowIndex}) {
                // console.log("rowIndex = " + rowIndex);
                row.id = rowIndex + 1;
            },
            handleEdit(index, row) {
                this.dialogTitle = "编辑会议室信息";
                console.log(row);
                this.roomInfo = row;
                this.dialogFormVisible = true;

            },
            handleDelete(index, row) {
                this.$confirm('此操作将删除该会议室, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //进行数据请求
                    let areaId = row.areaId;
                    this.axios.delete("/room/deleteRoom/" + areaId)
                        .then(response => {
                            return response.data;
                        })
                        .then(data => {
                            console.log(data);
                            if (data.code == 0) {
                                this.tableData.splice(index, 1);
                                this.$message({
                                    title: '成功',
                                    message: '删除成功',
                                    type: 'success'
                                });
                            } else {
                                this.$notify.error({
                                    title: '错误',
                                    message: '删除失败'
                                });
                            }
                        })
                        .catch(error => {
                            console.log(error);
                            this.$notify.error({
                                title: "错误",
                                message: "删除失败"
                            });
                        });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            clickAddButton() {
                this.dialogTitle = "添加会议室";
                this.dialogFormVisible = true;
                this.roomInfo = {
                    areaId: "",
                    areaName: "",
                    areaAddress: "",
                    useType: "",
                    areaNumber: "",
                    roomMark: ""
                }
            },
            dismissDialog(visible) {
                this.dialogFormVisible = visible;
            },
            changeRoomInfo() {
                console.log("刷新数据-----------");
                this.listRooms();
            },
            listRooms() {
                this.axios.post("/room/listRooms")
                    .then(response => {
                        return response.data;
                    })
                    .then(data => {
                        if (data.code == 0) {
                            this.tableData = data.data;
                        } else {
                            this.$notify.error({
                                title: '错误',
                                message: data.msg
                            });
                        }
                    })
                    .catch(error => {
                        console.log(error);
                        this.$notify.error({
                            title: '错误',
                            message: error.message
                        });
                    });
            }
        },
        mounted: function () {
            this.listRooms();
        }
    }
</script>

<style scoped>
    .el-table {
        font-size: 11px;
    }
</style>

<!--
areAddress: (...)
areaName: (...)
areaNumber: (...)
areaOrderId: (...)
areaOrderStatus: (...)
contactName: (...)
contactPhone: (...)
reserveDate: (...)
reserveEndTime: (...)
reserveStartTime: (...)
useType: (...)
-->