<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>
<script>
    export default {
        components: {},
        data() {
            return {
                loginState: false
            }
        },
        mounted() {
            this.loginState = this.$store.state.isLogin
        },
        //解决页面刷新，store.state数据丢失
        created() {
            //在页面加载时读取sessionStorage里的状态信息
            // this.$store.replaceState(JSON.parse(sessionStorage.getItem("store")));
            if (localStorage.getItem("store"))
                this.$store.replaceState(Object.assign(this.$store.state, JSON.parse(localStorage.getItem("store"))));
            //在页面刷新时将vuex里的信息保存到sessionStorage里
            // window.addEventListener("beforeunload", () => {
            //     sessionStorage.setItem("store", JSON.stringify(this.$store.state))
            // });
        }
    }
</script>

<style scoped lang="scss">

</style>
