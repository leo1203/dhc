import Vue from 'vue'
import Router from 'vue-router'
import Login from "@/views/Login";
import AdminMainView from "@/views/AdminMain"
import UserMainView from "@/views/UserMain"
//admin二级路由
import OrderQuery from "@/components/admin/OrderQuery";
import RoomManager from "@/components/admin/RoomManager";
import UserManager from "@/components/admin/UserManager";
//user二级路由
import UserOrderQuery from "@/components/user/UserOrderQuery"
import UserRoomList from "@/components/user/UserRoomList"
import UserRoomOrder from "@/components/user/UserRoomOrder"


Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: "/admin",
            name: 'adminLink',
            component: AdminMainView,
            redirect: "/admin/query",
            children: [
                {path: "/admin/query", name: "orderQueryLink", component: OrderQuery},
                {path: "/admin/roomManager", name: "roomManagerLink", component: RoomManager},
                {path: "/admin/userManager", name: "userManagerLink", component: UserManager},
            ]
        },
        {
            path: "/user",
            name: "userLink",
            component: UserMainView,
            redirect: "/user/orderQuery",
            children: [
                {path: "/user/orderQuery", name: "userOrderQueryLink", component: UserOrderQuery},
                {path: "/user/roomList", name: "userRoomListLink", component: UserRoomList},
                {path: "/user/roomOrder", name: "userRoomOrderLink", component: UserRoomOrder},
            ]
        },
        {
            path: "/",
            name: "loginLink",
            component: Login
        },
        {
            path: "*",
            redirect: "/"
        },
    ]
});
