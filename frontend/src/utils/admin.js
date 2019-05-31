// 表格头
const ADMIN = {
  ID: {
    name: 'ID',
    value: 'id',
    search: true,
    export: true,
    editable: false,
  },
  USERNAME: {
    name: '用户名',
    value: 'username',
    search: true,
    export: true,
    editable: true,
  },
  PASSWORD: {
    name: '密码',
    value: 'password',
    search: false,
    export: true,
    editable: true,
  },
  AUTHORITY: {
    name: '用户类型',
    value: 'authority',
    search: false,
    export: false,
    editable: false,
  },
};
// Object.defineProperty(ADMIN, 'PASSWORD', {
//   value: {
//     name: '密码',
//     value: 'password',
//   },
//   enumerable: false,
// });
// Object.defineProperty(ADMIN, 'AUTHORITY', {
//   value: {
//     name: '用户类型',
//     value: 'authority',
//   },
//   enumerable: false,
// });

export default ADMIN;
