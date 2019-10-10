import React, { Component } from "react";

class CarRow extends Component {
    constructor(props) {
        super(props);
    
        this.deleteCar = this.deleteCar.bind(this);
        this.openCarDetails = this.openCarDetails.bind(this);
      }
    
      deleteCar(carId) {
        this.props.onDeleteClicked(carId);
      }
    
      openCarDetails(carId, workshopId) {
        this.props.onOpenCarDetailsClicked(carId, workshopId);
      }
    
      render() {
        let object = this.props.object;
        let key = 0;
    
        return (
          <tr className="top-margin" key={object.carId}>
            <td key={key++} className="main-td">
              {object.carNumber}
            </td>
            <td key={key++} className="main-td">
              {object.mark}
            </td>
            <td key={key++} className="main-td">
              {object.model}
            </td>
            <td key={key++} className="main-td">
              {object.crashType}
            </td>
            <td key={key++} className="main-td">
              {object.mileage}
            </td>
            <td>
              <button
                className="btn btn-success"
                onClick={() => this.openCarDetails(object.carId, object.master.workshop.workshopId)}
              >
                Изменить
              </button>
            </td>
            <td>
              <button
                className="btn btn-warning"
                onClick={() => this.deleteCar(object.carId)}
              >
                Удалить
              </button>
            </td>
          </tr>
        );
      }
}

export default CarRow;