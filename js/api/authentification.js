import api from './axios.config.js'

const REQ_HEADER = {
  Accept: "application/json",
  "Content-Type": "application/json;charset=UTF-8",
}

export function signUpUser(url, payload) {
  return api
    .post(url, payload, REQ_HEADER)
    .then(response => {
      console.log(response)
    //   window.alert(`${response.data}`)
    })
    .catch(error => {
      window.alert(`ERROR: ${error.response.data}`)
    })
} 