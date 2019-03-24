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
    url: '/userInfo',
    method: 'put',
    data: userInfo,
  });
}

export function deleteUser(id) {
  return request({
    url: '/userInfo',
    method: 'delete',
    data: { id },
  });
}

export function addAdmin({ username, password }) {
  return request({
    url: '/admin',
    method: 'post',
    data: { username, password },
  });
}
