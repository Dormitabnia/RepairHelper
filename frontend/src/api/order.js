import request from '@/utils/request'

export function fetchRepairationList(query) {
  return request({
    url: '/repairationList',
    method: 'get',
    params: query
  })
}

export function fetchRepairation(id) {
  return request({
    url: '/repairation',
    method: 'get',
    params: { id }
  })
}

export function updateRepairation(repairation) {
  return request({
    url: '/repairation',
    method: 'put',
    data: repairation,
  });
}

export function deleteRepairation(id) {
  return request({
    url: '/repairation',
    method: 'delete',
    data: { id },
  });
}

export function addRepairation(data) {
  return request({
    url: '/repairation',
    method: 'post',
    data,
  })
}
