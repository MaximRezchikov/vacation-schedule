<template>
  <v-card>
    <v-card-title>
      Vacation of {{employee.username}}
      <v-spacer></v-spacer>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="employee.vacations"
        :items-per-page="10"
        class="elevation-1"
    >
      <template v-slot:item.vacations="{item}">
        {{item.startVacation}}
      </template>
      <template v-slot:item.vacations="{item}">
        {{item.endVacation}}
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
  import axios from "axios";

  export default {
    name: "EmployeeCard",

    data() {
      return {
        headers: [
          {text: 'Start date', value: 'startVacation'},
          {text: 'End date', value: 'endVacation'},
        ],
        employee: '',
        id: this.$route.params.id,
        vacations: []
      }
    },

    mounted() {
      axios.get('http://localhost:8080/employee/' + this.id)
      .then(result => {
        this.employee = result.data
      }, error => {
        console.error(error);
      });
    },

    methods: {}
  }
</script>

<style scoped>

</style>