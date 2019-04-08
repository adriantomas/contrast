<template>
  <v-container fluid grid-list-md>
    <v-layout row fill-height>
      <v-flex>
        <SideBar/>
      </v-flex>
      <v-flex>
        <NewsList/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import SideBar from "../components/SideBar";
import NewsList from "../components/NewsList";

export default {
  components: {
    SideBar,
    NewsList
  }
};
</script>
