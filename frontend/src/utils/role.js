const ROLE = {
  // ROOT: 0,
  // ADMIN: 0,
  FIXER: 1,
  ORDERER: 2,
  USER: 3,
  // PENDING: 100,
};

Object.defineProperty(ROLE, 'prop', {
  value: {
    // [`${ROLE.ROOT}`]: {
    //   name: '根用户',
    // },
    // [`${ROLE.ADMIN}`]: {
    //   name: '管理员',
    // },
    [`${ROLE.FIXER}`]: {
      name: '维修员',
      value: 1,
    },
    [`${ROLE.ORDERER}`]: {
      name: '报修人员',
      value: 2,
    },
    [`${ROLE.USER}`]: {
      name: '用户',
      value: 3,
    },
    // [`${ROLE.PENDING}`]: {
    //   name: '待审核',
    // }
  },
  enumerable: false,
})

export default ROLE;
