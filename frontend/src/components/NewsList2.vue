<template>
  <v-container fluid grid-list-md text-xs-center>
    <!--<v-layout row wrap>
      <v-flex xs12>
        <v-card color="primary">
          <v-card-text class="px-0">12</v-card-text>
        </v-card>
      </v-flex>
      <v-flex v-for="i in 2" :key="`6${i}`" xs6>
        <v-card color="secondary">
          <v-card-text class="px-0">6</v-card-text>
        </v-card>
      </v-flex>
    </v-layout>-->
    <v-layout v-for="j in 3" :key="`4${j}`" row wrap>
      <v-flex v-for="i in 3" :key="`4${i}`" xs4>
        <!-- <v-card color="primary" hover :href="wwww.google.es">
          <v-card-text class="px-0">{{ j+ i}}</v-card-text>
        </v-card> -->

        <v-card class="ma-3">
        <v-card-title>   
          <!-- primary-title -->        
            <span class="headline">Titular</span>
            <v-spacer></v-spacer>
            <div>
              <span class="grey--text">Fecha</span><br>
              <span class="grey--text">Periódico</span>
            </div>
        </v-card-title>
      <v-slide-y-transition>
        <v-card-text :class="{'text-truncate': show}" class="black--text white text-xs-left" height="60%"> 
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris diam massa, vehicula quis vulputate quis, auctor non lectus. Mauris vitae erat eu sapien lacinia dictum ut quis leo. Vivamus gravida est non nulla dictum, vitae gravida lacus efficitur. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum ac imperdiet erat, at sagittis lorem. Suspendisse consequat dolor at augue hendrerit vestibulum. Nam ut interdum ligula, sit amet aliquet quam. In tempus ornare velit nec mattis. Pellentesque libero eros, malesuada ac scelerisque quis, lobortis at nibh. Maecenas at dui augue
        </v-card-text>
</v-slide-y-transition>
        <v-card-actions>
          <v-btn flat>Ampliar</v-btn>
          <v-spacer></v-spacer>
          <v-btn icon @click="show = !show">
            <v-icon>{{ show ? 'keyboard_arrow_down' : 'keyboard_arrow_up' }}</v-icon>
          </v-btn>
        </v-card-actions>

        <!-- <v-slide-y-transition>
          <v-card-text v-show="show">
            I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
          </v-card-text>
        </v-slide-y-transition> -->
      </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    show: false
  })
}
</script>
