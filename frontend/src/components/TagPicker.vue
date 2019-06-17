<template>
  <v-flex>
    <v-subheader>Entidades</v-subheader>
    <v-autocomplete
      v-model="tagsSelection"
      :items="tags"
      chips
      color="accent"
      label="Selecciona"
      multiple
      item-text="name"
      item-value="name"
    >
      <template v-slot:selection="data">
        <v-chip
          :selected="data.selected"
          close
          class="chip--select-multi"
          @input="remove(data.item.name)"
        >
          <v-avatar>
            <v-icon>label</v-icon>
          </v-avatar>
          {{ data.item.name }}
        </v-chip>
      </template>
      <template v-slot:item="data">
        <span v-html="data.item.name"> </span>
        <v-spacer></v-spacer>
        <span class="body-2 grey--text"> ({{ data.item.valueCount }})</span>
      </template>
    </v-autocomplete>
  </v-flex>
</template>

<script>
export default {
  data: () => ({}),
  methods: {
    remove(item) {
      const index = this.tagsSelection.indexOf(item);
      if (index >= 0) {
        this.tagsSelection.splice(index, 1);
        this.$emit("updateTags", this.tagsSelection);
      }
    }
  },
  computed: {
    tagsNames() {
      return this.tags.map(a => a.name);
    },
    tagsSelection: {
      get() {
        return this.tagsSelected;
      },
      set(v) {
        this.$emit("updateTags", v);
      }
    }
  },
  props: {
    tagsSelected: Array,
    tags: Array
  }
};
</script>
