<template>
  <v-card>
    <v-card-title>
      <v-btn color="blue darken-1" text @click="downloadExcelVacationReport" data-marker="download-projects-excel">Excel
        Report
      </v-btn>
      <v-spacer></v-spacer>
    </v-card-title>
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
    </v-data-table>
  </v-card>
</template>

<script>
  import axios from "axios";

  const titles = ['Employee name', 'Start date', 'End date'];

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
        columnTitles: [],
        vacationIds: [],

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

      getIds() {
        for (let i of this.vacationList) {
          this.vacationIds.push(i.id)
        }
      },

      downloadExcelVacationReport() {
        this.getIds();
        let method = 'post';
        axios({
          url: 'http://localhost:8080/report/vacation/vacationExcel',
          method: 'POST',
          responseType: 'blob',
          data: {
            vacationIds: this.vacationIds,
            columnTitles: ['Employee name', 'Start date', 'End date', 'Remaining period'],
          }

        })
        .then(response => {
              const downloadUrl = window.URL.createObjectURL(new Blob([response.data]));
              const link = document.createElement('a');
              link.href = downloadUrl;
              link.setAttribute('download', 'VacationReport.xlsx');
              document.body.appendChild(link);
              link.click();
              link.remove();
              this.vacationIds.length = 0
            },
            error => {
              console.error(error);
            });
      }
    }
  }
</script>

<style scoped>

</style>