<template>
  <div class="app-container">
    <upload-excel-component :on-success="handleSuccess" :before-upload="beforeUpload"/>
    <el-table :data="tableData" border stripe highlight-current-row style="width: 100%;margin-top:20px;">
      <el-table-column v-for="item of tableHeader" :prop="item" :label="item" :key="item"/>
    </el-table>
    <transition name="slide">
      <el-button v-if="tableData.length!==0" :type="postBtnType" :icon="postIcon" :loading="posting" circle size="big" class="post-btn" @click="handleUpload" />
    </transition>
  </div>
</template>

<script>
import UploadExcelComponent from '@/components/UploadExcel'
import ORDER from '@/utils/order'
import { addRepairation } from '@/api/order'

export default {
  name: 'UploadExcel',
  components: { UploadExcelComponent },
  data() {
    return {
      tableData: [],
      tableHeader: [],
      postData: [],
      uploadDone: false,
      postDone: false,
      posting: false,
    }
  },
  computed: {
    isEmpty() {
      return this.tableData.length === 0;
    },
    postIcon() {
      return this.postDone ? 'el-icon-check' : 'el-icon-upload2';
    },
    postBtnType() {
      return this.postDone ? 'success' : 'primary';
    },
  },
  methods: {
    beforeUpload(file) {
      const isLt1M = file.size / 1024 / 1024 < 1

      if (isLt1M) {
        return true
      }

      this.$message({
        message: '不要选择大于 1M 的文件',
        type: 'warning'
      })
      return false
    },
    handleSuccess({ results, header }) {
      this.tableData = results
      this.tableHeader = header
      this.dataFilter();

      this.posting = false;
      this.postDone = false;
    },
    dataFilter() {
      const temp = this.tableData.map((row, i) => {
        const o = {};
        Object.values(ORDER).map(v => {
          if (row.hasOwnProperty(v.name)) {
            o[v.value] = row[v.name];
          }
        });
        return o;
      })

      this.postData = temp;
      console.log(this.postData);
    },
    handleUpload() {
      if (this.postDone) {
        return;
      }

      this.posting = true;
      const requestList = this.postData.map(v => {
        return addRepairation(v).catch(e => console.error(e));
      });

      Promise.all(requestList).then(() => {
        this.posting = false;
        this.postDone = true;
      });
    },
  }
}
</script>

<style scoped>
.post-btn {
  position: absolute;
  right: 40px;
  top: 90px;
}

.slide-enter, .slide-leave-to {
  transform: translateX(150%);
}

.slide-enter-active, .slide.leave-active {
  transition: transform .3s ease-out;
}
</style>
