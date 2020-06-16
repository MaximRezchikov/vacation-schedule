<template>
  <v-app>
    <v-app-bar app
               color="green"
    >
      <v-toolbar-title>Vacation Schedule</v-toolbar-title>
      <v-menu
          right
          bottom
      >
        <template v-slot:activator="{ on }">
          <v-btn icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item-title>
            <v-btn text
                   :disabled="$route.path === '/hello'"
                   @click="showHelloPage">
              Home Page
            </v-btn>
          </v-list-item-title>
          <v-list-item-title>
            <v-btn text
                   :disabled="$route.path === '/employees'"
                   @click="showEmployeeList">
              Employee List
            </v-btn>
          </v-list-item-title>
          <v-list-item-title>
            <v-btn text
                   :disabled="$route.path === '/vacations'"
                   @click="showVacationList">
              Vacation List
            </v-btn>
          </v-list-item-title>
        </v-list>

      </v-menu>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    name: "App",
    methods: {
      showHelloPage() {
        this.$router.push('/hello')
      },
      showEmployeeList() {
        this.$router.push('/employees')
      },
      showVacationList() {
        this.$router.push('/vacations')
      }
    }
  }
</script>

<style scoped>

</style>