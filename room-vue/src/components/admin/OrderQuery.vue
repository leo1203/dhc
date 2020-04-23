<!--会议室预约查询-->
<template>
    <div class="orderQuery">
        <div style="margin-top: 5px;">
            <el-input :placeholder="placeHolderText" v-model="searchText"
                      class="input-with-select search-input">
                <el-select @change="selectChanged($event)" v-model="select" slot="prepend" placeholder="请选择">
                    <el-option label="姓名" value="1"></el-option>
                    <el-option label="手机号" value="2"></el-option>
                </el-select>
                <el-button slot="append" type="primary" @click="startSearch()" icon="el-icon-search"></el-button>
            </el-input>
        </div>
        <el-table
                :data="tableData"
                height="360"
                border
                style="width: 100%"
                :row-class-name="tableRowClassName">
            <el-table-column
                    prop="areaName"
                    align="center"
                    label="地域">
            </el-table-column>
            <el-table-column
                    align="center"
                    width="160"
                    prop="areAddress"
                    label="地点">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="useType"
                    label="类型">
            </el-table-column>
            <el-table-column
                    align="center"
                    width="70"
                    prop="areaNumber"
                    label="容纳人数">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="reserveDate"
                    label="使用日期">
            </el-table-column>
            <el-table-column
                    align="center"
                    width="70"
                    prop="reserveStartTime"
                    label="开始时间">
            </el-table-column>
            <el-table-column
                    align="center"
                    width="70"
                    prop="reserveEndTime"
                    label="结束时间">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="contactName"
                    label="预约人">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="contactPhone"
                    label="预约人电话">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="areaOrderStatus"
                    label="状态">
                <template slot-scope="scope">
                    <font v-if="scope.row.areaOrderStatus === '10'" color="red">已预约</font>
                    <font v-else-if="scope.row.areaOrderStatus === '20'" color="gray">已使用</font>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination background layout="prev, pager, next"
                       :total="totalPage"
                       :page-size="pageSize"
                       @current-change="handleCurrentChange"
                       :current-page.sync="currentPage"
                       align="right"
                       style="margin-top: 20px">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "orderQuery",
        data() {
            return {
                placeHolderText: "请输入姓名或手机号",
                searchText: "",
                select: "",
                tableData: [],
                totalPage: 0,
                pageSize: 6,
                currentPage: 1,
            }
        },
        methods: {
            tableRowClassName({row, rowIndex}) {
                if (rowIndex % 2 !== 0) {
                    return "success-row";
                }
                return '';
            },
            startSearch() {
                this.search(this.searchText);
            },
            selectChanged(v) {
                if (v == 1) {
                    this.placeHolderText = "请输入姓名";
                } else if (v == 2) {
                    this.placeHolderText = "请输入手机号";
                } else {
                    this.placeHolderText = "请输入姓名或手机号";
                }
            },
            handleCurrentChange(v) {
                console.log(`当前页: ${v}`);
                this.currentPage = v;
                this.search();
            },
            search(text) {
                let params = new URLSearchParams();
                if (text != undefined)
                    params.append("searchContext", text);
                params.append("page", this.currentPage);
                params.append("rows", this.pageSize);
                this.axios.post("/order/queryMeetingOrderInfo", params)
                    .then(response => {
                        this.tableData = response.data.rows;
                        this.totalPage = response.data.total;
                    })
                    .catch(error => {
                        this.$notify.error({
                            title: "错误",
                            message: error.messge
                        })
                    });
            },
            statusFormatter(row, column) {
                let status = row.areaOrderStatus;
                if (status == 10) {
                    // return "<span style='color: red'>已预约</span>";
                    return "已预约";
                } else if (status == 20) {
                    return "已使用";
                    // return "<span style='color: gray'>已使用</span>";
                }
            }
        },
        mounted: function () {
            this.search();
        }
    }
</script>

<style scoped lang="scss">
    .search-input {
        width: 60%;
        margin: 0 auto;
    }

    .el-select {
        width: 100px;
    }

    .input-with-select {
        background-color: #fff;
    }

    .el-table {
        width: 80%;
        margin: 5px auto;
        font-size: 11px;
    }

    .warning-row {
        background-color: oldlace;
    }

    .success-row {
        background-color: #f0f9eb;
    }
</style>