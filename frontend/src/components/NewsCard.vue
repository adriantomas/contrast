<template>
  <!-- <v-card class="ma-3">
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
  </v-card-actions>-->

  <!-- <v-slide-y-transition>
          <v-card-text v-show="show">
            I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
          </v-card-text>
  </v-slide-y-transition>-->
  <!--   </v-card> -->
  <v-card class="ma-3" color="#26c6da">
    <v-card-title>
      <v-icon large left>mdi-newspaper</v-icon>
      <span class="title font-weight-light">{{ story.newspaper }}</span>
    </v-card-title>

    <v-card-text class="headline font-weight-bold">{{ story.headline }}</v-card-text>

    <v-card-actions>
      <v-layout row wrap align-center>
        <!-- <v-list-tile class="grow"> -->
        <!-- <v-list-tile-avatar color="grey darken-3">
          <v-img
            class="elevation-6"
            src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
          ></v-img>
        </v-list-tile-avatar>

        <v-list-tile-content>
          <v-list-tile-title>Evan You</v-list-tile-title>
        </v-list-tile-content>-->
        <v-btn @click.stop="readNews">Ampliar</v-btn>
        <v-spacer></v-spacer>
        <!-- <v-layout
          align-center
          justify-end
        >-->
        <v-icon class="mr-1">mdi-calendar</v-icon>
        <span class="subheading mr-2">{{ dateFormat(story.date) }}</span>
        <!-- <span class="mr-1">·</span>
          <v-icon class="mr-1">mdi-share-variant</v-icon>
        <span class="subheading">45</span>-->
        <!--  </v-layout>
        </v-list-tile>-->
      </v-layout>
    </v-card-actions>

    <v-dialog v-model="dialog" scrollable width="60%">
      <v-card>
        <v-card-title primary-title class="headline">{{ story.headline }}</v-card-title>
        <v-card-text v-html="story.descriptionRaw"></v-card-text>
        <!-- <v-card-actions>
          <v-btn color="success">text</v-btn>
          <v-divider></v-divider>
          <v-btn color="success" @click="dialog = false">Cerrar</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success">Texta</v-btn>
        </v-card-actions>-->
        <v-layout align-center>
          <v-item-group v-model="window" class="shrink mr-4" mandatory tag="v-flex">
            <v-item v-for="n in length" :key="n">
              <div slot-scope="{ active, toggle }">
                <v-btn :input-value="active" icon @click="toggle">
                  <v-icon small>mdi-record</v-icon>
                </v-btn>
              </div>
            </v-item>
          </v-item-group>

          <v-flex>
            <v-window v-model="window" class="elevation-1" vertical>
              <v-window-item v-for="n in length" :key="n">
                <v-card flat>
                  <v-card-title>
                    <v-icon large left>mdi-newspaper</v-icon>
                    <span class="title font-weight-light">{{ story.newspaper }}</span>
                    <v-spacer></v-spacer>
                    <v-icon class="mr-1">mdi-calendar</v-icon>
                    <span class="subheading mr-2">{{ dateFormat(story.date) }}</span>
                  </v-card-title>
                  <v-card-text class="headline font-weight-bold">{{ story.headline }}</v-card-text>
                  <!-- <v-card-text>
                    <v-layout align-center mb-3>
                      <v-avatar color="grey" class="mr-3"></v-avatar>
                      <strong class="title">Title {{ n }}</strong>
                      <v-spacer></v-spacer>
                      <v-btn icon>
                        <v-icon>mdi-account</v-icon>
                      </v-btn>
                    </v-layout>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                  </v-card-text>-->
                  <v-divider></v-divider>
                  <v-icon>label</v-icon>
                  <span>Etiquetas</span>
                  <v-flex wrap>
                    <v-chip
                      small
                      disabled
                      v-for="(item, index) in story.categories"
                      :key="index"
                    >{{item}}</v-chip>
                  </v-flex>
                </v-card>
              </v-window-item>
            </v-window>
          </v-flex>
        </v-layout>

        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="success" @click="dialog = false">Cerrar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import moment from "moment";

export default {
  data: () => ({
    dialog: false,
    window: 0,
    length: 3
  }),
  methods: {
    /* dateFormat(date) {
      return moment(date).format("LLLL");
    }, */
    dateFormat(date) {
      return moment(date).format("DD/MM/YYYY");
    },
    readNews() {
      /* this.$emit('readNews', story); */
      this.dialog = true;
    }
  },
  props: {
    story: Object
  }
};
</script>
