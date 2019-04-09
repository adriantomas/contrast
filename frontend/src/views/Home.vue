<template>
  <v-container fluid grid-list-md overflow-hidden>
    <v-layout row>
      <v-flex>
        <SideBar v-bind:noticias="noticias"/>
      </v-flex>
      <v-flex>
        <NewsList v-bind:noticias="noticias"/>
        <v-layout row justify-center>
          <v-pagination :length="length" v-model="page"></v-pagination>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>

import axios from "axios";

import SideBar from "../components/SideBar";
import NewsList from "../components/NewsList";

export default {
  data: () => ({
    noticias: null,
    errors: null,
    length: 4,
    page: 1
  }),
  components: {
    SideBar,
    NewsList
  },
  mounted() {
    axios
      .get("/api/newspaper/el")
      .then(response => {
        console.log(response);

        this.noticias = response.data;
      })
      .catch(e => {
        this.errors.push(e);
      })   
  },
};
</script>
