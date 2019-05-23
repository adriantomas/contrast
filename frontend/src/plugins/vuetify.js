import Vue from "vue";
import Vuetify from "vuetify/lib";
import "vuetify/src/stylus/app.styl";
import es from "vuetify/es5/locale/es";
import "@mdi/font/css/materialdesignicons.css"; // Ensure you are using css-loader

Vue.use(Vuetify, {
  iconfont: "mdi",
  lang: {
    locales: { es },
    current: "es"
  }
});
