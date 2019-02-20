<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.q" placeholder="查询内容" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"/>
      <el-select v-model="listQuery.qt" placeholder="查询类型" clearable style="width: 130px" class="filter-item">
        <el-option v-for="item in queryType" :key="item.value" :label="item.name" :value="item.value"/>
      </el-select>
      <el-select v-model="listQuery.f" placeholder="状态过滤" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in statusType" :key="item.value" :label="item.name" :value="item.value"/>
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">{{ $t('table.search') }}</el-button>
      <!-- <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">{{ $t('table.add') }}</el-button> -->
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">{{ $t('table.export') }}</el-button>
      <el-checkbox v-model="showDelete" class="filter-item red-check" style="margin-left:15px;" @change="tableKey=tableKey+1">{{ '删除' }}</el-checkbox>
    </div>

    <el-table
      v-loading="listLoading"
      :key="tableKey"
      :data="list"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%;">
      <el-table-column :label="queryType[0].name" prop="id" align="center" width="100">
        <template slot-scope="scope">
          <!-- <span>{{ scope.row.id }}</span> -->
          <span class="link-type" @click="handleUpdate(scope.row)">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="queryType[1].name" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="queryType[2].name" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.repairName }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="queryType[3].name" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.faultInfo | faultInfoFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处理状态" class-name="status-col" width="150">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column v-if="showDelete" label="操作" width="110px" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">{{ '删除' }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.size" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" @open="beforeDialogOpen" @opened="afterDialogOpen">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:50px;">
        <el-form-item label="ID" prop="id">
          <el-input v-model="temp.id" :disabled="true" />
        </el-form-item>
        <el-form-item label="用户名" prop="name">
          <el-input v-model="temp.username" />
        </el-form-item>
        <el-form-item label="维修员" prop="name">
          <el-input v-model="temp.repairName" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="temp.status" class="filter-item">
            <el-option v-for="item in statusType" :key="item.value" :label="item.name" :value="item.value"/>
          </el-select>
        </el-form-item>
        <!-- <el-form-item :label="$t('table.importance')">
          <el-rate
            v-model="temp.importance"
            :icon-classes="['ex-icon-frown-fill', 'ex-icon-meh-fill', 'ex-icon-smile-fill']"
            :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
            :texts="['差评','一般','一般','满意','满意']"
            :max="5"
            void-icon-class="ex-icon-meh-fill"
            style="margin-top:8px;"
          />
        </el-form-item> -->
        <el-row :style="{'margin-bottom': '22px'}" type="flex">
          <label class="el-form-item__label" style="width: 70px;">图片</label>
          <el-carousel :style="{width:'400px'}" :autoplay="false" trigger="click">
            <el-carousel-item v-for="(img, index) in temp.img" :key="index">
              <img :src="img" preview="0" class="img-item-center">
            </el-carousel-item>
          </el-carousel>
        </el-row>
        <!-- <el-row>
          <div id="waveform" />
        </el-row>
        <el-row>
          <audio src="/static/sound/test-1.mp3" controls />
        </el-row> -->
        <el-form-item label="故障信息">
          <el-input
            v-model="temp.faultInfo"
            type="textarea"
            autosize
            placeholder="请输入内容" />
            <!-- <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="temp.remark" type="textarea" placeholder="Please input"/> -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="exportDialogVisible" title="导出 Excel">
      <el-row type="flex" justify="center">
        <el-col :span="8">
          <img src="/static/image/excel.png" style="width: 100%">
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="6">
          <el-select v-model="exportPage" placeholder="导出页数">
            <el-option
              v-for="n in Math.ceil(total / listQuery.size)"
              :key="n"
              :label="n"
              :value="n"/>
          </el-select>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" style="margin-top: 20px;">
        <el-button :loading="downloadLoading" type="success" plain icon="el-icon-download" @click="handleExport">导出</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { fetchRepairationList, updateReparation, deleteReparation } from '@/api/order'
import waves from '@/directive/waves' // Waves directive
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

// 订单结构
const ORDER = {
  ID: {
    name: 'ID',
    value: 'id',
    search: true,
    export: true,
  },
  USERNAME: {
    name: '用户名',
    value: 'username',
    search: true,
    export: true,
  },
  REPAIRNAME: {
    name: '维修员',
    value: 'repairName',
    search: true,
    export: true,
  },
  EQUIPINFO: {
    name: '设备信息',
    value: 'equipInfo',
    search: false,
    export: true,
  },
  FAULTINFO: {
    name: '故障信息',
    value: 'faultInfo',
    search: true,
    export: true,
  },
  SOUND: {
    name: '录音',
    value: 'sound',
    search: false,
    export: false,
  },
  IMG: {
    name: '图片',
    value: 'img',
    search: false,
    export: false,
  },
  STATUS: {
    name: '状态',
    value: 'status',
    search: false,
    export: true,
  },
}

const STATUS = {
  UNDO: {
    name: '未处理',
    value: '未处理',
  },
  DOING: {
    name: '处理中',
    value: '处理中',
  },
  DONE: {
    name: '已处理',
    value: '已处理',
  },
}

// 生成查询选项
const queryType = Object.values(ORDER).filter(item => item.search);
// 生成状态选项
const statusType = Object.values(STATUS);

export default {
  name: 'OrderTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        [`${STATUS.UNDO.value}`]: 'danger',
        [`${STATUS.DOING.value}`]: 'info',
        [`${STATUS.DONE.value}`]: 'success',
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return 1;
    },
    faultInfoFilter(info) {
      if (info.length <= 32) {
        return info;
      }
      return info.slice(0, 32) + '...';
    },
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        size: 10,
        q: '',
        qt: '',
        f: '',
      },
      queryType,
      statusType,
      importanceOptions: [1, 2, 3],
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showDelete: false,
      temp: {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        type: '',
        status: 'published',
        imgs: '',
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '添加'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      },
      downloadLoading: false,
      exportDialogVisible: false,
      exportPage: undefined,
    }
  },
  created() {
    this.getList()
  },
  mounted() {
    console.log('mounted');
    const waveScript = document.createElement('script');
    waveScript.setAttribute('src', 'https://cdn.jsdelivr.net/npm/wavesurfer.js@2.1.3/dist/wavesurfer.min.js');
    this.$el.appendChild(waveScript);
    waveScript.onload = () => {
      console.log('wavesufer load success!');
    };
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchRepairationList(this.listQuery).then(data => {
        this.list = data.list;
        this.total = data.total;

        this.listLoading = false;
      })
    },
    handleFilter() {
      // 异常判断
      if ((this.listQuery.q !== '' && this.listQuery.qt === '') ||
          (this.listQuery.q === '' && this.listQuery.qt !== '')) {
        return this.$message.error('请补全参数！');
      }

      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
      }
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$confirm('是否确定修改？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            updateReparation(tempData).then(() => {
              for (const v of this.list) {
                if (v.id === this.temp.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.temp)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    handleDelete(row) {
      this.$confirm('是否确定删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteReparation(row.id).then(data => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          const index = this.list.indexOf(row)
          console.log(index)
          this.list.splice(index, 1)
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    handleDownload() {
      this.exportDialogVisible = true;
    },
    handleExport() {
      this.downloadLoading = true;
      let exportData = [];

      // 构造表头
      const tHeader = Object.values(ORDER).filter(item => item.export).map(v => v.name);
      const filterVal = Object.values(ORDER).filter(item => item.export).map(v => v.value);

      // 并发请求
      const fetchingList = new Array(this.exportPage).fill(0).map((v, i) => {
        return fetchRepairationList(Object.assign(this.listQuery, { page: i })).then(data => {
          exportData = exportData.concat(data.list);
        })
      });

      Promise.all(fetchingList).then(() => {
        console.log(exportData);
        import('@/vendor/Export2Excel').then(excel => {
          const data = this.formatJson(filterVal, exportData)
          console.log(data);
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: 'OrderExcel'
          })
          this.downloadLoading = false
        });
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        switch (j) {
          case 'equipInfo':
            return JSON.stringify(v[j]);
          default:
            return v[j];
        }
        // return v[j]
      }))
    },
    beforeDialogOpen() {
      console.log('Dialog is openning');

      setTimeout(() => {
        const wavesurfer = window.WaveSurfer.create({
          container: '#waveform',
          waveColor: 'violet',
          progressColor: 'purple',
        });
        console.log('isLoading');
        wavesurfer.load('/static/sound/test-1.mp3');
      }, 500);
    },
    afterDialogOpen() {
      console.log('Dialog is opened');
      const wavesurfer = window.WaveSurfer.create({
        container: 'waveform',
        waveColor: 'violet',
        progressColor: 'purple',
      });
      console.log('isLoading');
      wavesurfer.load('/static/sound/test-1.mp3');
    }
  }
}
</script>

<style>
@import url(https://cdn.jsdelivr.net/npm/photoswipe@4.1.3/dist/default-skin/default-skin.css);

.img-item-center {
  max-width: 400px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.red-check .el-checkbox__input.is-checked+.el-checkbox__label {
    color: #f56c6c;
}

.red-check .el-checkbox__input.is-checked .el-checkbox__inner {
    background-color: #f56c6c;
    border-color: #f56c6c;
}

.red-check .el-checkbox__inner:hover {
    border-color: #f56c6c;
}
</style>
