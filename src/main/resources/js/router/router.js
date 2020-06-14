import Vue from 'vue'
import VueRouter from 'vue-router'
import Hello from 'pages/Hello.vue'

Vue.use(VueRouter);

const routes = [
  {path: '/hello', component: Hello},
];

export default new VueRouter({
  mode: 'history',
  routes
});