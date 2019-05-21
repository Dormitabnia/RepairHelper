// 订单结构
const ORDER = {
  ID: {
    name: 'ID',
    value: 'id',
    search: true,
    export: true,
    editable: true,
  },
  // USERNAME: {
  //   name: '用户名',
  //   value: 'username',
  //   search: true,
  //   export: true,
  //   editable: true,
  // },
  // REPAIRNAME: {
  //   name: '维修员',
  //   value: 'repairName',
  //   search: true,
  //   export: true,
  //   editable: true,
  // },
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
    editable: false,
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
    export: false,
    editable: false,
  },
  IMG: {
    name: '图片',
    value: 'img',
    search: false,
    export: false,
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

export default ORDER;
export { STATUS };
