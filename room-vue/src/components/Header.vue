<template>
    <header>
        <h3><i class="el-icon-s-tools"></i>网上会议室预约系统</h3>
        <div style="float: right;margin-top: 10px;">
            <el-dropdown :split-button="true"
                         trigger="click"
                         @command="handleCommand">
                          <span class="el-dropdown-link">
                            <i class="el-icon-user el-icon--left"></i> {{username}}{{role}}
                          </span>
                <el-dropdown-menu slot="dropdown" style="width: 15%">
                    <el-dropdown-item icon="el-icon-setting" command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </header>
</template>

<script>
    export default {
        data() {
            return {}
        },
        methods: {
            handleCommand(command) {

                localStorage.setItem("isLogin", false);
                localStorage.setItem("token", "");
                this.$store.commit("setLoginState", false);
                this.$store.commit("setUserPhone", "");
                this.$store.commit("setUserName", "");
                this.$store.commit("setUserInfo", {});
                this.$store.commit("setTokenValue", "");
                this.$router.push({name: "loginLink"});
            }
        },
        computed: {
            username() {
                return this.$store.state.userName;
            },
            role() {
                let r = this.$store.state.userInfo.role.roleNo;
                if (r === "admin") {
                    return "(管理员)"
                } else {
                    return "";
                }
            }
        }
    }
</script>

<style scoped lang="scss">
    header {
        display: block;
    }

    header h3 {
        color: #fff;
        float: left;
        height: 60px;
        line-height: 60px;
        text-align: center;
    }

    header .el-button {
        float: right;
        height: 40px;
        color: #000;
        margin-top: 10px;
        text-align: center;
        opacity: 0.9;
        background-color: white;
        border: none;
    }

    header h3 i {
        width: 30px;
        height: 30px;
    }
</style>