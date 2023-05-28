import VueRouter from 'vue-router'
// 引入组件
import Student from '../components/Student'
import Login from '../components/Login'
import Register from '../components/Register'
const router = new VueRouter({
    mode:"history",
    routes: 
    [


        {
            path: "/login",
            component: Login,
            name: "login",
        },
        {
            path: "/register",
            component: Register,
            name: "register",
        },
        {
            path: "/student",
            component: Student,
            name: "student",
        },
    ]
})

export default router