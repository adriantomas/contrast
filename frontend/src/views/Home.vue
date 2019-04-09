<template>
  <v-container fluid grid-list-md overflow-hidden>
    <v-layout row>
      <v-flex>
        <SideBar/>
      </v-flex>
      <v-flex>
        <NewsList/>
        <v-layout row justify-center>
          <v-pagination :length="length" v-model="page"></v-pagination>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import SideBar from "../components/SideBar";
import NewsList from "../components/NewsList";

export default {
  data: () => ({
    length: 4,
    page: 1
  }),
  components: {
    SideBar,
    NewsList
  }
};
</script>
