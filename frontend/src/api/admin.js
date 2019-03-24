import request from '@/utils/request'

export function fetchAdminList(query) {
  return request({
    url: '/adminList',
    method: 'get',
    params: query
  })
}

export function fetchAdminInfo(id) {
  return request({
    url: '/admin',
    method: 'get',
    params: { id }
  })
}

export function updateAdminInfo(adminInfo) {
  return request({
    url: '/admin',
    method: 'put',
    data: adminInfo,
  });
}

export function deleteAdmin(id) {
  return request({
    url: '/admin',
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
