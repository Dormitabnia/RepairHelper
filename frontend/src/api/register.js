import request from '@/utils/request'

export function register({ username, password }) {
  const data = {
    username,
    password
  }
  return request({
    url: '/register',
    method: 'post',
    data
  })
}
