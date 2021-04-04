<template>
  <v-card class="mx-auto elevation-6" max-width="300" color="#efefef">
    <v-img
      class="white--text align-end"
      height="250px"
      :src="require(`@/assets/img/${groupImg}`)"
      gradient="to bottom left, rgba(200,70,150,.2), rgba(0,0,0,.7)"
    >
      <v-card-title style="font-size: 1.5rem; letter-spacing: -1px;">
        {{ groupName }}
      </v-card-title>
    </v-img>

    <v-card-text
      class="text--primary"
      style="height: 100px; letter-spacing: -1px; "
    >
      <div>{{ groupDesc }}</div>
    </v-card-text>

    <v-card-actions justify="end">
      <v-row no-gutters justify="end">
        <span
          style="letter-spacing: -1px; font-size: 0.9rem; color: #999999; font-weight:lighter; margin-right: 10px;"
        >
          <v-icon color="#999999">mdi-crown</v-icon>
          {{ groupMaster }}
        </span>
        <span
          style="letter-spacing: -1px; font-size: 0.9rem; color: #999999; margin-right: 10px;"
        >
          <v-icon color="#999999">mdi-account-multiple</v-icon>
          {{ groupMemberCnt }}
        </span>
      </v-row>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: [
    'groupImg',
    'groupName',
    'groupDesc',
    'groupMaster',
    'groupMemberCnt',
  ],
};
</script>

<style></style>
