import React, { Component } from "react";
import * as Constants from "../Constants";

class ObjectRow extends Component {
  constructor(props) {
    super(props);

    this.deleteObjectClicked = this.deleteObject.bind(this);
    this.openObject = this.openObject.bind(this);
    this.openObjectDetails = this.openObjectDetails.bind(this);
  }

  deleteObject(id) {
    this.props.onDeleteClicked(id);
  }

  openObject(id) {
    this.props.onOpenObjectClicked(id);
  }

  openObjectDetails(id) {
    this.props.onOpenObjectDetailsClicked(id);
  }

  render() {
    const object = this.props.object;
    let key = 0;

    switch (this.props.objectType) {
      case Constants.workshopObjectType:
        return (
          <tr className="top-margin" key={object.workshopId}>
            <td key={key++} className="main-td">
              {object.inn}
            </td>
            <td key={key++} className="main-td">
              {object.name}
            </td>
            <td key={key++} className="main-td">
              {object.address}
            </td>
            <td key={key++} className="main-td">
              {object.openHours}
            </td>
            <td key={key++} className="main-td">
              {object.closeHours}
            </td>
            <td key={key++} className="main-td">
              {object.ownerName}
            </td>
            <td>
              <button
                className="btn btn-info"
                onClick={() => this.openObject(object.workshopId)}
              >
                Открыть
              </button>
            </td>
            <td>
              <button
                className="btn btn-success"
                onClick={() => this.openObjectDetails(object.workshopId)}
              >
                Изменить
              </button>
            </td>
            <td>
              <button
                className="btn btn-warning"
                onClick={() => this.deleteObject(object.workshopId)}
              >
                Удалить
              </button>
            </td>
          </tr>
        );
      default:
        return <tr />;
    }
  }
}

export default ObjectRow;
