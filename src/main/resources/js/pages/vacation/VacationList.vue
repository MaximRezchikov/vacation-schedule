<template>
  <v-data-table
      :headers="headers"
      :items="vacationList"
      sort-by="id"
      mobile-breakpoint="800"
      class="elevation-1"
  >
    <template v-slot:item.actions="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          small
          @click="deleteVacation(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>Vacation List</v-toolbar-title>
        <v-divider
            class="mx-4"
            inset
            vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <v-card>
            <v-card-title>
              <span class="headline">Edit vacation</span>
            </v-card-title>

            <v-card-text>
              <v-row>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field v-model="editedItem.startVacation" label="Start date"
                                placeholder="DD.MM.YYYY"></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field v-model="editedItem.endVacation" label="End date"
                                placeholder="DD.MM.YYYY"></v-text-field>
                </v-col>
              </v-row>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="save(editedItem)">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
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