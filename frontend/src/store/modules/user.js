import { loginByUsername, logout, getUserInfo } from '@/api/login'
// import { getToken, setToken, removeToken } from '@/utils/auth'
import { getToken, setToken, removeToken } from '@/utils/tokenStorage'
import { getName, setName } from '@/utils/nameStorage'
import { MessageBox } from 'element-ui';
// import { parseJwt } from '@/utils/jwt'

const user = {
  state: {
    user: '',
    status: '',
    code: '',
    token: getToken(),
    // name: getToken() ? parseJwt(getToken()).name : 'Root',
    name: getName() || 'Root',
    avatar: '',
    introduction: '',
    roles: [],
    setting: {
      articlePlatform: []
    }
  },

  mutations: {
    SET_CODE: (state, code) => {
      state.code = code
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_SETTING: (state, setting) => {
      state.setting = setting
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_NAME: (state, name) => {
      setName(name);
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    }
  },

  actions: {
    // 用户名登录
    LoginByUsername({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        loginByUsername(username, userInfo.password).then(data => {
          // 设置 Token
          if (data) {
            commit('SET_TOKEN', data)
            setToken(data)
          }

          // 设置名字
          commit('SET_NAME', username)

          // const jwtPayload = parseJwt(data.token);
          // console.log(jwtPayload);

          // commit('SET_NAME', jwtPayload.name);

          // if (jwtPayload.roles && jwtPayload.roles.length > 0) { // 验证返回的roles是否是一个非空数组
          //   commit('SET_ROLES', jwtPayload.roles)
          // } else {
          //   reject('getInfo: roles must be a non-null array !')
          // }

          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户权限
    GetUserRoles({ commit, state }) {
      return new Promise((resolve, reject) => {
        if (state.roles.length === 0) {
          // const jwtPayload = parseJwt(state.token)
          const jwtPayload = { role: ['root'] };

          if (jwtPayload.role && jwtPayload.role.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', jwtPayload.role)
          } else {
            reject('getInfo: roles must be a non-null array !')
          }
        }

        resolve(state.roles)
      })
    },

    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.name).then(data => {
          // if (!response.data) { // 由于mockjs 不支持自定义状态码只能这样hack
          //   reject('error')
          // }
          // const data = response.data
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 第三方验证登录
    // LoginByThirdparty({ commit, state }, code) {
    //   return new Promise((resolve, reject) => {
    //     commit('SET_CODE', code)
    //     loginByThirdparty(state.status, state.email, state.code).then(response => {
    //       commit('SET_TOKEN', response.data.token)
    //       setToken(response.data.token)
    //       resolve()
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({ dispatch, commit, state }) {
      // 超时强制退出
      const logoutReq = new Promise((resolve, reject) => {
        logout(state.name).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      });

      let timer;
      const timeoutPromise = new Promise((resolve, reject) => {
        timer = setTimeout(() => {
          reject(Promise.reject(new Error('请求超时')));
        }, 2000);
      });

      return Promise.race([logoutReq, timeoutPromise]).catch(err => {
        // 退出登录请求失败 或 超时
        // 弹出 强制退出 框
        console.log('退出登录失败');
        clearTimeout(timer);
        MessageBox.confirm('是否强制退出？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          removeToken()
        })

        return Promise.reject(err);
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resolve()
      })
    },

    // 动态修改权限
    ChangeRoles({ commit, dispatch }, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken(role)
        getUserInfo(role).then(response => {
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          dispatch('GenerateRoutes', data) // 动态修改权限后 重绘侧边菜单
          resolve()
        })
      })
    }
  }
}

export default user
