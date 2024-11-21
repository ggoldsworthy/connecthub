import api from './axios.config.js'

const REQ_HEADER = {
  Accept: "application/json",
  "Content-Type": "application/json;charset=UTF-8",
}

export function signUpUser(url, payload) {
  return api
    .post(url, payload, REQ_HEADER)
    .then(response => {
    })
    .catch(error => {
      window.alert(`ERROR: ${error.response.data}`)
    })
} 

export function logInUser(url, payload) {
  return api 
    .post(url, payload, REQ_HEADER)
    .then(response => {
    })
    .catch(error => {
      // TODO differentiate errors
      console.log(error)
    })
}
