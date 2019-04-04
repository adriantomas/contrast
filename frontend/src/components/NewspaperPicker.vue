<template>
  <v-flex class="mb-4">
    <v-subheader>Periódicos</v-subheader>
    <v-switch
      v-for="newspaper in periodicos"
      v-model="selectedNewspapers"
      :value="newspaper"
      :key="newspaper"
      :label="newspaper"
      hide-details
      class="mt-2"
    ></v-switch>
  </v-flex>
</template>

<script>
export default {
  data: () => ({})
};
</script>