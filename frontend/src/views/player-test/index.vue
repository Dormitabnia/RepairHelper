<template>
  <div>
    <el-row :style="{ 'margin-top': '30px', 'height': '80px' }" type="flex" justify="center">
      <el-col :span="8" >
        <wave-player />
      </el-col>
    </el-row>
    <el-row :style="{ 'margin-top': '30px' }" type="flex" justify="center">
      <el-col :span="8" :style="{ 'text-align': 'center' }">
        路径
        <el-input v-model="audioName" placeholder="请输入内容" style="width:50%" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import WavePlayer from '@/components/WavePlayer'

export default {
  name: 'PlayerTest',
  components: {
    WavePlayer,
  },
  data() {
    return {
      audioName: 'test-1.mp3',
    };
  },
  methods: {

  },
}
</script>

<style scoped>
html {
  box-sizing: border-box;
}

*, *::after, *::before {
  box-sizing: inherit;
}

</style>
