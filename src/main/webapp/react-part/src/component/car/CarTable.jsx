import React, { Component } from "react";
import AutorepairService from "../../service/AutorepairService";
import PageName from "../PageName";
import SuccessMessage from "../message/SuccessMessage";
import * as Constants from "../../Constants";
import BackButton from "../BackButton";
import * as Utils from "../../utils/Utils";
import CarRow from "./CarRow";

class CarTable extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          parentName: Utils.resolveCarParentObjectName(this.props.location.search, this.props.match.url),
          parentId: this.props.match.params.parentId,
          workshopId: this.props.match.params.workshopId,
          parentType: Utils.resolveCarParentObjectType(this.props.match.url),
          objects: [],
          message: null
        };
        this.requestCarsByParentId = this.requestCarsByParentId.bind(this);
        this.deleteCar = this.deleteCar.bind(this);
        this.openCarDetails = this.openCarDetails.bind(this);
        this.createCar = this.createCar.bind(this);
        this.goBack = this.goBack.bind(this);
      }
    
      componentDidMount() {
        this.requestCarsByParentId(this.state.parentId);
      }
    
      requestCarsByParentId(parentId) {
        if (this.state.parentType === "master") {
          AutorepairService.getCarsByMasterId(parentId)
            .then(response => {
              this.setState({ objects: response.data });
            })
            .catch(e => this.setState({ message: e.message }));
        } else {
          AutorepairService.getCarsByCustomerId(parentId)
            .then(response => {
              this.setState({ objects: response.data });
            })
            .catch(e => this.setState({ message: e.message }));
        }
      }
    
      deleteCar(carId) {
        if (window.confirm("Вы действительно хотите удалить этот автомобиль?"))
          AutorepairService.deleteCar(carId)
            .then(() => {
              this.setState({ message: "Автомобиль успешно удален!" });
              this.requestCarsByParentId(this.state.parentId);
            })
            .catch(e => this.setState({ message: e.message }));
      }
    
      openCarDetails(carId, workshopId) {
        this.props.history.push(`/workshop/${workshopId}/cars/car/${carId}`);
      }
    
      createCar(workshopId) {
        this.props.history.push(`/workshop/${workshopId}/cars/car/-1`);
      }
    
      goBack() {
        this.props.history.goBack()
      }
    
      render() {
        let key = 0;
        let pageName = Constants.carListPageName + this.state.parentName;
    
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
                      {Constants.carListTableHeaders.map(tableHeader => (
                        <th className="main-th" key={key++}>
                          {tableHeader}
                        </th>
                      ))}
                    </tr>
                  </thead>
                  <tbody>
                    {this.state.objects.map(object => (
                      <CarRow
                        key={key++}
                        object={object}
                        onDeleteClicked={this.deleteCar}
                        onOpenCarDetailsClicked={this.openCarDetails}
                      />
                    ))}
                  </tbody>
                </table>
                <button
                  className="btn btn-success"
                  onClick={() => this.createCar(this.state.workshopId)}
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

export default CarTable;