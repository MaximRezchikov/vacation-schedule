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
            <template v-slot:activator="{ on }">
              <v-btn color="primary" dark class="mb-2" v-on="on">New vacation</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
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
  </v-card>
</template>

<script>
  import axios from "axios";

  export default {
    name: "EmployeeCard",

    data() {
      return {
        dialog: false,
        headers: [
          {text: 'Start date', value: 'startVacation'},
          {text: 'End date', value: 'endVacation'},
          {text: 'Actions', value: 'actions', sortable: false}
        ],
        editedIndex: -1,
        employee: '',
        id: this.$route.params.id,
        vacations: [],
        vacationList: [],
        defaultItem: {
        },
        editedItem: {
        },
      }
    },

    computed: {
      formTitle() {
        return this.editedIndex === -1 ? 'New vacation' : 'Edit vacation'
      },
    },

    watch: {
      dialog(val) {
        val || this.close()
      },
    },

    mounted() {
      this.getEmployee();
    },

    methods: {

      getEmployee() {
        axios.get('http://localhost:8080/employee/' + this.id)
        .then(result => {
          this.employee = result.data
        }, error => {
          console.error(error);
        });
      },

      deleteVacation(item) {
        const index = this.vacations.indexOf(item);
        if (confirm('Are you sure you want to delete this vacation?') && this.vacations.splice(index, 1)) {
          axios.delete('http://localhost:8080/vacation/' + item.id, {
            id: this.vacations.id
          });
        }
      },

      close() {
        this.dialog = false;
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem);
          this.editedIndex = -1
        }, 300)
      },

      save(item) {

        let id = item.id;

        if (id) {
          axios.put('http://localhost:8080/vacation/' + id, {
            startVacation: this.editedItem.startVacation,
            endVacation: this.editedItem.endVacation,
          });
          location.reload();
        } else {
          let employeeId = this.id;
          axios.post('http://localhost:8080/employee/' + employeeId + '/vacation', {
            startVacation: this.editedItem.startVacation,
            endVacation: this.editedItem.endVacation,
          }).then(result => {
            this.employee.vacations.push(this.getEmployee())
          });
          this.vacations.startVacation = ''
          this.vacations.endVacation = ''
        }

        this.close()
      },

      editItem(item) {
        this.editedIndex = this.vacations.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
    }
  }
</script>

<style scoped>

</style>