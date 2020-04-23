import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import {store} from './store'

axios.defaults.baseURL = "http://10.10.1.35:8666/roomapi";
axios.defaults.timeout = 8000;

Vue.config.productionTip = false;
Vue.prototype.axios = axios;

Vue.use(ElementUI);

router.beforeEach((to, from, next) => {
    console.log("login = " + store.state.isLogin);
    if (localStorage.getItem("isLogin") && localStorage.getItem("token") !== "") {
        next();
    } else if (to.path === "/") {
        next();
    } else {
        next({path: "loginLink"});
    }
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
