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
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-upload" @click="handleUploadBtn">导入</el-button>
      <el-checkbox v-model="showEdit" class="filter-item" style="margin-left:15px;" @change="tableKey=tableKey+1">编辑</el-checkbox>
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
      <el-table-column :label="ORDER.USERNAME.name" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row[ORDER.USERNAME.value] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="queryType[1].name" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row[queryType[1].value] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="ORDER.REPAIRNAME.name" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row[ORDER.REPAIRNAME.value] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="queryType[2].name" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row[queryType[2].value] }}</span>
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
      <el-table-column v-if="showEdit" label="操作" width="160px" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleUpdate(scope.row)">{{ '编辑' }}
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">{{ '删除' }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.size" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" @open="beforeDialogOpen" @opened="afterDialogOpen" @close="beforeDialogClose" >
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:50px;">
        <el-form-item label="ID" prop="id">
          <el-input v-model="temp.id" :disabled="true" />
        </el-form-item>
        <!-- <el-form-item label="用户名" prop="username">
          <el-input v-model="temp.username" />
        </el-form-item>
        <el-form-item label="维修员" prop="repairName">
          <el-input v-model="temp.repairName" />
        </el-form-item> -->
        <el-form-item label="用户ID">
          <el-input v-model="temp.userId" />
        </el-form-item>
        <el-form-item label="维修员ID">
          <el-input v-model="temp.repairId" />
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
            <!-- <el-carousel-item v-for="(img, index) in temp.img" :key="index">
              <img :src="img" preview="0" class="img-item-center">
            </el-carousel-item> -->
            <el-carousel-item>
              <img :src="temp.img | imgFilter" preview="0" class="img-item-center">
            </el-carousel-item>
          </el-carousel>
        </el-row>
        <!-- <el-row>
          <div id="waveform" />
        </el-row> -->
        <!-- <el-row>
          <audio :src="temp.sound" controls />
        </el-row> -->
        <el-form-item label="音频">
          <audio ref="sound" :src="temp.sound | mediaFilter" controls/>
        </el-form-item>
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

    <!-- 上传 Excel -->
    <el-dialog :visible.sync="uploadDialogVisible" title="上传 Excel">
      <upload-excel-component :on-success="handleUploadSuccess" :before-upload="beforeUpload" />
      <el-button :disabled="tableData.length===0" :type="postBtnType" :loading="posting" style="margin: 20px auto 0; display: block" plain @click="handleUpload" >{{ postText }}</el-button>
    </el-dialog>

  </div>
</template>

<script>
import { fetchRepairationList, updateRepairation, deleteRepairation, addRepairation } from '@/api/order'
import UploadExcelComponent from '@/components/UploadExcel'
import waves from '@/directive/waves' // Waves directive
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import ORDER, { STATUS } from '@/utils/order';

// 生成查询选项
const queryType = Object.values(ORDER).filter(item => item.search);
// 生成状态选项
const statusType = Object.values(STATUS);

export default {
  name: 'OrderTable',
  components: { Pagination, UploadExcelComponent },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        [`${STATUS.UNDO.value}`]: 'danger',
        [`${STATUS.CONFIRM.value}`]: 'info',
        [`${STATUS.DOING.value}`]: '',
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
    imgFilter(src) {
      return `/media/${src}`;
    },
    mediaFilter(src) {
      return `/media/${src}`;
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
      showEdit: false,
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
        id: [{ required: true, message: '不能为空', trigger: 'blur' }],
        username: [{ required: true, message: '不能为空', trigger: 'blur' }],
        repairName: [{ required: true, message: '不能为空', trigger: 'blur' }],
      },
      downloadLoading: false,
      exportDialogVisible: false,
      exportPage: undefined,
      uploadDialogVisible: false,
      uploadDisable: true,
      // 上传 Excel 相关
      tableData: [],
      tableHeader: [],
      postData: [],
      uploadDone: false,
      postDone: false,
      posting: false,
      ORDER,
    }
  },
  computed: {
    postText() {
      return this.postDone ? '上传完成' : '上传';
    },
    postBtnType() {
      return this.postDone ? 'success' : 'primary';
    },
  },
  created() {
    this.getList()
  },
  mounted() {
    // console.log('mounted');
    // const waveScript = document.createElement('script');
    // waveScript.setAttribute('src', 'https://cdn.jsdelivr.net/npm/wavesurfer.js@2.1.3/dist/wavesurfer.min.js');
    // this.$el.appendChild(waveScript);
    // waveScript.onload = () => {
    //   console.log('wavesufer load success!');
    // };
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
            let tempData = Object.assign({}, this.temp)
            // 过滤 Data
            tempData = Object.values(ORDER).filter(v => v.editable)
              .reduce((obj, v) => {
                obj[v.value] = tempData[v.value];
                return obj;
              }, {});

            updateRepairation(tempData).then(() => {
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
        deleteRepairation(row.id).then(data => {
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
        return fetchRepairationList({ ...this.listQuery, page: i + 1 }).then(data => {
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

      // setTimeout(() => {
      //   const wavesurfer = window.WaveSurfer.create({
      //     container: '#waveform',
      //     waveColor: 'violet',
      //     progressColor: 'purple',
      //   });
      //   console.log('isLoading');
      //   wavesurfer.load('/static/sound/test-1.mp3');
      // }, 500);
    },
    afterDialogOpen() {
      // console.log('Dialog is opened');
      // const wavesurfer = window.WaveSurfer.create({
      //   container: 'waveform',
      //   waveColor: 'violet',
      //   progressColor: 'purple',
      // });
      // console.log('isLoading');
      // wavesurfer.load('/static/sound/test-1.mp3');
    },
    beforeDialogClose() {
      // 音频暂停
      this.$refs.sound.pause();
    },

    // 上传 Excel 相关
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
    handleUploadSuccess({ results, header }) {
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
        // 手动刷新
        this.getList();
      });
    },
    handleUploadBtn() {
      this.uploadDialogVisible = true;
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
