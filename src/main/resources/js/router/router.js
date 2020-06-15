import Vue from 'vue'
import VueRouter from 'vue-router'
import Hello from 'pages/Hello.vue'
import EmployeeList from 'pages/employee/EmployeeList.vue'
import EmployeeCard from 'pages/employee/EmployeeCard.vue'

Vue.use(VueRouter);

const routes = [
  {path: '/hello', component: Hello},
  {path: '/employees', component: EmployeeList},
  {path: '/employeeCard/:id', component: EmployeeCard},
];

export default new VueRouter({
  mode: 'history',
  routes
});