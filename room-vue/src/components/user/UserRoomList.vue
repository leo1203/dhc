<!--会议室管理-->
<template>
    <div class="roomManager">
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
                            type="warning"
                            @click="handleOrder(scope.$index, scope.row)">预约
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

    </div>
</template>

<script>
    export default {
        name: "roomManager",
        components: {},
        data() {
            return {
                tableData: [],
            }
        },
        methods: {
            rowClassName({row, rowIndex}) {
                // console.log("rowIndex = " + rowIndex);
                row.id = rowIndex + 1;
            },
            handleOrder(index, row) {
                this.$router.push({
                    name: "userRoomOrderLink",
                    params: {
                        roomInfo: row
                    }
                });
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