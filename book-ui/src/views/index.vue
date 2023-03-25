<template>
  <div class='el-main-div'>
    <el-container>
      <el-header><book-header /></el-header>
      <el-main>
        <p />
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import BookHeader from "@/layout/header/BookHeader.vue";
</script>

<style scoped>
.el-main-div {
  padding: 0;
  margin: 0;
}
</style>
