<template>
  <v-container fluid grid-list-md overflow-hidden>
    <v-layout row>
      <v-flex>
        <SideBar
          :newspapersBase="newspapersBase"
          :newspapersSelected="newspapersSelected"
          @updateNewspapers="onUpdateNewspapers"
          :datesFacets="datesFacets"
          :initialDateSelected="initialDateSelected"
          :finalDateSelected="finalDateSelected"
          @updateInitialDate="onUpdateInitialDate"
          @updateFinalDate="onUpdateFinalDate"
          :tags="tags"
          :tagsSelected="tagsSelected"
          @updateTags="onUpdateTags"
          :fragment="fragment"
          @updateFragment="onUpdateFragment"
        />
      </v-flex>
      <v-flex>
        <NewsList :news="news"/>
        <v-layout row justify-center>
          <v-pagination :length="totalPages" v-model="page" v-on:input="this.search" :total-visible="7"></v-pagination>
        </v-layout>
      </v-flex>
    </v-layout>

    <v-dialog
      v-model="dialog"
      scrollable  
    >
      <v-card>
        <v-card-title primary-title>
          title
        </v-card-title>
        <v-card-text>
          
        </v-card-text>
        <v-card-actions>
          <v-btn color="success" @click="dialog = false">Cerrar</v-btn>
        </v-card-actions>
      </v-card>    
    </v-dialog>

  </v-container>
</template>

<script>
import axios from "axios";

import SideBar from "../components/SideBar";
import NewsList from "../components/NewsList";

export default {
  data: () => ({
    dialog: false,
    news: null,
    errors: null,
    totalPages: 0,
    page: 1,
    datesFacets: [],
    tags: [],

    newspapersBase: [],

    newspapersSelected: [],
    initialDateSelected: null,
    finalDateSelected: null,
    tagsSelected: [],
    fragment: ""
  }),
  components: {
    SideBar,
    NewsList
  },
  methods: {
    search() {
      const newspapersQuery = this.newspapersSelected.length
        ? this.newspapersSelected.map(a => a.name)
        : "*";
      const tagsQuery = this.tagsSelected.length ? this.tagsSelected : "*";
      const fragmentQuery =
        this.fragment && this.fragment.length ? this.fragment : "*";
      var query =
        "/api/content/" +
        this.newspapersSelected.map(a => a.name) +
        "/" +
        this.initialDateSelected +
        "/" +
        this.finalDateSelected +
        "/" +
        tagsQuery +
        "/" +
        fragmentQuery +
        "/" +
        (this.page - 1);
      /*  console.log("/api/content/" + encodeURIComponent(this.newspapersSelected.map(a => a.name))  + "/" + this.initialDateSelected + "/" + this.finalDateSelected + "/" + encodeURIComponent(tagsQuery) + "/" + encodeURIComponent(fragmentQuery) + "/" + (this.page -1));
      console.log("/api/content/" + this.newspapersSelected.map(a => a.name) + "/" + this.initialDateSelected + "/" + this.finalDateSelected + "/" + tagsQuery + "/" + fragmentQuery + "/" + (this.page -1)); */
      axios
        .get(
          "/api/content/" +
            encodeURIComponent(newspapersQuery) +
            "/" +
            this.initialDateSelected +
            "/" +
            this.finalDateSelected +
            "/" +
            encodeURIComponent(tagsQuery) +
            "/" +
            encodeURIComponent(fragmentQuery) +
            "/" +
            (this.page - 1)
        )
        .then(response => {
          console.log(response);
          this.news = response.data.content;
          this.totalPages = response.data.totalPages;

          this.tags.length = 0;
          response.data.facetResultPages[2].content.forEach(element => {
            this.tags.push({
              name: element.value,
              valueCount: element.valueCount
            });
          });

          var newNewspapersBase = [];
          
          response.data.facetResultPages[0].content.forEach(element => {
            newNewspapersBase.push({
              name: element.value,
              valueCount: element.valueCount
            });
          });
          var newspapersBaseArray = this.newspapersBase.map(a => a.name);
          var newNewspapersBaseArray = newNewspapersBase.map(a => a.name);
          var diff = newspapersBaseArray.filter(
            x => !newNewspapersBaseArray.includes(x)
          );
          var finalNewspapers = [];
          if (diff.length) {
            diff.forEach(element => {
              finalNewspapers.push({ name: element, valueCount: 0 });
            });
            newNewspapersBase.forEach(element => {
              finalNewspapers.push({
                name: element.name,
                valueCount: element.valueCount
              });
            });
          } else {
            finalNewspapers = newNewspapersBase;
          }
          finalNewspapers.sort((a,b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
          Array.prototype.splice.apply(this.newspapersBase, [0, finalNewspapers.length].concat(finalNewspapers));
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    onUpdateNewspapers(newspapers) {
      this.newspapersSelected = newspapers;
      this.search();
    },
    onUpdateInitialDate(date) {
      this.initialDateSelected = date;
      this.search();
    },
    onUpdateFinalDate(date) {
      this.finalDateSelected = date;
      this.search();
    },
    onUpdateTags(tags) {
      this.tagsSelected = tags;
      this.search();
    },
    onUpdateFragment(fragment) {
      this.fragment = fragment;
      this.search();
    }
  },
  mounted() {
    axios
      .get("/api/content/" + (this.page - 1))
      .then(response => {
        this.totalPages = response.data.totalPages;
        this.news = response.data.content;

        response.data.facetResultPages[0].content.forEach(element => {
          this.newspapersSelected.push({
            name: element.value,
            valueCount: element.valueCount
          });
          this.newspapersBase.push({
            name: element.value,
            valueCount: element.valueCount
          });
        });
        this.newspapersBase.sort((a,b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
        this.newspapersSelected.sort((a,b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));

        response.data.facetResultPages[1].content.forEach(element => {
          this.datesFacets.push({
            name: element.value,
            valueCount: element.valueCount
          });
          this.initialDateSelected = new Date(
            Math.min.apply(null, this.datesFacets.map(a => new Date(a.name)))
          )
            .toISOString()
            .substr(0, 10);
          this.finalDateSelected = new Date(
            Math.max.apply(null, this.datesFacets.map(a => new Date(a.name)))
          )
            .toISOString()
            .substr(0, 10);
        });

        response.data.facetResultPages[2].content.forEach(element => {
          this.tags.push({
            name: element.value,
            valueCount: element.valueCount
          });
        });
      })
      .catch(e => {
        this.errors.push(e);
      });
  }
};
</script>
