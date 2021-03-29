<template>
  <div>
    <middle-nav v-bind:propsdata="navInfo"></middle-nav>
    <v-container>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col>
          <h1 style="margin:50px 0">정처기 합격가즈아</h1>
          <v-row
            justify="space-between"
            style="font-size:1.5rem; margin-bottom:50px;"
          >
            정처기 원콤을 목표로 하는 스터디입니다
            <span
              ><v-icon>mdi-account</v-icon>5
              <v-icon>mdi-crown</v-icon>dldkfma</span
            >
          </v-row>

          <v-row justify="space-around" style="margin-bottom:50px;">
            <v-btn icon color="black" x-large @click="ToGroupHome"
              ><v-icon>mdi-home</v-icon> 홈</v-btn
            >
            <v-btn icon color="black" x-large @click="ToGroupRanking"
              ><v-icon>mdi-poll</v-icon> 랭킹</v-btn
            >
            <v-btn icon color="black" x-large
              ><v-icon>mdi-calendar-month</v-icon> 출석부</v-btn
            >
          </v-row>
          <v-card>
            <v-toolbar>
              <template>
                <v-tabs
                  v-model="tabs"
                  fixed-tabs
                  centered
                  slider-color="#673fb4"
                >
                  <v-tab v-for="item in items" :key="item">
                    {{ item.title }}
                  </v-tab>
                </v-tabs>
              </template>
            </v-toolbar>

            <v-tabs-items v-model="tabs">
              <v-tab-item>
                <v-card>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="position:relative; top:-25%">이아름</h1>
                      <h2 style="position:relative; top:25%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="position:relative; top:-15%">이아름</h1>
                      <h2 style="position:relative; top:35%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="position:relative; top:-35%">이아름</h1>
                      <h2 style="position:relative; top:10%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                  </v-row>
                </v-card>
              </v-tab-item>
              <v-tab-item>
                <v-card>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="position:relative; top:-25%">이아름</h1>
                      <h2 style="position:relative; top:25%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="position:relative; top:-15%">이아름</h1>
                      <h2 style="position:relative; top:35%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="position:relative; top:-35%">이아름</h1>
                      <h2 style="position:relative; top:10%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                  </v-row>
                </v-card> </v-tab-item
              ><v-tab-item>
                <v-card>
                  <v-row style="height:600px" justify="center" align="end">
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#673fb4; height:45%"
                      ><h1 style="position:relative; top:-25%">이아름</h1>
                      <h2 style="position:relative; top:25%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color:#524072; height:70%"
                      ><h1 style="position:relative; top:-15%">이아름</h1>
                      <h2 style="position:relative; top:35%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                    <v-col
                      cols="3"
                      style="text-align:center;background-color: #9471d1; height:30%"
                      ><h1 style="position:relative; top:-35%">이아름</h1>
                      <h2 style="position:relative; top:10%; color:white">
                        03:03:03
                      </h2></v-col
                    >
                  </v-row>
                </v-card>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tabs: null,
      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
      items: [{ title: "일간" }, { title: "주간" }, { title: "월간" }],
    };
  },
  methods: {
    ToGroupHome() {
      this.$router.push("/group-detail");
    },
    ToGroupRanking() {
      this.$router.push("/group-ranking");
    },
  },
};
</script>

<style>
.dfadf {
  color: #9471d1;
}
</style>
