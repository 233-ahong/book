import request from "@/utils/request.js";
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  });
}

export function loginByPassword(data) {
  return request({
    url: '/loginByPassword',
    method: 'post',
    data: data
  });
}
export function loginByPhone (data) {
  return request({
    url: '/loginByPhone',
    method: 'post',
    data: data
  });
}
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  });
}

export function getCode(phone) {
  return request({
    url: '/code/' + phone,
    headers: {
      isToken: false
    },
    method: 'get',
  })
}

export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    data: data
  })
}
