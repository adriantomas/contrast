<template>
  <v-card class="d-inline-block elevation-12">
    <v-navigation-drawer floating permanent stateless value="true">
      <v-toolbar flat>
        <v-toolbar-title>contrast</v-toolbar-title>
      </v-toolbar>

      <v-divider></v-divider>

      <v-layout column wrap class="mx-2">
        <v-flex>
          <v-text-field
            prepend-inner-icon="search"
            clearable
            label="Buscar"
            v-model="fragmentSelected"
          ></v-text-field>
        </v-flex>
        <v-divider></v-divider>

        <NewspaperPicker
          :newspapers-base="newspapersBase"
          :newspapers-selected="newspapersSelected"
          @updateNewspapers="onUpdateNewspapers"
        />

        <v-divider></v-divider>

        <DatePicker
          :dates-facets="datesFacets"
          :initial-date-selected="initialDateSelected"
          :final-date-selected="finalDateSelected"
          @updateInitialDate="onUpdateInitialDate"
          @updateFinalDate="onUpdateFinalDate"
        />

        <v-divider></v-divider>

        <TagPicker
          :tags="tags"
          :tags-selected="tagsSelected"
          @updateTags="onUpdateTags"
        />

        <!--  <v-divider></v-divider>

        <v-btn flat href="https://github.com/adriantomas/contrast" target="_blank">
          <span class="mr-2">Github</span>
          <v-icon>open_in_new</v-icon>
        </v-btn> -->
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
  },
  props: {
    newspapersSelected: Array,
    newspapersBase: Array,
    datesFacets: Array,
    initialDateSelected: String,
    finalDateSelected: String,
    tags: Array,
    tagsSelected: Array,
    fragment: String
  },
  methods: {
    onUpdateNewspapers(newspapers) {
      this.$emit("updateNewspapers", newspapers);
    },
    onUpdateInitialDate(date) {
      this.$emit("updateInitialDate", date);
    },
    onUpdateFinalDate(date) {
      this.$emit("updateFinalDate", date);
    },
    onUpdateTags(tags) {
      this.$emit("updateTags", tags);
    }
  },
  computed: {
    fragmentSelected: {
      get() {
        return this.fragment;
      },
      set(v) {
        this.$emit("updateFragment", v);
      }
    }
  }
};
</script>
