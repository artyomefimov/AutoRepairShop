import React, { Component } from "react";
import AutorepairService from "../../service/AutorepairService";
import AuthenticationService from "../../service/AuthenticationService";
import PageName from "../PageName";
import SuccessMessage from "../message/SuccessMessage";
import * as Constants from "../../Constants";
import BackButton from "../BackButton";
import MasterRow from "./MasterRow";
import * as Utils from "../../utils/Utils";

class MasterTable extends Component {
  constructor(props) {
    super(props);

    this.state = {
      parentName: Utils.resolveParentObjectTypeAndName(
        this.props.location.search,
        this.props.match.url
      ),
      parentId: this.props.match.params.parentId,
      parentType: Utils.resolveParentObjectType(this.props.match.url),
      objects: [],
      message: null
    };
    this.requestMastersByParentId = this.requestMastersByParentId.bind(this);
    this.deleteMaster = this.deleteMaster.bind(this);
    this.openMasterDetails = this.openMasterDetails.bind(this);
    this.openCarsByMasterId = this.openCarsByMasterId.bind(this);
    this.createMaster = this.createMaster.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    this.requestMastersByParentId(this.state.parentId);
  }

  requestMastersByParentId(parentId) {
    if (this.state.parentType === "workshop") {
      AutorepairService.getMastersByWorkshopId(parentId)
        .then(response => {
          this.setState({ objects: response.data });
        })
        .catch(e => {
          AuthenticationService.redirectToLoginIfUnauthorized(
            e.response.status,
            this.props.history,
            this.props.location
          );
          this.setState({ message: e.message });
        });
    } else {
      AutorepairService.getMastersByLevelId(parentId)
        .then(response => {
          this.setState({ objects: response.data });
        })
        .catch(e => {
          AuthenticationService.redirectToLoginIfUnauthorized(
            e.response.status,
            this.props.history
          );
          this.setState({ message: e.message });
        });
    }
  }

  deleteMaster(masterId) {
    if (window.confirm("Вы действительно хотите удалить этого мастера?"))
      AutorepairService.deleteMaster(masterId)
        .then(() => {
          this.setState({ message: "Мастер успешно удален!" });
          this.requestMastersByParentId(this.state.parentId);
        })
        .catch(e => {
          this.setState({ message: e.message });
          AuthenticationService.redirectToLoginIfUnauthorized(
            e.response.status,
            this.props.history
          );
        });
  }

  openMasterDetails(masterId) {
    this.props.history.push(`/masters/master/${masterId}`);
  }

  openCarsByMasterId(masterId, name, workshopId) {
    this.props.history.push({
      pathname: `/workshop/${workshopId}/master/${masterId}/cars`,
      search: `?master=${name}`
    });
  }

  createMaster() {
    this.props.history.push(`/masters/master/-1`);
  }

  goBack() {
    if (this.state.parentType === "workshop") {
      this.props.history.push(`/workshops`);
    } else {
      this.props.history.push(`/levels`);
    }
  }

  render() {
    let key = 0;
    let pageName = Constants.masterListPageName + this.state.parentName;

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
                  {Constants.masterListTableHeaders.map(tableHeader => (
                    <th className="main-th" key={key++}>
                      {tableHeader}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {this.state.objects.map(object => (
                  <MasterRow
                    key={key++}
                    object={object}
                    onDeleteClicked={this.deleteMaster}
                    onOpenMasterDetailsClicked={this.openMasterDetails}
                    onOpenCarsClicked={this.openCarsByMasterId}
                  />
                ))}
              </tbody>
            </table>
            <button
              className="btn btn-success"
              onClick={() => this.createMaster()}
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

export default MasterTable;
