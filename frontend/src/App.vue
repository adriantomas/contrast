<template>
  <v-app dark>
    <v-navigation-drawer app clipped right>
      <div>
        <span class="title">Periódicos</span>
        <v-switch
          v-for="newspaper in periodicos"
          v-model="selectedNewspapers"
          :value="newspaper"
          :key="newspaper"
          :label="newspaper"
        ></v-switch>
      </div>
      <div>
        <span class="title">Fecha</span>
        <v-menu 
          offset-y 
          v-model="datePicker1"
          :close-on-content-click="false"
          :nudge-right="40"
          lazy
          transition="scale-transition"
          full-width>
          <template v-slot:activator="{ on }">
            <v-text-field
              label="Desde"
              v-model="formattedDate1"
              hint="Algo por aqui"
              persistent-hint
              prepend-icon="event"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker 
            v-model="dateSelected1" 
            no-title  
            @input="datePicker1 = false"
            first-day-of-week="1"
            locale="es-es"
            reactive
            :max="dateSelected2"></v-date-picker> 
        </v-menu>
        <v-menu 
          offset-y 
          v-model="datePicker2"
          :close-on-content-click="false"
          :nudge-right="40"
          lazy
          transition="scale-transition"
          full-width>
          <template v-slot:activator="{ on }">
            <v-text-field
              label="Hasta"
              v-model="formattedDate2"
              hint="Algo por aqui"
              persistent-hint
              prepend-icon="event"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker 
            v-model="dateSelected2" 
            no-title  
            @input="datePicker2 = false"
            first-day-of-week="1"
            locale="es-es"
            reactive
            :max="today"></v-date-picker> 
        </v-menu>
      </div>

      <div>
        <v-subheader>Etiquetas</v-subheader>
        <v-autocomplete
          v-model="etiquetasSeleccionadas"
          :items="etiquetas"
          box
          chips
          color="accent"
          label="Selecciona"
          multiple
          >
          <template v-slot:selection="data">
            <v-chip
              :selected="data.selected"
              close
              class="chip--select-multi"
              @input="remove(data.item)"
              >
              <v-avatar>
                <v-icon>label</v-icon>
              </v-avatar>
              {{ data.item }}
            </v-chip>
          </template>
          
        </v-autocomplete>
      </div>

    </v-navigation-drawer>

    <v-toolbar app fixed clipped-right>
      <v-toolbar-title class="headline text-uppercase">
        <span>contrast</span>
        <span class="font-weight-light"></span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field prepend-inner-icon="search"></v-text-field>
      <v-btn icon class="hidden-xs-only">
        <v-icon>md-dropdown</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn flat href="https://github.com/vuetifyjs/vuetify/releases/latest" target="_blank">
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
      </v-expansion-panel>-->
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
import moment from 'moment'

export default {
  name: "App",
  data: () => ({
    periodicos: ["eldiario", "elmundo", "elpais"],
    selectedNewspapers: ["eldiario", "elmundo", "elpais"],
    dateSelected1: new Date().toISOString().substr(0,10),
    dateSelected2: new Date().toISOString().substr(0,10),
    today: new Date().toISOString().substr(0,10),
    datePicker1: false,
    datePicker2: false,
    etiquetas: ["deporte", "política", "nacional", "Murcia"],
    etiquetasSeleccionadas: []
  }),

  computed: {
    formattedDate1 () {
      return this.dateSelected1 ? moment(this.dateSelected1).format('DD/MM/YYYY') : ''
    },
    formattedDate2 () {
      return this.dateSelected2 ? moment(this.dateSelected2).format('DD/MM/YYYY') : ''
    }
  },
  methods: {
      remove (item) {
        const index = this.etiquetasSeleccionadas.indexOf(item)
        if (index >= 0) this.etiquetasSeleccionadas.splice(index, 1)
      }
    }
};
</script>
