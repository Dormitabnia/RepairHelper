// 表格头
const USER = {
  ID: {
    name: 'ID',
    value: 'id',
    search: true,
    export: true,
    editable: false,
  },
  USERNAME: {
    name: '用户名',
    value: 'name',
    search: true,
    export: true,
    editable: true,
  },
  COMPANY: {
    name: '公司',
    value: 'company',
    search: true,
    export: true,
    editable: true,
  },
  PHONE: {
    name: '电话',
    value: 'phone',
    search: true,
    export: true,
    editable: true,
  },
  AUTHORITY: {
    name: '用户类型',
    value: 'authority',
    search: false,
    export: true,
    editable: true,
  },
};
// Object.defineProperty(USER, 'AUTHORITY', {
//   value: {
//     name: '用户类型',
//     value: 'authority',
//   },
//   enumerable: false,
// })

export default USER;
