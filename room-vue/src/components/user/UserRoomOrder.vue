<template>
    <div class="userRoomOrder">
        <div class="container">
            <el-form :model="roomInfo" size="mini" :rules="rules" ref="roomInfoForm" label-width="100px"
                     class="demo-ruleForm">
                <el-form-item label="会议室名称" prop="areaId">
                    <el-select style="width: 100%" v-model="roomInfo.areaId">
                        <el-option v-for="item in rooms" :label="item.areaAddress" :value="item.areaId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="借用人" prop="contactName">
                    <el-input v-model="roomInfo.contactName" placeholder="请输入联系人姓名" disabled=""></el-input>
                </el-form-item>
                <el-form-item label="联系电话" prop="contactPhone">
                    <el-input v-model="roomInfo.contactPhone" placeholder="请输入联系人手机号码"></el-input>
                </el-form-item>

                <el-form-item label="使用日期" prop="reserveDate">
                    <el-date-picker type="date"
                                    placeholder="选择日期"
                                    v-model="roomInfo.reserveDate"
                                    value-format="yyyy-MM-dd"
                                    style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="使用时间" required prop="reserveStartTime">
                    <el-row style="float: left;">
                        <el-col :span="11">
                            <el-time-select
                                    placeholder="开始时间"
                                    v-model="roomInfo.reserveStartTime"
                                    :picker-options="{
                                  start: '08:30',
                                  step: '00:30',
                                  end: '18:30'
                                 }">
                            </el-time-select>
                        </el-col>
                        <el-col :span="2">&nbsp;</el-col>
                        <el-col :span="11">
                            <el-time-select
                                    placeholder="结束时间"
                                    v-model="roomInfo.reserveEndTime"
                                    :picker-options="{
                              start: '08:30',
                              step: '00:30',
                              end: '18:30',
                              minTime: roomInfo.reserveStartTime
                            }">
                            </el-time-select>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-row>
                    <el-col :span="11">
                        <el-form-item
                                prop="email"
                                label="参会人邮箱">
                            <el-input @keyup.enter.native="enterSubmit()" v-model="roomInfo.email"
                                      placeholder="请输入邮箱"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col class="line" :span="2">&nbsp;</el-col>
                    <el-col :span="11">
                        <el-input
                                type="textarea"
                                :rows="3"
                                :readonly="readOnlyEmails"
                                placeholder="参会人邮箱"
                                v-model="roomInfo.contactEmails">
                        </el-input>

                    </el-col>
                </el-row>

                <el-form-item style="margin-top: 10px;">
                    <el-button style="float: left;" type="primary" @click="submitForm('roomInfoForm')">立即预约</el-button>
                    <el-button style="float: left;margin-right: 15px;" type="primary" plain
                               @click="resetForm('roomInfoForm')">重置
                    </el-button>
                    <el-button style="float: right;" type="success"
                               @click="textReadOnly()">{{btnText}}
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "userRoomOrder",
        data() {
            return {
                roomInfo: {
                    areaId: "",
                    areaName: "",
                    contactName: "",
                    contactPhone: "",
                    reserveDate: "",
                    reserveStartTime: "",
                    reserveEndTime: "",
                    email: "",
                    contactEmails: ""
                },
                readOnlyEmails: true,
                btnText: "编辑",
                rooms: [],
                rules: {
                    areaId: {required: true, message: "请选择会议室", trigger: 'change'},
                    contactPhone: {required: true, message: "请输入联系人手机号码", trigger: 'blur'},
                    contactName: {required: true, message: "请输入联系人姓名", trigger: 'blur'},
                    reserveDate: {required: true, message: "请选择借用日期", trigger: 'blur'},
                    reserveStartTime: {required: true, message: "请选择开始和结束时间", trigger: ['blur', 'change']},
                    email: [
                        {required: false, message: '请输入邮箱地址', trigger: 'blur'},
                        {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
                    ]
                }
            }
        },
        methods: {
            submitForm(form) {
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        let params = new URLSearchParams();
                        params.append("areaId", this.roomInfo.areaId);
                        params.append("contactName", this.roomInfo.contactName);
                        params.append("contactPhone", this.roomInfo.contactPhone);
                        params.append("reserveDate", this.roomInfo.reserveDate);
                        params.append("reserveStartTime", this.roomInfo.reserveStartTime);
                        params.append("reserveEndTime", this.roomInfo.reserveEndTime);
                        if (this.roomInfo.contactEmails === undefined) {
                            params.append("contactEmails", "");
                        } else {
                            params.append("contactEmails", this.roomInfo.contactEmails);
                        }
                        params.append("areaOrderStatus", 10);

                        this.axios.post("/order/createRoomOrder", params)
                            .then(response => {
                                return response.data;
                            })
                            .then(data => {
                                if (data.code === 0) {
                                    this.$message.success("预约成功");
                                    this.$router.push({
                                        name: "userOrderQueryLink"
                                    });
                                } else {
                                    this.$message.error(data.msg);
                                }
                            })
                            .catch(error => {
                                this.$notify.error({
                                    title: '错误',
                                    message: data.msg
                                });
                            });

                    } else {
                        return false;
                    }
                });
            },
            resetForm(form) {
                this.$refs[form].resetFields();
            },
            enterSubmit() {
                if (this.roomInfo.contactEmails === undefined)
                    this.roomInfo.contactEmails = "";
                this.roomInfo.contactEmails += this.roomInfo.email + "|";
                this.roomInfo.email = "";
            },
            textReadOnly() {
                if (this.readOnlyEmails) {
                    this.readOnlyEmails = false;
                    this.btnText = "锁定";
                } else {
                    this.readOnlyEmails = true;
                    this.btnText = "编辑";
                }
            },
            listRooms() {
                this.axios.post("/room/listRooms")
                    .then(response => {
                        return response.data;
                    })
                    .then(data => {
                        console.log(data);
                        if (data.code === 0) {
                            this.rooms = data.data;
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
            },
            getRouterData() {
                if (this.$route.params.roomInfo !== undefined) {
                    this.roomInfo = this.$route.params.roomInfo;
                    console.log("roomInfo--------------");
                    console.log(this.roomInfo);
                }
            }
        },
        mounted() {
            this.roomInfo.contactName = this.$store.state.userName;
            this.roomInfo.contactPhone = this.$store.state.userPhone;
            this.listRooms();
        },
        created() {
            this.getRouterData();
        }
    }
</script>

<style scoped>
    .container {
        width: 60%;
        margin: 10px auto;
        padding-top: 20px;
    }
</style>