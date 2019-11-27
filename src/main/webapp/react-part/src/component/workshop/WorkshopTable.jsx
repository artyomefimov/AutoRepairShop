import React, { Component } from "react";
import WorkshopRow from "./WorkshopRow";
import AutorepairService from "../../service/AutorepairService";
import PageName from "../PageName";
import SuccessMessage from "../message/SuccessMessage";
import * as Constants from "../../Constants";
import AuthenticationService from "../../service/AuthenticationService";

class WorkshopTable extends Component {
  constructor(props) {
    super(props);

    this.state = {
      objects: [],
      message: null
    };
    this.requestWorkshops = this.requestWorkshops.bind(this);
    this.deleteWorkshop = this.deleteWorkshop.bind(this);
    this.openWorkshopDetails = this.openWorkshopDetails.bind(this);
    this.openMastersByWorkshopId = this.openMastersByWorkshopId.bind(this);
    this.openCustomersByWorkshopId = this.openCustomersByWorkshopId.bind(this);
    this.createWorkshop = this.createWorkshop.bind(this);
    this.openLevels = this.openLevels.bind(this);
  }

  componentDidMount() {
    this.requestWorkshops();
  }

  requestWorkshops() {
    AutorepairService.getAllWorkshops()
      .then(response => {
        this.setState({ objects: response.data });
      })
      .catch(e => {
        this.setState({ message: e.message });
        AuthenticationService.redirectToLoginIfUnauthorized(
          e.response.status,
          this.props.history
        );
      });
  }

  deleteWorkshop(id) {
    if (window.confirm("Вы действительно хотите удалить эту автомастерскую?"))
      AutorepairService.deleteWorkshop(id)
        .then(() => {
          this.setState({ message: "Автомастерская успешно удалена!" });
          this.requestWorkshops();
        })
        .catch(e => {
          this.setState({ message: e.message });
          AuthenticationService.redirectToLoginIfUnauthorized(
            e.response.status,
            this.props.history
          );
        });
  }

  openWorkshopDetails(id) {
    this.props.history.push(`/workshops/workshop/${id}`);
  }

  openMastersByWorkshopId(workshopId, name) {
    this.props.history.push({
      pathname: `/workshop/${workshopId}/masters`,
      search: `?workshop=${name}`
    });
  }

  openCustomersByWorkshopId(workshopId, name) {
    this.props.history.push({
      pathname: `/workshop/${workshopId}/customers`,
      search: `?workshop=${name}`
    });
  }

  openLevels() {
    this.props.history.push("/levels");
  }

  createWorkshop() {
    this.props.history.push(`/workshops/workshop/-1`);
  }

  render() {
    let key = 0;
    return (
      <>
        <PageName pageName={Constants.workshopListPageName} />
        <div className="container">
          {this.state.message && (
            <SuccessMessage message={this.state.message} />
          )}
          <div className="container">
            <table className="table">
              <thead>
                <tr>
                  {Constants.workshopListTableHeaders.map(tableHeader => (
                    <th className="main-th" key={key++}>
                      {tableHeader}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {this.state.objects.map(object => (
                  <WorkshopRow
                    key={key++}
                    object={object}
                    onDeleteClicked={this.deleteWorkshop}
                    onOpenWorkshopDetailsClicked={this.openWorkshopDetails}
                    onOpenMastersClicked={this.openMastersByWorkshopId}
                    onOpenCustomersClicked={this.openCustomersByWorkshopId}
                  />
                ))}
              </tbody>
            </table>
            <button
              className="btn btn-success"
              onClick={() => this.createWorkshop()}
            >
              Добавить
            </button>
          </div>
          <button
            className="btn btn-info"
            onClick={() => this.openLevels()}
            style={{ marginTop: "10px" }}
          >
            Квалификации
          </button>
        </div>
      </>
    );
  }
}

export default WorkshopTable;
