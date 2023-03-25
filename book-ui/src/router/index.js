import { createWebHistory, createRouter } from "vue-router";
import Index from "@/views/index.vue";

// 公共路由
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/pages/login"),
    hidden: true
  },
  {
    path: "/register",
    component: () => import("@/views/pages/register"),
    hidden: true
  },
  {
    path: "/:pathMatch(.*)*",
    component: () => import("@/views/pages/error/404"),
    hidden: true,
  },
  {
    path: "/401",
    component: () => import("@/views/pages/error/401"),
    hidden: true,
  },
  {
    path: "/",
    component: Index,
    redirect: "/index",
    children: [
      {
        path: "/index",
        component: () => import("@/views/pages/front"),
        name: "Index",
        meta: { title: "首页", icon: "dashboard", affix: true },
      },
      {
        path: '/book',
        component: () => import('@/views/pages/book/Main.vue')
      },
      {
        path: '/bookInfo',
        component: () => import('@/views/pages/book/BookInfo.vue')
      },
      {
        path: '/userInfo',
        component: () => import('@/views/pages/user')
      },
      {
        path: '/cart',
        component: import('@/views/pages/cart')
      },
      {
        path: '/borrow',
        component: import('@/views/pages/borrow')
      },
      {
        path: '/calculate',
        component: import('@/views/pages/calculate')
      }
    ],
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes: constantRoutes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  },
});

export default router;
