<template>
    <div class="userMain">
        <el-container>
            <el-header>
                <app-header></app-header>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <user-aside></user-aside>
                </el-aside>
                <el-container>
                    <el-main>
                        <router-view></router-view>
                    </el-main>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script>

    import UserAside from '../components/user/UserAside'
    import Header from '../components/Header'

    export default {
        name: "userMain",
        components: {
            "user-aside": UserAside,
            "app-header": Header
        }
    }
</script>

<style scoped>

    .userMain {
        height: 100%;
    }

    .el-header {
        background-color: #222;
    }

    .el-aside {
        background-color: #545c64;
        color: #333;
        text-align: center;
        height: 100%;
    }


    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        height: 100%;
        /*line-height: 360px;*/
    }
</style>