<template>
    <v-container>
        <div>
            <v-text-field
                :placeholder="placeholder"
                outlined
                color="#673fb4"
            ></v-text-field>
        </div>
    </v-container>
</template>

<script>
export default {
    name: "InputBar",
    props: ['placeholder'],
}
</script>

<style scoped>
.v-text-field--outlined >>> fieldset {
  border-color: #E0E0E0;
  border-width: 4px;
}
</style>