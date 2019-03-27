<template>
  <v-app dark>
    <v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>contrast</span>
        <span class="font-weight-light"></span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field
      prepend-inner-icon="search"
      >
      </v-text-field>
      <v-btn icon class="hidden-xs-only">
        <v-icon>md-dropdown</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        flat
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>open_in_new</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
     <!-- <v-expansion-panel 
      v-model="panel"
      expand>
    <v-expansion-panel-content>
      <v-autocomplete
        v-model="model"
        :hint="Hola"
        :items="tags"
        :label="HOLA"
        prepend-icon="search"
      >
      </v-autocomplete>
      <v-btn icon class="hidden-xs-only">
        <v-icon>search</v-icon>
      </v-btn>
    </v-expansion-panel-content>
    </v-expansion-panel>  -->
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data () {
    return {
      //
    }
  }
}
</script>
