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
        v-model="initialDate"
        no-title
        @input="datePicker1 = false"
        first-day-of-week="1"
        locale="es-es"
        reactive
        :max="finalDate"
        :min="min"
        :events="events"
        :event-color="eventsColor"
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
      <v-tooltip bottom z-index="1000">
        <template v-slot:activator="{ on }">
          <v-date-picker
            v-model="finalDate"
            no-title
            @input="datePicker2 = false"
            first-day-of-week="1"
            locale="es-es"
            reactive
            :max="max"
            :min="initialDate"
            :events="events"
            :event-color="eventsColor"
            v-on="on"
          ></v-date-picker>
        </template>
        <span>TOOLTIP</span>
      </v-tooltip>
    </v-menu>
  </v-flex>
</template>

<script>
import moment from "moment";
moment.locale("es");

export default {
  data: () => ({
    datePicker1: false,
    datePicker2: false
  }),
  computed: {
    formattedDate1() {
      return this.initialDate
        ? moment(this.initialDate).format("DD/MM/YYYY")
        : "";
    },
    formattedDate2() {
      return this.finalDate ? moment(this.finalDate).format("DD/MM/YYYY") : "";
    },
    min() {
      return this.datesFacets.length
        ? new Date(
            Math.min.apply(null, this.datesFacets.map(a => new Date(a.name)))
          )
            .toISOString()
            .substr(0, 10)
        : undefined;
    },
    max() {
      return this.datesFacets.length
        ? new Date(
            Math.max.apply(null, this.datesFacets.map(a => new Date(a.name)))
          )
            .toISOString()
            .substr(0, 10)
        : undefined;
    },
    events() {
      if (this.datesFacets.length) {
        var datesOrdered = this.datesFacets;
        datesOrdered.sort(
          (a, b) => parseInt(a.valueCount) - parseInt(b.valueCount)
        );
        return datesOrdered.map(a =>
          new Date(a.name).toISOString().substr(0, 10)
        );
      }
      return null;
    },
    initialDate: {
      get() {
        return this.initialDateSelected;
      },
      set(v) {
        this.$emit("updateInitialDate", v);
      }
    },
    finalDate: {
      get() {
        return this.finalDateSelected;
      },
      set(v) {
        this.$emit("updateFinalDate", v);
      }
    }
  },
  props: {
    datesFacets: Array,
    initialDateSelected: String,
    finalDateSelected: String
  },
  methods: {
    eventsColor(date) {
      if (this.datesFacets.length) {
        var datesOrdered = this.events;
        if (datesOrdered.includes(date)) {
          if (datesOrdered.indexOf(date) < this.datesFacets.length / 3) {
            return "amber lighten-5";
          } else if (
            datesOrdered.indexOf(date) <
            (this.datesFacets.length / 3) * 2
          ) {
            return "amber darken-1";
          } else {
            return "amber darken-4";
          }
        } else {
          return "grey";
        }
      }
    }
  }
};
</script>