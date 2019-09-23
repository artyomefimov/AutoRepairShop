import React, { Component } from 'react';

class PageName extends Component {
    render() {
        return (
            <div className="container">
                <h3>{this.props.pageName}</h3>
            </div>
        );
    }
}

export default PageName;