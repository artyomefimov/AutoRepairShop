import React, { Component } from "react";

class SuccessMessage extends Component {
  render() {
    return <div className="alert alert-success">{this.props.message}</div>;
  }
}

export default SuccessMessage;
