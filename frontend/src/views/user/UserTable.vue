<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.q" placeholder="查询内容" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"/>
      <el-select v-model="listQuery.qt" placeholder="查询类型" clearable style="width: 130px" class="filter-item">
        <el-option v-for="item in queryType" :key="item.value" :label="item.name" :value="item.value"/>
      </el-select>
      <el-select v-model="listQuery.f" placeholder="用户过滤" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in userType" :key="item" :label="item | typeFilter" :value="item"/>
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
      <el-table-column :label="tableCol.ID.name" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tableCol.USERNAME.name" align="center" width="150">
        <template slot-scope="scope">
          <span class="link-type" @click="handleUpdate(scope.row)">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tableCol.COMPANY.name" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.company }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tableCol.PHONE.name" width="200" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="tableCol.AUTHORITY.name" class-name="status-col" width="150">
        <template slot-scope="scope">
          <el-tag :type="scope.row.authority | roleTagFilter">{{ scope.row.authority | typeFilter }}</el-tag>
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

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:50px;">
        <el-form-item :label="tableCol.ID.name" prop="id">
          <el-input v-model="temp.id" :disabled="true" />
        </el-form-item>
        <el-form-item :label="tableCol.USERNAME.name" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item :label="tableCol.COMPANY.name" prop="company">
          <el-input v-model="temp.company"/>
        </el-form-item>
        <el-form-item :label="tableCol.PHONE.name" prop="phone">
          <el-input v-model="temp.phone"/>
        </el-form-item>
        <el-form-item :label="tableCol.AUTHORITY.name" prop="authority">
          <el-select v-model="temp.authority" class="filter-item">
            <el-option v-for="item in userType" :key="item" :label="item | typeFilter" :value="item"/>
          </el-select>
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
import { fetchUserList, updateUserInfo, deleteUser } from '@/api/user'
import waves from '@/directive/waves' // Waves directive
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import ROLE from '@/utils/role'

const userType = Object.values(ROLE);

// 表格头
const tableCol = {
  ID: {
    name: 'ID',
    value: 'id',
  },
  USERNAME: {
    name: '用户名',
    value: 'name',
  },
  COMPANY: {
    name: '公司',
    value: 'company',
  },
  PHONE: {
    name: '电话',
    value: 'phone',
  },
};
Object.defineProperty(tableCol, 'AUTHORITY', {
  value: {
    name: '用户类型',
    value: 'authority',
  },
  enumerable: false,
})

const queryType = Object.values(tableCol);

export default {
  name: 'UserTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    roleTagFilter(type) {
      const tagMap = {
        [`${ROLE.ROOT}`]: 'danger',
        [`${ROLE.ADMIN}`]: 'danger',
        [`${ROLE.FIXER}`]: 'success',
        [`${ROLE.USER}`]: '',
        [`${ROLE.PENDING}`]: 'info',
      }
      return tagMap[type];
    },
    typeFilter(type) {
      if (+type >= ROLE.PENDING) {
        type = ROLE.PENDING;
      }
      return ROLE.prop[type].name;
    }
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
      userType,
      tableCol,
      queryType,
      showDelete: false,
      temp: {
        id: undefined,
        name: '',
        company: '',
        phone: '',
        authority: '',
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '添加'
      },
      dialogPvVisible: false,
      rules: {
        id: [{ required: true, message: '不能为空', trigger: 'blur' }],
        name: [{ required: true, message: '不能为空', trigger: 'blur' }],
        phone: [{ required: true, message: '不能为空', trigger: 'blur' }],
        company: [{ required: true, message: '不能为空', trigger: 'blur' }],
      },
      downloadLoading: false,
      exportDialogVisible: false,
      exportPage: undefined,
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchUserList(this.listQuery).then(data => {
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
        name: '',
        company: '',
        phone: '',
        authority: '',
      };
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
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
            updateUserInfo(tempData).then(() => {
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
        deleteUser(row.id).then(data => {
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
      const tHeader = [];
      const filterVal = [];

      // 构造表头
      for (const k in this.tableCol) {
        tHeader.push(this.tableCol[k].name);
        filterVal.push(this.tableCol[k].value);
      }
      tHeader.push(this.tableCol.AUTHORITY.name);
      filterVal.push(this.tableCol.AUTHORITY.value);

      // 并发请求
      const fetchingList = new Array(this.exportPage).fill(0).map((v, i) => {
        return fetchUserList(Object.assign(this.listQuery, { page: i })).then(data => {
          exportData = exportData.concat(data.list);
        })
      });

      Promise.all(fetchingList).then(() => {
        console.log(exportData);
        import('@/vendor/Export2Excel').then(excel => {
        // tHeader = ['ID', '用户名', '公司', '电话', '用户类型'];
        // filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
          const data = this.formatJson(filterVal, exportData)
          console.log(data);
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: 'UserExcel'
          })
          this.downloadLoading = false
        });
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        let type = '';
        switch (j) {
          case 'authority':
            type = v[j];
            if (+type >= ROLE.PENDING) {
              type = ROLE.PENDING;
            }
            return ROLE.prop[type].name;
          default:
            return v[j];
        }
        // return v[j]
      }))
    },
  }
}
</script>

<style>
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
