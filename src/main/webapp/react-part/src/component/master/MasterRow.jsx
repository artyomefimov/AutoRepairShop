import React, { Component } from "react";
import * as Utils from "../../utils/Utils"

class MasterRow extends Component {
    constructor(props) {
        super(props);
    
        this.deleteMaster = this.deleteMaster.bind(this);
        this.openCarsByMasterId = this.openCarsByMasterId.bind(this);
        this.openMasterDetails = this.openMasterDetails.bind(this);
      }
    
      deleteMaster(customerId) {
        this.props.onDeleteClicked(customerId);
      }
    
      openCarsByMasterId(customerId) {
        this.props.onOpenCarsClicked(customerId);
      }
    
      openMasterDetails(customerId) {
        this.props.onOpenMasterDetailsClicked(customerId);
      }
    
      render() {
        let object = this.props.object;
        let key = 0;
        let {series, num} = Utils.splitPassportNumOnSeriesAndNum(object.customerPassportNum)
    
        return (
          <tr className="top-margin" key={object.masterId}>
            <td key={key++} className="main-td">
              {series + " " + num}
            </td>
            <td key={key++} className="main-td">
              {object.name}
            </td>
            <td key={key++} className="main-td">
              {object.phone}
            </td>
            <td>
              <button
                className="btn btn-info"
                onClick={() => this.openCarsByMasterId(object.masterId)}
              >
                Автомобили
              </button>
            </td>
            <td>
              <button
                className="btn btn-success"
                onClick={() => this.openMasterDetails(object.masterId)}
              >
                Изменить
              </button>
            </td>
            <td>
              <button
                className="btn btn-warning"
                onClick={() => this.deleteMaster(object.masterId)}
              >
                Удалить
              </button>
            </td>
          </tr>
        );
      }
}

export default MasterRow;