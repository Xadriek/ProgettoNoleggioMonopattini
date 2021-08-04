import Vue from 'vue';
import Router from 'vue-router';
import Home from './view/Home.vue';
import Login from './view/Login.vue';
import Register from './view/Register.vue';


Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./view/Profile.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('./view/BoardAdmin.vue')
    },
    {
      path: '/partner',
      name: 'partner',
      // lazy-loaded
      component: () => import('./view/BoardPartner.vue')
    },
    {
      path: '/customer',
      name: 'customer',
      // lazy-loaded
      component: () => import('./view/BoardCustomer.vue')
    }
  ]
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/home'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/home');
  } else {
    next();
  }
});