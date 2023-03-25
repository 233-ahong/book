<template>
  <div class="el-row">
    <el-image :src="logo" class="logo-img" />
    <div class="logo-text">云上书城</div>
  </div>
</template>

<script setup>
import logo from "@/assets/images/yun.png";
</script>

<style scoped>
.logo-img {
  height: 8vh;
}
.logo-text {
  min-height: 8vh;
  font-size: 4vh;
  color: #48d1cc;
  display: inline;
  margin-left: 1vh;
  line-height: 8vh;
}
</style>
