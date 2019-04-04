<template>
  <v-card class="d-inline-block elevation-12">
    <v-navigation-drawer floating permanent stateless value="true" >
      <v-toolbar flat>
        <v-toolbar-title>contrast</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn flat href="https://github.com/vuetifyjs/vuetify/releases/latest" target="_blank">
          <span class="mr-2">Github</span>
          <v-icon>open_in_new</v-icon>
        </v-btn>
      </v-toolbar>

      <v-divider></v-divider>

      <v-layout column wrap class="mx-2">
        <v-flex>
          <v-text-field prepend-inner-icon="search" clearable label="Buscar"></v-text-field>
        </v-flex>
        <v-divider></v-divider>

        <NewspaperPicker/>

        <v-divider></v-divider>

        <DatePicker/>

        <v-divider></v-divider>

        <TagPicker/>
      </v-layout>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
import NewspaperPicker from "./NewspaperPicker";
import DatePicker from "./DatePicker";
import TagPicker from "./TagPicker";

export default {
  data: () => ({}),
  components: {
      NewspaperPicker,
      DatePicker,
      TagPicker
  }
};
</script>