import React, { Component } from "react";
import AuthenticationService from "../service/AuthenticationService";

class LoginComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      previousLocation: this.props.location,
      username: "",
      password: "",
      hasLoginFailed: false,
      showSuccessMessage: false
    };

    this.handleChange = this.handleChange.bind(this);
    this.loginClicked = this.loginClicked.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  loginClicked() {
    AuthenticationService.executeBasicAuthenticationService(
      this.state.username,
      this.state.password
    )
      .then(response => {
        AuthenticationService.registerSuccessfulLogin(response.data);
        this.props.history.push(`/workshops`);
      })
      .catch(e => {
        console.log(e);
        this.setState({ showSuccessMessage: false });
        this.setState({ hasLoginFailed: true });
      });
  }

  render() {
    return (
      <div>
        <h1>Вход</h1>
        <div className="container">
          {this.state.hasLoginFailed && (
            <div className="alert alert-warning">Неверные логин или пароль!</div>
          )}
          {this.state.showSuccessMessage && <div>Login Successful</div>}
          Логин:{" "}
          <input
            type="text"
            name="username"
            value={this.state.username}
            onChange={this.handleChange}
          />
          <br />
          Пароль:{" "}
          <input
            type="password"
            name="password"
            value={this.state.password}
            onChange={this.handleChange}
            style={{ marginTop: "10px" }}
          />
          <br />
          <button
            className="btn btn-success"
            style={{ marginTop: "10px" }}
            onClick={this.loginClicked}
          >
            Вход
          </button>
        </div>
      </div>
    );
  }
}

export default LoginComponent;
