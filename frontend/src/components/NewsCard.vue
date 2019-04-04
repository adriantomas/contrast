<template>
  <v-card class="ma-3">
    <v-card-title>
      <span class="title">Titular</span>
      <v-spacer></v-spacer>
      <div>
        <span class="grey--text">Fecha</span>
        <br>
        <span class="grey--text">Periódico</span>
      </div>
    </v-card-title>
    <v-slide-y-transition>
      <v-card-text
        :class="{'text-truncate': show}"
        class="black--text white text-xs-left"
        min-height="200px"
      >Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris diam massa, vehicula quis vulputate quis, auctor non lectus. Mauris vitae erat eu sapien lacinia dictum ut quis leo. Vivamus gravida est non nulla dictum, vitae gravida lacus efficitur. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum ac imperdiet erat, at sagittis lorem. Suspendisse consequat dolor at augue hendrerit vestibulum. Nam ut interdum ligula, sit amet aliquet quam. In tempus ornare velit nec mattis. Pellentesque libero eros, malesuada ac scelerisque quis, lobortis at nibh. Maecenas at dui augue</v-card-text>
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
    </v-slide-y-transition>-->
  </v-card>
</template>

<script>
export default {
  data: () => ({
    show: false
  })
};
</script>
