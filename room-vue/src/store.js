import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

function storeLocalStore(state) {
    window.localStorage.setItem("store", JSON.stringify(state));
}


export const store = new Vuex.Store({
    state: {
        isLogin: false,
        token: "",
        userInfo: {
            role: {},
            user: {}
        },
        userPhone: "",
        userName: ""
    },
    mutations: {
        setLoginState(state, loginState) {
            state.isLogin = loginState;
            storeLocalStore(state)
        },
        setUserPhone(state, userPhone) {
            state.userPhone = userPhone;
            storeLocalStore(state)
        },
        setUserName(state, userName) {
            state.userName = userName;
            storeLocalStore(state)
        },
        setUserInfo(state, userInfo) {
            state.userInfo = userInfo;
            storeLocalStore(state)
        },
        setTokenValue(state, token) {
            state.token = token;
            storeLocalStore(state)
        }
    }
});


/*
* role:
    createTime: "2019-05-09 11:12:24"
    del_flag: false
    roleId: 1
    roleName: "管理员"
    roleNo: "admin"
    roleRemark: "系统管理员"
    updateTime: "2019-05-09 11:12:24"
token: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE1MzIzNTEsInVzZXJuYW1lIjoiMTgyOTI0ODk0NzgifQ.LSw8no7dKQXFPZ34qVrt_fyKUfTtYQ70VGw946NrR5Q"
user:
    createTime: "2019-05-08 18:17:33"
    delFlag: false
    gender: "1"
    mobile: "18292489478"
    password: "8a6f2805b4515ac12058e79e66539be9"
    roleId: 1
    userId: "40285adc6a96eb6f016a96f2c3010001"
    userName: "22222"
* */