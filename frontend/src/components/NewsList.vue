<template>
    <v-container fluid grid-list-md>
        <v-layout row wrap>
            <v-flex v-for="story in news" :key="story.id" xs4>
                <NewsCard :headline="story.headline" :newspaper="story.newspaper" :date="story.date"/>
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
         news: Array,
     }
}
</script>