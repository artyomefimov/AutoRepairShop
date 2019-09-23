import React, { Component } from "react";

class ObjectRow extends Component {
  render() {
    const object = this.props.object;
    let key = 0
    switch (this.props.objectType) {
      case "workshop":
        return (
          <tr key={object.inn}>
            <td key = {key++} className="main-td">{object.inn}</td>
            <td key = {key++} className="main-td">{object.name}</td>
            <td key = {key++} className="main-td">{object.address}</td>
            <td key = {key++} className="main-td">{object.openHours}</td>
            <td key = {key++} className="main-td">{object.closeHours}</td>
            <td key = {key++} className="main-td">{object.ownerName}</td>
          </tr>
        );
      default:
        return <tr />;
    }
  }
}

export default ObjectRow;
