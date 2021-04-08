<template>
    <div >
        <div class="infochip">
            {{ text }}
        </div>
    </div>
</template>

<script>
export default {
    name: "InfoChip",
    props: ['text'],
}
</script>

<style>
.infochip{
    border: 0.1rem solid;
    border-color: #E0E0E0;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 2px;
    padding-bottom: 2px;
    border-radius: 30px;
    color: #757575;
    width: 80px;
    text-align: center;
    font-size: 8px;
}
</style>