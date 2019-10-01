import React, { Component } from "react";
import AutorepairService from "../../service/AutorepairService";
import PageName from "../PageName";
import SuccessMessage from "../message/SuccessMessage";
import * as Constants from "../../Constants";
import BackButton from "../BackButton";
import CustomerRow from "./CustomerRow";
import * as Utils from "../../utils/Utils"

class CustomerTable extends Component {
  constructor(props) {
    super(props);

    this.state = {
      workshopName: this.props.location.search,//Utils.resolveParentObjectName(this.props.location.search),
      workshopId: this.props.match.params.workshopId,
      objects: [],
      message: null
    };
    this.requestCustomersByWorkshopId = this.requestCustomersByWorkshopId.bind(
      this
    );
    this.deleteCustomer = this.deleteCustomer.bind(this);
    this.openCustomerDetails = this.openCustomerDetails.bind(this);
    this.openCarsByCustomerId = this.openCarsByCustomerId.bind(this);
    this.createCustomer = this.createCustomer.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    this.requestCustomersByWorkshopId(this.state.workshopId);
  }

  requestCustomersByWorkshopId(workshopId) {
    AutorepairService.getCustomersByWorkshopId(workshopId)
      .then(response => {
        this.setState({ objects: response.data });
      })
      .catch(e => this.setState({ message: e.message }));
  }

  deleteCustomer(customerId) {
    if (window.confirm("Вы действительно хотите удалить этого клиента?"))
      AutorepairService.deleteLevel(customerId)
        .then(() => {
          this.setState({ message: "Клиент успешно удален!" });
          this.requestCustomersByWorkshopId();
        })
        .catch(e => this.setState({ message: e.message }));
  }

  openCustomerDetails(customerId) {
    this.props.history.push(`/customers/customer/${customerId}`);
  }

  openCarsByCustomerId(customerId) {
    alert(`openMastersByLevelId ${customerId}`);
  }

  createCustomer() {
    this.props.history.push(`/customers/customer/-1`);
  }

  goBack() {
    this.props.history.push(`/workshops`);
  }

  render() {
    let key = 0;
    let pageName = Constants.customerListPageName;// + this.state.workshopName

    return (
      <>
        <PageName pageName={pageName} />
        <div className="container">
          {this.state.message && (
            <SuccessMessage message={this.state.message} />
          )}
          <div className="container">
            <table className="table">
              <thead>
                <tr>
                  {Constants.customerListTableHeaders.map(tableHeader => (
                    <th className="main-th" key={key++}>
                      {tableHeader}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {this.state.objects.map(object => (
                  <CustomerRow
                    key={key++}
                    object={object}
                    onDeleteClicked={this.deleteCustomer}
                    onOpenCustomerDetailsClicked={this.openCustomerDetails}
                    onOpenCarsClicked={this.openCarsByCustomerId}
                  />
                ))}
              </tbody>
            </table>
            <button
              className="btn btn-success"
              onClick={() => this.createCustomer()}
            >
              Добавить
            </button>
          </div>
          <BackButton goBackAction={this.goBack} />
        </div>
      </>
    );
  }
}

export default CustomerTable;
