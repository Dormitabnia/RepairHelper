const b64DecodeUnicode = str =>
  decodeURIComponent(
    Array.prototype.map.call(atob(str), c =>
      '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
    ).join(''))

export const parseJwt = token =>
  JSON.parse(
    b64DecodeUnicode(
      token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/')
    )
  )
