<template>
  <!-- <v-container>  
    <v-card> -->
      <v-container fluid grid-list-md>
        <v-layout row wrap>
          <SideBar/>
        <v-flex v-for="i in 9" :key="i" xs3>
          <NewsCard/>
        </v-flex>

              </v-layout>
       
        <!-- <v-layout v-for="j in 3" :key="`4${j}`" row wrap>
        <v-flex v-for="i in 3" :key="`4${i}`" xs4>-->

        <!-- </v-flex>
        </v-layout> -->
      </v-container>
    <!-- </v-card>
  </v-container> -->
</template>

<script>
/* import HelloWorld from '../components/HelloWorld' */
import SideBar from "../components/SideBar";
import NewsCard from "../components/NewsCard";

export default {
  components: {
    /* HelloWorld */
    SideBar,
    NewsCard
  }
};
</script>
