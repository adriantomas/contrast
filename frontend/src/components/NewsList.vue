<template>
    <v-container fluid grid-list-md>
        <v-layout row wrap>
            <v-flex v-for="story in news" :key="story.id" xs4>
                <NewsCard :story="story" @readNews="onClickReadNews"/>
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
     },
     methods: {
        onClickReadNews (news) {
            this.$emit("readNews", news);
        }
     }
}
</script>