<template>
    <v-app class="groupInfo">
        <v-flex xs2>
            <v-img :src="src" style="border-radius:10px;">
                <div class="groupCnt"><v-icon dark>mdi-account</v-icon><span>{{ groupCnt }} / {{ groupTotalCnt}}</span></div>
            </v-img>
        </v-flex>
        <div>
            <span class="groupName">{{ groupName }}</span><br>
            <span class="groupDesc">{{ groupDesc }}</span>
        </div>
    </v-app>
</template>

<script>
export default {
  props: ["src", "groupName", "groupDesc", "groupCnt", "groupTotalCnt"],
};
</script>

<style>
.groupCnt {
    color:white;
    padding-top:135px;
    font-size: 15px;
    font-weight: 500;
    padding-left: 10px;
}
.groupName {
    font-weight: 600;
    font-size: 20px;
}
.groupDesc {
    color:#BDBDBD;
}
.groupInfo {
    margin: 20px;
}
</style>