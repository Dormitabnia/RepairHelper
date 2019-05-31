const NameKey = 'username'

export function getName() {
  return localStorage.getItem(NameKey);
}

export function setName(token) {
  return localStorage.setItem(NameKey, token);
}

export function removeName() {
  return localStorage.removeItem(NameKey);
}
