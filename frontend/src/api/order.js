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

export function updateReparation(repairation) {
  return request({
    url: '/editRepairation',
    method: 'post',
    data: repairation,
  });
}

export function deleteReparation(id) {
  return request({
    url: '/deleteRepairation',
    method: 'post',
    data: id,
  });
}
