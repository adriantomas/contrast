import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import News from "./views/News.vue";
import News2 from "./views/News2.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/1",
      name: "newslist1",
      component: News
    },
    {
      path: "/2",
      name: "home",
      component: News2
    },
    /*{
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ /*"./views/About.vue")
}*/
  ]
});
