<template>
  <v-data-table
      :headers="headers"
      :items="vacationList"
      sort-by="id"
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
          {text: 'Employee id', value: 'employeeId'},
          {text: 'Start date', value: 'startVacation'},
          {text: 'End date', value: 'endVacation'},
        ],
        editedIndex: -1,
        vacationList: [],
        defaultItem: {},
        editedItem: {},
      }
    },

    mounted() {
      this.getVacationList();
    },

    methods: {

      getVacationList() {
        axios.get('http://localhost:8080/vacation')
        .then(result => {
          this.vacationList = result.data
        }, error => {
          console.error(error);
        });
      },

      getEmployee(id) {
        axios.get('http://localhost:8080/employee/' + id)
        .then(result => {
          this.employees = result.data
        }, error => {
          console.error(error);
        });
      },

      close() {
        this.dialog = false;
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem);
          this.editedIndex = -1
        }, 300)
      },
    }
  }
</script>

<style scoped>

</style>