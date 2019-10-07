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
          parentName: Utils.resolveParentObjectTypeAndName(this.props.location.search, this.props.match.url),
          parentId: this.props.match.params.parentId,
          parentType: Utils.resolveParentObjectType(this.props.match.url),
          //previousLocation: Utils.resolvePreviousLocation(this.props.location.previousLocation, this.props.match.url),
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
    
      openCarDetails(carId) {
        this.props.history.push(`/cars/car/${carId}`);
      }
    
      createCar() {
        this.props.history.push(`/cars/car/-1`);
      }
    
      goBack() {
        this.props.history.push("/")
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
                  onClick={() => this.createCar()}
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