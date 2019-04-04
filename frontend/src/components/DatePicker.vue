<template>
  <v-flex class="mb-2">
    <v-subheader>Fecha</v-subheader>
    <v-menu
      offset-y
      v-model="datePicker1"
      :close-on-content-click="false"
      :nudge-right="40"
      lazy
      transition="scale-transition"
      full-width
    >
      <template v-slot:activator="{ on }">
        <v-text-field
          label="Desde"
          v-model="formattedDate1"
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
        :max="dateSelected2"
      ></v-date-picker>
    </v-menu>
    <v-menu
      offset-y
      v-model="datePicker2"
      :close-on-content-click="false"
      :nudge-right="40"
      lazy
      transition="scale-transition"
      full-width
    >
      <template v-slot:activator="{ on }">
        <v-text-field
          label="Hasta"
          v-model="formattedDate2"
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
        :max="today"
      ></v-date-picker>
    </v-menu>
  </v-flex>
</template>

<script>
export default {
     data: () => ({})
}
</script>