<template>
  <div>
    <el-row :gutter='24' class='div-row-bc'>
      <el-col :span='2'/>
      <el-col :span='20'>
        {{order}}
      </el-col>
      <el-col :span='2'></el-col>
    </el-row>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { ref } from 'vue'
const order = ref('')
function getOrder() {
  order.value = useRoute().query.data

  console.log(order)
}
getOrder()
</script>

<style scoped>

</style>
