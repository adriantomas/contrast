<template>
    <v-container fluid grid-list-md>
        <v-layout row wrap>
            <v-flex v-for="i in 9" :key="i" xs3>
                <NewsCard/>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

import NewsCard from "../components/NewsCard";

export default {
     components: {
         NewsCard
     }
}
</script>