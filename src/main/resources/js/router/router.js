import Vue from 'vue'
import VueRouter from 'vue-router'
import Hello from 'pages/Hello.vue'
import EmployeeList from 'pages/employee/EmployeeList.vue'
import VacationList from 'pages/vacation/VacationList.vue'
import EmployeeCard from 'pages/employee/EmployeeCard.vue'
import DateFormat from 'pages/components/DateFormat.vue'


Vue.use(VueRouter);

const routes = [
  {path: '/hello', component: Hello},
  {path: '/employees', component: EmployeeList},
  {path: '/employeeCard/:id', component: EmployeeCard},
  {path: '/vacations', component: VacationList},
  {path: '/date', component: DateFormat},
  { path: '*', redirect: '/hello' }
];

export default new VueRouter({
  mode: 'history',
  routes
});