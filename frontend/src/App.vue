<template>
  <v-app dark overflow-hidden>
    <v-content>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>


export default {
  name: "App",
  data: () => ({
    periodicos: ["eldiario", "elmundo", "elpais"],
    selectedNewspapers: ["eldiario", "elmundo", "elpais"],
    dateSelected1: new Date().toISOString().substr(0, 10),
    dateSelected2: new Date().toISOString().substr(0, 10),
    today: new Date().toISOString().substr(0, 10),
    datePicker1: false,
    datePicker2: false,
    etiquetas: ["deporte", "política", "nacional", "Murcia"],
    etiquetasSeleccionadas: []
  })
};
</script>
