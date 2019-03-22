const ROLE = {
  ROOT: 0,
  ADMIN: 1,
  FIXER: 2,
  USER: 3,
  PENDING: 100,
};

Object.defineProperty(ROLE, 'prop', {
  value: {
    [`${ROLE.ROOT}`]: {
      name: '根用户',
    },
    [`${ROLE.ADMIN}`]: {
      name: '管理员',
    },
    [`${ROLE.FIXER}`]: {
      name: '维修员',
    },
    [`${ROLE.USER}`]: {
      name: '用户',
    },
    [`${ROLE.PENDING}`]: {
      name: '待审核',
    }
  },
  enumerable: false,
})

export default ROLE;