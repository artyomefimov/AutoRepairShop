import React, { Component } from "react";
import * as Utils from "../../utils/Utils"

class CustomerRow extends Component {
  constructor(props) {
    super(props);

    this.deleteCustomer = this.deleteCustomer.bind(this);
    this.openCarsByCustomerId = this.openCarsByCustomerId.bind(this);
    this.openCustomerDetails = this.openCustomerDetails.bind(this);
  }

  deleteCustomer(customerId) {
    this.props.onDeleteClicked(customerId);
  }

  openCarsByCustomerId(customerId, name, workshopId) {
    this.props.onOpenCarsClicked(customerId, name, workshopId);
  }

  openCustomerDetails(customerId) {
    this.props.onOpenCustomerDetailsClicked(customerId);
  }

  render() {
    let object = this.props.object;
    let key = 0;
    let {series, num} = Utils.splitPassportNumOnSeriesAndNum(object.customerPassportNum)
    let birthDate = Utils.getDayMonthAndYearFromDateAsLong(object.birthDate)

    return (
      <tr className="top-margin" key={object.customerId}>
        <td key={key++} className="main-td">
          {series + " " + num}
        </td>
        <td key={key++} className="main-td">
          {object.name}
        </td>
        <td key={key++} className="main-td">
          {object.phone}
        </td>
        <td key={key++} className="main-td">
          {object.address}
        </td>
        <td key={key++} className="main-td">
          {birthDate}
        </td>
        <td>
          <button
            className="btn btn-info"
            onClick={() => this.openCarsByCustomerId(object.customerId, object.name, object.workshop.workshopId)}
          >
            Автомобили
          </button>
        </td>
        <td>
          <button
            className="btn btn-success"
            onClick={() => this.openCustomerDetails(object.customerId)}
          >
            Изменить
          </button>
        </td>
        <td>
          <button
            className="btn btn-warning"
            onClick={() => this.deleteCustomer(object.customerId)}
          >
            Удалить
          </button>
        </td>
      </tr>
    );
  }
}

export default CustomerRow;
