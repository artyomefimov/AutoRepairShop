import React, { Component } from "react";

class WorkshopRow extends Component {
  constructor(props) {
    super(props);

    this.deleteWorkshop = this.deleteWorkshop.bind(this);
    this.openMastersByWorkshopId = this.openMastersByWorkshopId.bind(this);
    this.openCustomersByWorkshopId = this.openCustomersByWorkshopId.bind(this);
    this.openWorkshopDetails = this.openWorkshopDetails.bind(this);
  }

  deleteWorkshop(id) {
    this.props.onDeleteClicked(id);
  }

  openMastersByWorkshopId(id, name) {
    this.props.onOpenMastersClicked(id, name);
  }

  openCustomersByWorkshopId(id, name) {
    this.props.onOpenCustomersClicked(id, name);
  }

  openWorkshopDetails(id) {
    this.props.onOpenWorkshopDetailsClicked(id);
  }

  render() {
    const object = this.props.object;
    let key = 0;

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
            onClick={() => this.openMastersByWorkshopId(object.workshopId, object.name)}
          >
            Мастера
          </button>
        </td>
        <td>
          <button
            className="btn btn-info"
            onClick={() => this.openCustomersByWorkshopId(object.workshopId, object.name)}
          >
            Клиенты
          </button>
        </td>
        <td>
          <button
            className="btn btn-success"
            onClick={() => this.openWorkshopDetails(object.workshopId)}
          >
            Изменить
          </button>
        </td>
        <td>
          <button
            className="btn btn-warning"
            onClick={() => this.deleteWorkshop(object.workshopId)}
          >
            Удалить
          </button>
        </td>
      </tr>
    );
  }
}

export default WorkshopRow;
