import request from '@/utils/request'

export function fetchUserList(query) {
  return request({
    url: '/userList',
    method: 'get',
    params: query
  })
}

export function fetchUserInfo(id) {
  return request({
    url: '/userInfo',
    method: 'get',
    params: { id }
  })
}

export function updateUserInfo(userInfo) {
  return request({
    url: '/editUserInfo',
    method: 'post',
    data: userInfo,
  });
}

export function deleteUser(id) {
  return request({
    url: '/deleteUser',
    method: 'post',
    data: id,
  });
}

export function addAdmin({ username, password }) {
  return request({
    url: '/admin',
    method: 'post',
    data: { username, password },
  });
}
