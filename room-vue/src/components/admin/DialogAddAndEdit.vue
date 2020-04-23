<template>
    <div class="dialogAddAndEdit">
        <el-dialog :title="dialogTitle"
                   :visible.sync="dialogFormVisible"
                   :show-close="false"
                   :close-on-click-modal="false"
                   :before-close="dialogClose()">
            <el-form ref="ruleForm"
                     :model="roomInfo"
                     :rules="rules"
                     status-icon
                     :label-width="formLabelWidth">
                <el-form-item
                        label="地域"
                        prop="areaName">
                    <el-input
                            v-model="roomInfo.areaName"
                            placeholder="请录入会议室地域信息(如：西安软件新城)"
                            autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="地点" prop="areaAddress">
                    <el-input
                            v-model="roomInfo.areaAddress"
                            placeholder="请录入会议室地点信息(如：c2座1502会议室(一)"
                            autocomplete="off"></el-input>
                </el-form-item>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="类型" prop="useType">
                            <el-select v-model="roomInfo.useType" placeholder="请选择">
                                <el-option v-for="item in optionUseTypes" :label="item" :value="item"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="容纳人数" prop="areaNumber">
                            <el-select v-model="roomInfo.areaNumber" placeholder="请选择">
                                <el-option label="10人以内" value="10人以内"></el-option>
                                <el-option label="10-20人" value="10-20人"></el-option>
                                <el-option label="20-50人" value="20-50人"></el-option>
                                <el-option label="50人以上" value="50人以上"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="备注" prop="roomMark">
                    <el-input type="textarea" v-model="roomInfo.roomMark"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button style="float:right;" type="primary"
                               @click="confirmAddRoom('ruleForm')">确 定
                    </el-button>
                    <el-button style="float:right;margin-right:15px;" @click="cancelOpration()">取 消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "dialogAddAndEdit",
        props: {
            roomInfo: {
                areaId: "",
                areaName: "",
                areaAddress: "",
                useType: "",
                areaNumber: "",
                roomMark: ""
            },
            dialogTitle: String,
            dialogFormVisible: Boolean,
        },
        data() {
            return {
                formLabelWidth: '100px',
                optionUseTypes: ["视频会议室", "视频电话会议室", "普通会议室", "阶梯会议室"],
                rules: {
                    areaName: [
                        {type: "string", required: true, message: "请输入地域信息", trigger: "blur"}
                    ],
                    areaAddress: [
                        {type: "string", required: true, message: '请输入地点信息', trigger: 'blur'}
                    ],
                    useType: [
                        {type: "string", required: true, message: '请选择会议室类型', trigger: 'change'}
                    ],
                    areaNumber: [
                        {type: "string", required: true, message: '请选择会议室容纳人数', trigger: 'change'}
                    ]
                }
            }
        },
        methods: {
            confirmAddRoom(ruleForm) {
                this.$refs[ruleForm].validate((valid) => {
                    console.log("valid = " + valid);
                    if (valid) {
                        let params = new URLSearchParams();
                        params.append("areaName", this.roomInfo.areaName);
                        params.append("areaAddress", this.roomInfo.areaAddress);
                        params.append("useType", this.roomInfo.useType);
                        params.append("areaNumber", this.roomInfo.areaNumber);
                        params.append("roomMark", this.roomInfo.roomMark);
                        if (this.roomInfo.areaId != "") {
                            console.log("修改-----------------");
                            params.append("areaId", this.roomInfo.areaId);
                            this.axios.put("/room/updateMeetingAreaById", params)
                                .then(response => {
                                    return response.data;
                                })
                                .then(data => {
                                    if (data.code == 0) {
                                        this.$emit("changeRowStatus", this.roomInfo);
                                        this.$refs[ruleForm].resetFields();
                                        this.$message.success("修改成功");
                                        this.$emit("dismissDialog", false);
                                    } else {
                                        this.$message.error(data.msg);
                                    }
                                })
                                .catch(error => {
                                    this.$notify.error({
                                        title: "错误",
                                        message: error.message
                                    })
                                });
                        } else {
                            console.log("添加-----------------");
                            this.axios.post("/room/createRoom", params)
                                .then(response => {
                                    return response.data;
                                })
                                .then(data => {
                                    if (data.code == 0) {
                                        this.$emit("changeRowStatus", this.roomInfo);
                                        this.$refs[ruleForm].resetFields();
                                        this.$message.success("添加成功");
                                        this.$emit("dismissDialog", false);
                                    } else {
                                        this.$message.error(data.msg);
                                    }
                                })
                                .catch(error => {
                                    this.$notify.error({
                                        title: "错误",
                                        message: error.message
                                    })
                                });
                        }
                    } else {
                        return false;
                    }
                });
            },
            cancelOpration() {
                this.$emit("dismissDialog", false);
            },
            dialogClose() {
                if (this.$refs['roomInfo'] != undefined) {
                    this.$refs['roomInfo'].resetFields();
                }
                console.log("dialog close ...")
            }
        }
    }
</script>

<style scoped>

</style>