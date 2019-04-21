<template>
  <v-flex class="mb-4">
    <v-subheader>Periódicos</v-subheader>
    <v-flex v-for="(newspaper, index) in newspapersBase" :key="index">
      <v-switch
        v-model="newspapers"
        :value="newspaper"
        :label="`${newspaper.name} (${newspaper.valueCount})`"
        hide-details
        class="mt-2"
    ></v-switch>
    </v-flex>
    <!-- <v-switch
      v-for="(newspaper, index) in newspapersBase"
      v-model="newspapers"
      :value="newspaper"
      :key="index"
      :label="`${newspaper.name} (${newspaper.valueCount})`"
      hide-details
      class="mt-2"
    ></v-switch> -->
  </v-flex>
</template>

<script>
export default {
  data: () => ({}),
  props: {
    newspapersBase: Array,
    newspapersSelected: Array,
  },
  computed: {
    newspapers : {
      get () { 
        return this.newspapersSelected},
      set (v) {   
        this.$emit('updateNewspapers', v)}
    }
  },
};
</script>