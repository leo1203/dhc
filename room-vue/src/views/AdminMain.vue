<template>
    <div class="admin">
        <el-container>
            <el-header>
                <app-header></app-header>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <app-aside></app-aside>
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
    import Header from '../components/Header'
    import Aside from '../components/admin/Aside'

    export default {
        name: "admin",
        components: {
            'app-header': Header,
            'app-aside': Aside
        },
        data() {
            return {
                //父向子组件传值
                msg: 'Hello World!'
            }
        }
    }
</script>

<style scoped>
    .admin {
        width: 100%;
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