<template>
  <v-card class="ma-3" color="cyan darken-2">
    <v-card-title>
      <v-icon large left>mdi-newspaper</v-icon>
      <span class="title font-weight-light">{{ story.newspaper }}</span>
    </v-card-title>

    <v-card-text class="headline font-weight-bold">
      {{
      story.headline
      }}
    </v-card-text>

    <v-card-actions>
      <v-layout row wrap align-center>
        <v-btn @click.stop="readNews">Ampliar</v-btn>
        <v-spacer></v-spacer>
        <v-icon class="mr-1">mdi-calendar</v-icon>
        <span class="subheading mr-2">{{ dateFormat(story.date) }}</span>
      </v-layout>
    </v-card-actions>

    <v-dialog v-model="dialog" scrollable width="60%">
      <v-card>
        <v-card-title primary-title class="headline">
          {{
          story.headline
          }}
        </v-card-title>

        <v-card-text>
          <span v-html="story.descriptionRaw"></span>
          <!-- <v-layout row wrap align-center>
            <v-icon left>mdi-newspaper</v-icon>
            <span class="body-2">{{dateFormat(story.date)}}</span>
            <v-spacer></v-spacer>
          </v-layout> -->
          <v-divider class="my-3"></v-divider>
          <v-flex>
            <p class="subheading font-weight-bold">Noticias similares</p>
            <v-layout align-center>
              <v-item-group v-model="window" class="shrink mr-4" mandatory tag="v-flex">
                <v-item v-for="relatedStory in relatedNews" :key="relatedStory.id">
                  <div slot-scope="{ active, toggle }">
                    <v-btn :input-value="active" icon @click="toggle">
                      <v-icon small>mdi-record</v-icon>
                    </v-btn>
                  </div>
                </v-item>
              </v-item-group>

              <v-flex>
                <v-window v-model="window" class="elevation-1" vertical>
                  <v-window-item v-for="relatedStory in relatedNews" :key="relatedStory.id">
                    <v-card flat class="mr-3">
                      <v-card-title>
                        <v-icon left>mdi-newspaper</v-icon>
                        <span class="subheading font-weight-light">
                          {{
                          relatedStory.newspaper
                          }}
                        </span>
                        <v-spacer></v-spacer>
                        <v-icon class="mr-1">mdi-calendar</v-icon>
                        <span class="body-1 mr-2">
                          {{
                          dateFormat(relatedStory.date)
                          }}
                        </span>
                      </v-card-title>
                      <v-card-text class="title font-weight-light">
                        {{
                        relatedStory.headline
                        }}
                      </v-card-text>
                      <v-divider></v-divider>
                      <v-layout row>
                        <v-icon left>label</v-icon>
                        <span class="body-2 ma-2">Entidades en común</span>
                      </v-layout>
                      <v-flex wrap class="pb-2">
                        <v-chip
                          class="caption"
                          small
                          disabled
                          v-for="(item, index) in tagsMatched(
                          relatedStory.categories
                        )"
                          :key="index"
                        >{{ item }}</v-chip>
                      </v-flex>
                    </v-card>
                  </v-window-item>
                </v-window>
              </v-flex>
            </v-layout>
          </v-flex>
        </v-card-text>
        <!-- <v-card-actions>
          <v-btn color="success">text</v-btn>
          <v-divider></v-divider>
          <v-btn color="success" @click="dialog = false">Cerrar</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success">Texta</v-btn>
        </v-card-actions>-->

        <v-card-actions>
          <v-btn small flat :href="story.link">
            Visitar la fuente
            <v-icon>open_in_new</v-icon>
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success" @click="dialog = false">Cerrar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import moment from "moment";
import axios from "axios";

export default {
  data: () => ({
    dialog: false,
    window: 0,
    relatedNews: []
  }),
  methods: {
    dateFormat(date) {
      return moment(date).format("DD/MM/YYYY");
    },
    readNews() {
      this.dialog = true;
      axios
        .get(
          "/api/related/" + encodeURIComponent(this.story.categories.join(","))
        )
        .then(response => {
          this.relatedNews = response.data.response.docs;
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    tagsMatched(tags) {
      return this.story.categories.filter(value => tags.includes(value));
    }
  },
  props: {
    story: Object
  }
};
</script>
