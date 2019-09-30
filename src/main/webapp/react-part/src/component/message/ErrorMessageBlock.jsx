import React, { Component } from 'react';

class ErrorMessageBlock extends Component {
    render() {
        return <div className="alert alert-danger">{this.props.message}</div>;
    }
}

export default ErrorMessageBlock;