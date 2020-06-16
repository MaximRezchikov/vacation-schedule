<template>
  <v-data-table
      :headers="headers"
      :items="vacationList"
      sort-by="employee.username"
      mobile-breakpoint="800"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>Vacation List</v-toolbar-title>
        <v-divider
            class="mx-4"
            inset
            vertical
        ></v-divider>
        <v-spacer></v-spacer>
      </v-toolbar>
    </template>
    <template v-slot:item.vacationList="{item}">

    </template>
  </v-data-table>
</template>

<script>
  import axios from "axios";

  export default {
    name: "VacationList",
    data() {

      return {
        dialog: false,
        headers: [
          {text: 'Employee name', value: 'employee.username'},
          {text: 'Start date', value: 'startVacation'},
          {text: 'End date', value: 'endVacation'},
        ],
        vacationList: [],
      }
    },

    mounted() {
      this.getVacationList();
    },

    methods: {

      getVacationList() {
        axios.get('http://localhost:8080/vacation/employeeVacation')
        .then(result => {
          this.vacationList = result.data
        }, error => {
          console.error(error);
        });
      },
    }
  }
</script>

<style scoped>

</style>