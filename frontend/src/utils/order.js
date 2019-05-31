// 订单结构
const ORDER = {
  ID: {
    name: '维修单ID',
    value: 'id',
    search: true,
    export: true,
    editable: true,
  },
  USERNAME: {
    name: '用户名',
    value: 'submitter',
    search: false,
    export: true,
    editable: true,
  },
  REPAIRNAME: {
    name: '维修员',
    value: 'repairer',
    search: false,
    export: true,
    editable: true,
  },
  USERID: {
    name: '用户ID',
    value: 'userId',
    search: true,
    export: true,
    editable: true,
  },
  REPAIRID: {
    name: '维修员ID',
    value: 'repairId',
    search: true,
    export: true,
    editable: true,
  },
  EQUIPINFO: {
    name: '设备信息',
    value: 'equipInfo',
    search: false,
    export: true,
    editable: true,
  },
  FAULTINFO: {
    name: '故障信息',
    value: 'faultInfo',
    search: true,
    export: true,
    editable: true,
  },
  SOUND: {
    name: '录音',
    value: 'sound',
    search: false,
    export: true,
    editable: false,
  },
  IMG: {
    name: '图片',
    value: 'img',
    search: false,
    export: true,
    editable: false,
  },
  STATUS: {
    name: '状态',
    value: 'status',
    search: false,
    export: true,
    editable: true,
  },
};

// const STATUS = {
//   UNDO: {
//     name: '未处理',
//     value: '未处理',
//   },
//   DOING: {
//     name: '处理中',
//     value: '处理中',
//   },
//   DONE: {
//     name: '已处理',
//     value: '已处理',
//   },
// }

const STATUS = {
  UNDO: {
    name: '报修等待',
    value: '报修等待',
  },
  CONFIRM: {
    name: '报修确认',
    value: '报修确认',
  },
  DOING: {
    name: '维修处理',
    value: '维修处理',
  },
  DONE: {
    name: '处理完毕',
    value: '处理完毕',
  },
}

export default ORDER;
export { STATUS };
