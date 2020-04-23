<template>
    <el-row class="tac">
        <el-menu
                class="el-menu-vertical-demo"
                background-color="#545c64"
                text-color="#fff"
                :default-active="$route.path"
                active-text-color="#ffd04b">
            <router-link tag="li" :to="{name:'userOrderQueryLink'}">
                <el-menu-item index="/user/orderQuery">
                    <i class="el-icon-search"></i>
                    <span slot="title">预约查询</span>
                </el-menu-item>
            </router-link>
            <router-link tag="li" :to="{name:'userRoomOrderLink'}">
                <el-menu-item index="/user/roomOrder">
                    <i class="el-icon-tickets"></i>
                    <span slot="title">会议室预约</span>
                </el-menu-item>
            </router-link>
            <router-link tag="li" :to="{name:'userRoomListLink'}">
                <el-menu-item index="/user/roomList">
                    <i class="el-icon-menu"></i>
                    <span slot="title">会议室查看</span>
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