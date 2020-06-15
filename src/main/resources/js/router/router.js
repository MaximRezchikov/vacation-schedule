import Vue from 'vue'
import VueRouter from 'vue-router'
import Hello from 'pages/Hello.vue'
import EmployeeList from 'pages/employee/EmployeeList.vue'

Vue.use(VueRouter);

const routes = [
  {path: '/hello', component: Hello},
  {path: '/employees', component: EmployeeList},
];

export default new VueRouter({
  mode: 'history',
  routes
});