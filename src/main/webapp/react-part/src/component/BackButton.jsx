import React, { Component } from "react";

class BackButton extends Component {
  render() {
    return (
      <button
        className="btn btn-light"
        onClick={() => this.props.goBackAction()}
        style={{ marginTop: "10px" }}
      >
        Назад
      </button>
    );
  }
}

export default BackButton;
