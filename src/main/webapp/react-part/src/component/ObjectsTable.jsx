import React, { Component } from "react";
import ObjectRow from "./ObjectRow";
import WorkshopService from "../service/WorkshopService";

class ObjectsTable extends Component {
  constructor(props) {
    super(props);

    this.state = {
      objects: [],
      message: null
    };
    this.requestObjects = this.requestObjects.bind(this);
  }

  componentDidMount() {
    this.requestObjects();
  }

  requestObjects() {
    WorkshopService.getAllWorkshops().then(
        response => {
            this.setState({objects: response.data})
        }
    )
  }

  render() {
      let key = 0
    return (
      <div className="container">
        {this.state.message && (
          <div className="alert alert-success">{this.state.message}</div>
        )}
        <div className="container">
          <table className="table">
            <thead>
              <tr>
                {this.props.tableHeaders.map(tableHeader => (
                  <th className="main-th" key = {key++}>{tableHeader}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {this.state.objects.map(object => (
                <ObjectRow key = {key++} object={object} objectType={this.props.objectType} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ObjectsTable;
