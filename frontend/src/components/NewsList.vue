<template>
    <v-container fluid grid-list-md>
        <v-layout row wrap>
            <v-flex v-for="noticia in noticias" :key="noticia.headline" xs4>
                <!-- <NewsCard v-for="noticia in noticias" :key="noticia.headline" v-bind:headline="noticia.headline" xs4/> -->
                <NewsCard v-bind:headline="noticia.headline" v-bind:newspaper="noticia.newspaper" v-bind:date="noticia.date" xs4/>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

import NewsCard from "../components/NewsCard";

export default {
    data() {
        return {
        }
    },
     components: {
         NewsCard
     },
     props: {
         noticias: Array,

     }
}
</script>