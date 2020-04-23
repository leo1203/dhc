<template>
    <el-row class="tac">
        <el-menu
                class="el-menu-vertical-demo"
                background-color="#545c64"
                text-color="#fff"
                :default-active="$route.path"
                active-text-color="#ffd04b">
            <router-link tag="li" :to="{name:'orderQueryLink'}">
                <el-menu-item index="/admin/query">
                    <i class="el-icon-search"></i>
                    <span slot="title">预约查询</span>
                </el-menu-item>
            </router-link>
            <router-link tag="li" :to="{name:'roomManagerLink'}">
                <el-menu-item index="/admin/roomManager">
                    <i class="el-icon-menu"></i>
                    <span slot="title">会议室管理</span>
                </el-menu-item>
            </router-link>
            <router-link tag="li" :to="{name:'userManagerLink'}">
                <el-menu-item index="/admin/userManager">
                    <i class="el-icon-document"></i>
                    <span slot="title">用户管理</span>
                </el-menu-item>
            </router-link>
        </el-menu>
    </el-row>
</template>

<script>
    export default {}
</script>

<style scoped>
    .el-menu {
        height: 100%;
        text-align: left;
        border: 1px solid #545c64;
    }
</style>