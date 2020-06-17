<template>
  <v-card>
    <v-card-title>
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="employeeList"
        :search="search"
        sort-by="username"
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
            @click="deleteEmployee(item)"
        >
          mdi-delete
        </v-icon>
      </template>
      <template v-slot:item.username="{item}">
        <v-btn color="blue darken-1" text @click="showEmployeeForm(item.id)">{{item.username}}</v-btn>
      </template>
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-toolbar-title>Employee List</v-toolbar-title>
          <v-divider
              class="mx-4"
              inset
              vertical
          ></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ on }">
              <v-btn color="primary" dark class="mb-2" v-on="on">New employee</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                        v-model="editedItem.username"
                        label="Employee name"
                        :rules="[v => !!v || 'Employee is required']"
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                        v-model="editedItem.birthday"
                        label="Birthday"
                        placeholder="DD.MM.YYYY"
                        :rules="[v => !!v || 'Birthday is required',
                        v => /^\s*(3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2})\s*$/.test(v) || 'Birthday must be valid']"
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                        v-model="editedItem.personnelNumber"
                        label="Personnel Number"
                        :rules="[v => !!v || 'Personnel Number is required']"
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                        v-model="editedItem.post"
                        label="Post"
                        :rules="[v => !!v || 'Post is required']"
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                        v-model="editedItem.startDate"
                        label="Work Start Date"
                        placeholder="DD.MM.YYYY"
                        :rules="[v => !!v || 'Work Start Date is required',
                        v => /^\s*(3[01]|[12][0-9]|0?[1-9])\.(1[012]|0?[1-9])\.((?:19|20)\d{2})\s*$/.test(v) || 'Work Start Date must be valid']"
                        required
                    ></v-text-field>
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
    name: "EmployeeList",
    data() {

      return {
        search: '',
        dialog: false,
        headers: [
          {text: 'Employee name', value: 'username'},
          {text: 'Birthday', value: 'birthday'},
          {text: 'Personnel Number', value: 'personnelNumber'},
          {text: 'Post', value: 'post'},
          {text: 'Start Date', value: 'startDate'},
          {text: 'Actions', value: 'actions', sortable: false}
        ],
        editedIndex: -1,
        employeeList: [],
        defaultItem: {
          username: ''
        },
        editedItem: {
          username: ''
        },
        id: ''
      }
    },

    computed: {
      formTitle() {
        return this.editedIndex === -1 ? 'New employee' : 'Edit employee data'
      },
    },

    watch: {
      dialog(val) {
        val || this.close()
      },
    },

    mounted() {
      this.getEmployeeList()
    },

    methods: {

      getEmployeeList() {
        axios.get('http://localhost:8080/employee')
        .then(result => {
          this.employeeList = result.data
        }, error => {
          console.error(error);
        });
      },

      deleteEmployee(item) {
        const index = this.employeeList.indexOf(item);
        if (confirm('Are you sure you want to delete this person?') && this.employeeList.splice(index, 1)) {
          axios.delete('http://localhost:8080/employee/' + item.id, {
            username: this.employeeList.username
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
          axios.put('http://localhost:8080/employee/' + id, {
            username: this.editedItem.username,
            birthday: this.editedItem.birthday,
            personnelNumber: this.editedItem.personnelNumber,
            post: this.editedItem.post,
            startDate: this.editedItem.startDate
          });
          location.reload();
        } else {
          axios.post('http://localhost:8080/employee', {
            username: this.editedItem.username,
            birthday: this.editedItem.birthday,
            personnelNumber: this.editedItem.personnelNumber,
            post: this.editedItem.post,
            startDate: this.editedItem.startDate
          }).then(result => {
            this.employeeList.push(this.getEmployeeList())
          });
          this.employeeList.username = ''
          this.employeeList.birthday = ''
          this.employeeList.personnelNumber = ''
          this.employeeList.post = ''
          this.employeeList.startDate = ''
        }

        this.close()
      },

      editItem(item) {
        this.editedIndex = this.employeeList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      showEmployeeForm(id) {
        this.$router.push('/employeeCard/' + id)
        //EmployeeCard.mounted(id)
      }
    }
  }
</script>

<style scoped>

</style>