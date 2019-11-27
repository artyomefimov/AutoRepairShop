import axios from "axios";

import * as Constants from "../Constants";

export const USER_NAME_SESSION_ATTRIBUTE_NAME = "authenticatedUser";

class AuthenticationService {
  redirectToLoginIfUnauthorized(status, history, previousLocation) {
    if (Constants.UNAUTHORIZED === status) {
      // history.push({
      //   pathname: "/login",
      //   search: previousLocation
      // });
      history.push("/login");
    }
  }

  executeBasicAuthenticationService(username, password) {
    var token = `${username}:${password}`;
    return axios.post(`${Constants.AUTOREPAIR_API_URL}/api/login`, {
      headers: {
        "Access-Control-Allow-Origin": "*",
        Authorization: `basic ${window.btoa(token)}`
      }
    });
  }

  registerSuccessfulLogin(token) {
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, token);
    this.setupAxiosInterceptors(token);
  }

  setupAxiosInterceptors(token) {
    axios.interceptors.request.use(config => {
      if (this.isUserLoggedIn()) {
        config.headers.Authorization = token;
      }
      return config;
    });
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return false;
    return true;
  }
}

export default new AuthenticationService();
