<template>
  <v-flex>
    <v-subheader>Etiquetas</v-subheader>
    <v-autocomplete
      v-model="etiquetasSeleccionadas"
      :items="etiquetas"
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
  </v-flex>
</template>
<script>
export default {
  data: () => ({})
};
</script>